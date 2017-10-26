UILib
---

`UILib`这个仓库将存放平时我所书写的一些UI控件,并定期发布版本，便于多项目重复利用

现有控件
---
* MaterialImageView:多模式图形显示控件，支持圆形边框，圆角框
* ColorButton:颜色修改按钮
* LargeImage:可放大缩小图片的控件
* GalleryView:画廊View,控件式的画廊效果，可以自定义为画廊Activity，之后将提供默认的GalleryActivity
* GifImageView:可以使用动画的ImageView，这里暂时实现了帧动画
* BottomNavigationBar:底部导航栏

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
	compile 'com.github.Aspsine:SwipeToLoadLayout:1.0.4'
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


### ColorButton
---

*资源文件中使用
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
*java代码中使用(暂无)

```java
设置颜色：
imageView.setColor(@ColorRes int normalColor,@ColorRes int pressColor);
```


### LargeImageView
---


### GalleryView
---


*已知的一些bug
未滑动完全



### GifImageView
---
gif推荐还是使用android-gif-drawable，其他控件包括Glide的gif都容易导致oom，而android-gif=drawable并没有此问题，底层解码使用C实现，极大的提高了解码效率，同时很大程度上避免了OOM现象出现。



### BottomNavigationBar：底部导航栏
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

