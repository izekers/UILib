package com.zoker.lib.image.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.zoker.lib.image.largeimage.LargeImageView;
import com.zoker.lib.image.largeimage.factory.FileBitmapDecoderFactory;
import com.zoker.lib.image.largeimage.factory.InputStreamBitmapDecoderFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/25.
 */

public class GalleryView extends FrameLayout {
    private ViewPager viewPager;
    private List<GalleryModel> datas;
    protected ImagePagerAdapter adapter;

    public GalleryView(@NonNull Context context) {
        super(context);
        initView();
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setBackgroundColor(Color.BLACK);
        viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager.setAdapter(adapter = new ImagePagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int lastIndex = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.onChangeIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    if (lastIndex != viewPager.getCurrentItem()) {
                        adapter.currView.get(lastIndex).reset();
                        lastIndex = viewPager.getCurrentItem();
                    }
                }
            }
        });
        this.addView(viewPager);
    }

    public void setData(List<GalleryModel> datas) {
        this.datas = datas;
        adapter.notifyDataSetChanged();
    }

    class ImagePagerAdapter extends PagerAdapter {
        protected List<LargeImageView> currView = new ArrayList<>();
        protected int currIndex, count;

        @Override
        public int getCount() {
            if (datas == null)
                return 0;
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object viewObj) {
            View view = (View) viewObj;
            collection.removeView(view);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (currIndex == position && currView.get(position) != null) return;
            if (currView.get(position) != null)
                currView.get(position).reset();
            currIndex = position;
        }

        public void onChangeIndex(int position) {
//            refreshUI();
//            for (MyVideoView videoView : videoViewList) {
//                videoView.release();
//                videoView.reset();
//                tipView.setVisibility(View.INVISIBLE);
//            }
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            View convertView = getView(position);
            collection.addView(convertView, 0);
            return convertView;
        }

        protected View getView(int position) {
            final GalleryModel model = datas.get(position);
            final LargeImageView convertView;
            if (currView.size() <= position || currView.get(position) == null) {
                convertView = new LargeImageView(getContext());
                currView.add(convertView);
            } else
                convertView = currView.get(position);

            if (model.getDataType() == GalleryModel.SOURCE_FORM_URL) {
                convertView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        convertView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        String imgPath = model.getSource();
                        if (!isNullOrWhiteSpace(imgPath)) {
                            Glide.with(getContext()).load(imgPath).downloadOnly(new SimpleTarget<File>() {
                                @Override
                                public void onResourceReady(File thumbFile, GlideAnimation<? super File> glideAnimation) {
                                    if (thumbFile != null && thumbFile.exists()) {
                                        convertView.setImage(new FileBitmapDecoderFactory(thumbFile));
                                    }
                                }
                            });
                        }
                    }
                });

            } else if (model.getDataType() == GalleryModel.SOURCE_FORM_BITMAP) {
                convertView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        convertView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        convertView.setImage(model.getBitmap());
                    }
                });

            } else if (model.getDataType() == GalleryModel.SOURCE_FORM_ASSEST) {
                convertView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        convertView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        try {
                            convertView.setImage(new InputStreamBitmapDecoderFactory(getContext().getAssets().open(model.getSource())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return convertView;
        }

        /**
         protected ImageLoadingListener getImageLoadingListener(final BaseImageView imageView) {
         return new EmptyImageLoadingListener() {
        @Override public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        imageView.setLoadSuccess(false);
        imageView.getProgressBar().setVisibility(View.GONE);
        if (!currView.isLoadSuccess()) {
        Toast.makeText(BaseApplication.Instance, R.string.image_load_fail_check_network, Toast.LENGTH_SHORT).show();
        optionView.setVisibility(View.INVISIBLE);
        }
        }

        @Override public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        imageView.getProgressBar().setVisibility(View.GONE);
        imageView.getPhotoView().setVisibility(View.VISIBLE);
        optionView.setVisibility(View.VISIBLE);
        if (readOnly) {
        optionView.setVisibility(View.INVISIBLE);
        }
        }
        };
         }
         **/
    }


    public boolean isNullOrWhiteSpace(String text) {
        if (text == null)
            return true;
        return text.matches("^\\s*$");
    }
}
