package com.zoker.lib.fragment.list;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

import java.util.Collection;
import java.util.Observable;

/**
 * Created by Administrator on 2017/11/28.
 */

abstract class PageDataProvider<T> {
    private static final String TAG = "PageDataProvider";

    int pageCount = 10;
    int page = 0;

    // 还有更多数据
    private boolean hasMore = true;

    public abstract T sourse(int page);

    /**
     * 设置当前页码
     *
     * @param count
     */
    public void setPageCount(int count) {
        pageCount = count;
    }

    /**
     * @return
     */
    public int getPageCount() {
        return pageCount;
    }

    public void setCurrentPage(int page) {
        this.page = page;
    }

    /**
     * 下一页数据
     *
     * @return
     */
    @MainThread
    public boolean next() {
        page++;
        getData(page);
        return hasMore;
//        return true;
    }

    // 前一页数据
    @MainThread
    public boolean pre() {
        page--;
        getData(page);
        return false;
    }

    public void reset() {
        page = 0;
//        next();
    }

    /**
     * 取消当前操作
     */
    public void cancel() {

    }

    /**
     * 销毁
     */
    public void destory() {
        if (mTask != null && mTask.getStatus() != AsyncTask.Status.FINISHED) {
            mTask.cancel(true);
        }
    }

    /**
     * 在当前线程返回结果
     *
     * @param page
     * @return
     */
    public T getPageData(int page) {
        return sourse(page);
    }

    /**
     * 数据回调
     *
     * @param data
     */
    @MainThread
    public abstract void onData(int page, T data);

    private synchronized void getData(final int p) {

        // 使用RxAndroid处理数据
//        rxJava(p);
//        // 使用系统的AsyncTask
        asyncTask(p);
    }

    private DataTask mTask = null;

    /**
     * 使用系统AsyncTask处理数据
     *
     * @param page
     */
    private void asyncTask(int page) {
        Log.i(TAG, "#asyncTask page = " + page);
        if (mTask != null && mTask.getStatus() != AsyncTask.Status.FINISHED) {
            mTask.cancel(true);
        }

        mTask = new DataTask();
        mTask.execute(Integer.valueOf(page));

    }

    private class DataTask extends AsyncTask<Integer, Integer, T> {
        private int p = 0;
        private boolean isError = false;
        private String errorMsg = null;

        @Override
        protected T doInBackground(Integer... params) {
            try {
                Log.i(TAG, "$DataTask#doInBackground params[0] = " + params[0]);
                p = params[0];
                isError = false;
                T result = sourse(p);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                setError(true, e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(T data) {
            if (isError)
                onError(errorMsg);
            else {
                if (data instanceof Collection) {
                    Collection collection = (Collection) data;
                    hasMore = !(p > 1 && collection.size() < pageCount);
                } else {
                    hasMore = !(data == null);
                }
                onData(p, data);
            }
        }
    }

    public void setError(Boolean isError, String msg) {
        if (mTask != null) {
            mTask.isError = isError;
            mTask.errorMsg = msg;
        }
    }

    public void onError(String msg) {

    }
}
