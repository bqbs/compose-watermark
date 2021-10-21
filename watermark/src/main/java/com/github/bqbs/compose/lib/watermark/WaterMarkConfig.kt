package com.github.bqbs.compose.lib.watermark

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

class WaterMarkConfig(
    var maskText: String,
    var mvTextColor: Color = Color.LightGray,
    var mvTextSize: Float = 36f,
    override var row: Int = 3,
    override var column: Int = 3,
    override var alignment: Alignment = Alignment.Center,
    override var degrees: Float = 0f
) : IWaterMarkConfig

interface IWaterMarkConfig {
    var row: Int
    var column: Int
    var alignment: Alignment
    var degrees: Float
}