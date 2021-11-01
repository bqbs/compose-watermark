# Jetpack compose WaterMark

每天混迹掘金摸鱼区，突然有一天看见了这篇文章[【开源项目】简单易用的Compose版骨架屏,了解一下~](https://juejin.cn/post/7004447246854914085)，看完就想到了能不能用类似的实现做一个水印功能呢？由于之前有个项目中用到了水印功能（自定义 View & AspectJ 实现）。目前这个开源项目应该不会出现在业务中，纯粹为了实现想法，写有意思的代码，同时也算是知识储备吧。


### 预览


![](https://raw.githubusercontent.com/bqbs/images/master/wm_preview_small.png)

### Demo
[点击下载 demo](https://raw.githubusercontent.com/bqbs/compose-watermark/main/preview/preview.apk) 或者源代码自己构建一下

### 项目地址
[Github](https://github.com/bqbs/compose-watermark)

目前没有发布到 maven 仓库的想法。有需求的话，直接CV

### 使用

1. 在需要加水印的节点使用 `Modifier.waterMark`
``` java
modifier = Modifier.waterMark(
    visible = true,
    config = WaterMarkConfig(
        markText = "@一窝鸡尼斯",
        mvTextColor = Color(0xffeeeeee),
        row = 3,
        column = 3,
        alignment = alignmentPair.second
            ?: Alignment.Center,
        degrees = degrees
    )
)
```

2. 配置水印。通过改变 `WaterMarkConfig` 各个参数修改水印的细节。
``` java
config = WaterMarkConfig(
        markText = "@一窝鸡尼斯",
        mvTextColor = Color(0xffeeeeee),
        row = 3,
        column = 3,
        alignment = Alignment.Center,
        degrees = degrees
    )
```
- 可用属性

| 属性名            | 默认值           | 说明                             |
| ----------------- | ---------------- | -------------------------------- |
| markText          | -                | 水印的内容                       |
| mvTextColor       | Color.LightGray  | 水印文本的颜色                   |
| mvTextSize        | 36px             | 水印文本字体，单位：像素         |
| row               | 3                | 水印的行数                       |
| column            | 3                | 水印的列数                       |
| alignment         | Alignment.Center | 水印的对齐方式                   |
| degrees           | 0                | 水印的旋转角度。建议：15（右下） |
| paddingVertical   | 0                | 垂直边距                        |
| paddingHorizontal | 0                | 水平边距                         |

### 基本原理

- 在 `watermark` 里面就两个文件。动手就行👻👻
- 最重要的一点是 Modifier 链的原理。滚动到下方看 **大佬 RugerMc 的图解Compose Modifier实现原理 ，竟然如此简单！** 这篇文章。真得很受用）
- 基本的实现就是计算位置，绘制
- 这是唯一一段我觉得你们可以收藏起来的代码（如何在Jetpack Compose Canvas里面绘制文本）

``` java
// ...  省略初始化paint的代码

// 计算文本的宽度
val textWidth = config.markText.length * config.mvTextSize
// 计算文本高度
val textHeight: Float = paint.descent() - paint.ascent()
// 计算offset
val textOffset: Float = textHeight / 2 - paint.descent()

// 构建文本绘制区域
// left 和 top 为代码中计算得到的水印开始位置
val txtBound = RectF( left, top, left + textWidth, top + textHeight )
// 很遗憾 Compose 没有 drawText 的 API。所以这里调用 nativeCanvas 获取一个实现 Canvas 接口的原生实例，通过原生实例进行绘制。
it.nativeCanvas.drawText(
    config.markText,
    txtBound.centerX(), // 中心点 x 轴坐标
    txtBound.centerY() + textOffset,// // 中心点 y 轴坐标 加上 Offset。绘制的文本上下居中
    paint
)
```

### 总结

支持图标的版本可能要拖一段时间了～毕竟晚期患者，而且出新英雄了!


### 参考资料

[【开源项目】简单易用的Compose版骨架屏,了解一下~](https://juejin.cn/post/7004447246854914085)

[图解Compose Modifier实现原理 ，竟然如此简单！by RugerMc](https://juejin.cn/user/1714893871911502)

看我文章也就图一乐，真要学东西还得看👆🏻大佬的，强烈建议建议去点赞他们～

我的随意就好。

自由转载，留个言就行～