package com.github.bqbs.compose.watermask

import android.graphics.RectF
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


fun Modifier.waterMask(visible: Boolean, config: WaterMaskConfig = WaterMaskConfig()): Modifier =
    composed {
        WaterMaskModifier(visible = visible, "Water mask test", config = config)
    }

internal class WaterMaskModifier(
    private var visible: Boolean,
    private val mMaskText: String,
    private var config: WaterMaskConfig
) : DrawModifier, LayoutModifier {
    private val cleanPaint: Paint = Paint()
    private val paint = NPaint().apply {
        isAntiAlias = true
    }
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    override fun ContentDrawScope.draw() {
        drawIntoCanvas {
            it.withSaveLayer(Rect(0f, 0f, size.width, size.height), paint = cleanPaint) {

                drawContent()
                if (!visible) {
                    return
                }
                when (config.mvOrientation) {
                    Orientation.BOTTOM_RIGHT -> {
                        it?.rotate(15f, mWidth / 2f, mHeight / 2f)
                    }
                    Orientation.HORIZONTAL -> {
                        it?.rotate(0f, mWidth / 2f, mHeight / 2f)
                    }
                    Orientation.TOP_LEFT -> {
                        it?.rotate(-15f, mWidth / 2f, mHeight / 2f)
                    }
                }
                val textWidth = mMaskText.length * config.mvTextSize
                val left = (mWidth - mMaskText.length * config.mvTextSize) / 2
                for (i in 0 until config.mvCount) {
                    paint.reset()
                    paint.color = config.mvColor.toArgb()
                    paint.textSize = config.mvTextSize
                    paint.style = NPaint.Style.STROKE
                    paint.textAlign = NPaint.Align.CENTER
                    val textHeight: Float = paint.descent() - paint.ascent()
                    val top =
                        (mHeight / config.mvCount * i) + (mHeight / config.mvCount - textHeight) / 2
                    paint.textAlign = NPaint.Align.CENTER
                    val textOffset: Float = textHeight / 2 - paint.descent()
                    val bounds = RectF(left, top, left + textWidth, top + textHeight)
                    paint.style = NPaint.Style.FILL_AND_STROKE
                    it.nativeCanvas.drawText(
                        mMaskText,
                        bounds.centerX(),
                        bounds.centerY() + textOffset,
                        paint
                    )
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
        visible = true
    }
}


enum class Orientation(val orientation: Int) {
    BOTTOM_RIGHT(2), TOP_LEFT(1), HORIZONTAL(0)
}

data class WaterMaskConfig(
    var mvColor: Color = Color.LightGray,
    var mvTextSize: Float = 24f,
    var mvCount: Int = 3,
    var mvOrientation: Orientation = Orientation.BOTTOM_RIGHT,
)