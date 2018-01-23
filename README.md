UILib
---

`UILib`这个仓库将存放平时我所书写的一些UI控件,并定期发布版本，便于多项目重复利用

计划
---
* WebActivityView：一个现有的万能WebViewActivity
* 一个包含上下拉刷新的快速开发Fragment


现有控件
---
* MaterialImageView:多模式图形显示控件，支持圆形边框，圆角框
* ColorButton:颜色修改按钮
* LargeImage:可放大缩小图片的控件
* GalleryView:画廊View,控件式的画廊效果，可以自定义为画廊Activity，之后将提供默认的GalleryActivity
* GifImageView:可以使用动画的ImageView，这里暂时实现了帧动画
* BottomNavigationBar:底部导航栏
* FlowLayout:流式布局
* FoldingLayout:折叠布局，暂只支持两个子布局
* TabScrollView:仿支付宝tab和ScrollView联动布局

工具类
--
* ScrollerHelper:由ViewDrawHelper启发，用于快速开发自定义View的滚动效果时使用

开始使用UILib
---

UILib使用

### 添加依赖

* Step1.把 JitPack repository 添加到工程的build.gradle文件中 repositories的末尾:
```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

* Step 2. 在你的app build.gradle 的 dependencies 中添加依赖
```groovy
dependencies {
	compile 'com.github.izekers:UILib:v1.7.1'
}
```

*Step3. 查看wiki（暂未书写）

控件介绍
---

### MaterialImageView/多模式图形显示控件，支持圆形边框，圆角框

*资源文件中使用
```xml
app:mode=""  (circle:圆形边框 normal:不做修改 rounded_corners:圆角边框)
app:round_size=""  (round_size:圆角边框弯曲弧度)
```
*java代码中使用

```java
imageView.setMode(MaterialImageView.MODE_CIRCLE); (MaterialImageView.MODE_CIRCLE:圆形边框  MaterialImageView.MODE_NORMAL:不做修改 MaterialImageView.MODE_ROUNDED_CORNERS:圆角边框)
```


ColorButton
---

* 资源文件中使用
```xml
边框颜色：
app:border_color="@android:color/darker_gray"
边框宽度：
app:border_width="1dp"
正常显示时的颜色：
app:normal_color="@android:color/holo_blue_light"
点击显示颜色：
app:press_color="@android:color/holo_blue_dark"
弯曲弧度：
app:radius="20dp"
```
* java代码中使用(暂无)

```java
设置颜色：
imageView.setColor(@ColorRes int normalColor,@ColorRes int pressColor);
```


LargeImageView
---


GalleryView
---


* 已知的一些bug
未滑动完全



GifImageView
---
gif推荐还是使用android-gif-drawable，其他控件包括Glide的gif都容易导致oom，而android-gif=drawable并没有此问题，底层解码使用C实现，极大的提高了解码效率，同时很大程度上避免了OOM现象出现。



BottomNavigationBar：底部导航栏
---

```java
//绑定ViewPager
BottomNavigationBar.Worker worker = bottomNavigationBar.setup(viewPager, getSupportFragmentManager());
worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("首页"));
worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第二页"));
worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第三页"));
//仅当调用work的时候绑定数据
worker.work();

//自定义tab样式，注意：image使用@id/tab_img，title使用@id/tab_title
bottomNavigationBar.setTabLayout(@LayoutRes int tab_layout);
```

FlowLayout:流式布局
---
*资源文件中使用
```xml
app:flow_gravity=" "(center:居中排版 left:从左到右排版 right:从右到左排版 bottom:从下到上排版)
```
*java代码中使用

```java
使用规则跟普通的ViewGroup一样即可

 /**
     * 设置最多显示行数
     * @param maxLines
    */
 public void setMaxLines(int maxLines)
```

Folding:折叠布局
---
*资源文件中使用
```xml
   <com.zoker.lib.widght.FoldingLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="bottomView"
            android:paddingTop="10dp"
            android:paddingBottom="10dp"/>
        <TextView
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:text="topView"
            android:background="#321358"
            android:paddingTop="10dp"
            android:clickable="true"
            android:paddingBottom="10dp"/>
    </com.zoker.lib.widght.FoldingLayout>
```

TabScrollView：仿支付宝tab和Scroll联动布局
---
* java代码中使用
```java
   /**
     * 移除所有子控件
     */
    public void removeAllItems()   
    
    
   /**
    * 修正底部空隙
    */
   public void setBottomSpace(int bottomSpace)
        
        
    /**
     * 添加联动的item
     * @param tabString tab的名字
     * @param view      联动的item
      */
    public void addItem(String tabString, View view) 
```

# 工具类介绍

ScrollerHelper：滑动实现助手
---

## 使用方法
1. 创建实例对象
```java
ScrollerHelper scrollerHelper = ScrollerHelper.create(View view, ScrollerHelper.Callback callback);
```

2. 根据实现ScrollerHelper.Callback抽象类
```java

    public abstract static class Callback {
        /**
         * 松开手后的操作
         *
         * @param scrollX View滑动到的点 x坐标 = view.getScrollX();
         * @param scrollY View滑动到的点 y坐标 = view.getScrollY();
         */
        public void onRelease(float scrollX, float scrollY) {
        }


        /**
         * 滑动事件触发
         *
         * @param scrollX View滑动到的点 x坐标 = view.getScrollX();
         * @param dx       水平线上滑动的距离
         */
        public void onHorizontalMove(float scrollX, float dx) {
        }

        /**
         * 滑动事件触发
         *
         * @param scrollY View滑动到的点 y坐标 = view.getScrollY();
         * @param dy       竖直线上滑动的距离
         */
        public void onVerticalMove(float scrollY, float dy) {
        }

        /**
         * 判断组件是否可以横向移动
         *
         * @param scrollX 当前View滑动到的点 x坐标 = view.getScrollX();
         * @param diff    手指在屏幕上滑动的距离
         * @return
         */
        public boolean isHorizontalMove(float scrollX, float diff) {
            return false;
        }

        /**
         * Y
         * 判断组件是否可以横向移动
         *
         * @param scrollY 当前View滑动到的点 y坐标 = view.getScrollY();
         * @param diff    手指在屏幕上滑动的距离
         * @return
         */
        public boolean isVerticalMove(float scrollY, float diff) {
            return false;
        }
    }
```

3. 在自定义类的相应方法中调用以下方法
```java
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollerHelper.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scrollerHelper.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        scrollerHelper.computeScroll();
    }
```


4. Helper类提供的一些方法
```java
    /**
     * 只允许在Callback.onRelease()回调中使用，View在松开手后会自动滚动到响应位置
     * @param endx  结束点x
     * @param endy  结束点y
     */
    public void autoMove(int endx, int endy);
```