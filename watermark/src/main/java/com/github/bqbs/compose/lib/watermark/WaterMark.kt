package com.github.bqbs.compose.lib.watermark

import android.graphics.RectF
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import android.graphics.Paint as NPaint


fun Modifier.waterMark(
    visible: Boolean,
    config: WaterMarkConfig
): Modifier = composed {
    WaterMarkModifier(visible = visible, config = config)
}

internal class WaterMarkModifier(
    private var visible: Boolean,
    private var config: WaterMarkConfig
) : DrawModifier, LayoutModifier {
    private val cleanPaint: Paint = Paint()
    private val paint = NPaint().apply {
        isAntiAlias = true
        color = config.mvTextColor.toArgb()
        textSize = config.mvTextSize
        textAlign = NPaint.Align.CENTER
        style = NPaint.Style.FILL_AND_STROKE
    }
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    override fun ContentDrawScope.draw() {
        drawIntoCanvas {
            it.withSaveLayer(
                Rect(
                    0f,
                    0f,
                    size.width,
                    size.height
                ), paint = cleanPaint
            ) {
                drawContent()
                if (visible) {

                    if (config.row * config.column == 0) {
                        throw IllegalArgumentException("WaterMarkConfig.row or WaterMarkConfig.column can not be Zero.")
                    }

                    val textWidth = config.maskText.length * config.mvTextSize

                    when (config.alignment) {
                        Alignment.TopStart -> {
                        }
                        Alignment.BottomStart -> {
                        }
                    }
                    it.rotate(config.degrees, mWidth / 2f, mHeight / 2f)

                    val textHeight: Float = paint.descent() - paint.ascent()

                    for (i in 0 until config.row) {
                        val top: Float = when (config.alignment) {
                            Alignment.TopCenter, Alignment.TopStart, Alignment.TopEnd, Alignment.Top -> {
                                // TOP
                                (mHeight / config.row * i).toFloat()
                            }
                            Alignment.BottomEnd, Alignment.BottomCenter, Alignment.BottomStart, Alignment.Bottom -> {
                                // Bottom
                                (mHeight / config.row * i) + (mHeight / config.row - textHeight)
                            }
                            Alignment.CenterStart, Alignment.CenterEnd, Alignment.Center, Alignment.CenterVertically -> {
                                // CenterVertically
                                (mHeight / config.row * i) + (mHeight / config.row - textHeight) / 2
                            }
                            else -> (mHeight / config.row * i).toFloat()

                        }

                        for (j in 0 until config.column) {
                            val left = when (config.alignment) {
                                Alignment.BottomStart, Alignment.TopStart, Alignment.CenterStart, Alignment.Start -> {
                                    mWidth / config.column * j
                                }
                                Alignment.CenterEnd, Alignment.TopEnd, Alignment.BottomEnd, Alignment.End -> {
                                    (mWidth / config.column - config.maskText.length * config.mvTextSize) + (mWidth / config.column * j)

                                }
                                Alignment.TopCenter, Alignment.BottomCenter, Alignment.Center, Alignment.CenterHorizontally -> {
                                    ((mWidth / config.column - config.maskText.length * config.mvTextSize) / 2f) + (mWidth / config.column * j)
                                }
                                else -> {
                                    mWidth / config.column * j
                                }
                            }

                            val textOffset: Float = textHeight / 2 - paint.descent()
                            val bounds = RectF(
                                left.toFloat(),
                                top,
                                left.toFloat() + textWidth,
                                top + textHeight
                            )
                            it.nativeCanvas.drawText(
                                config.maskText,
                                bounds.centerX(),
                                bounds.centerY() + textOffset,
                                paint
                            )
                        }
                    }
                }
            }
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val placeable = measurable.measure(constraints)
        val size = Size(width = placeable.width.toFloat(), height = placeable.height.toFloat())
        updateSize(size)
        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }

    private fun updateSize(size: Size) {
        mHeight = size.height.toInt()
        mWidth = size.width.toInt()
    }
}
