package com.github.bqbs.compose.lib.watermark

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

/**
 * Configure for watermark
 */
data class WaterMarkConfig(
    var maskText: String,
    var mvTextColor: Color = Color.LightGray,
    var mvTextSize: Float = 36f,
    override var row: Int = 3,
    override var column: Int = 3,
    override var alignment: Alignment = Alignment.Center,
    override var degrees: Float = 0f,
    override var paddingVertical: Float = 0f,
    override var paddingHorizontal: Float = 0f
) : IWaterMarkConfig

interface IWaterMarkConfig {
    var row: Int
    var column: Int

    /**
     * Layout direction for watermark
     *  Check 2D Alignments.[Alignment.Companion]
     */
    var alignment: Alignment

    /**
     * Rotate degrees.
     */
    var degrees: Float

    /**
     * Padding for top or bottom
     */
    var paddingVertical: Float

    /**
     * Padding for start or end
     */
    var paddingHorizontal: Float
}