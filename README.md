UILib
---

`UILib`这个仓库将存放平时我所书写的一些UI控件,并定期发布版本，便于多项目重复利用

现有控件
---
* MaterialImageView:多模式图形显示控件，支持圆形边框，圆角框
* ColorButton:颜色修改按钮

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