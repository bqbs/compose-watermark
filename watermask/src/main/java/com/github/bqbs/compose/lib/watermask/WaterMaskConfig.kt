package com.github.bqbs.compose.lib.watermask

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

class WaterMaskConfig(
    var maskText: String,
    var mvTextColor: Color = Color.LightGray,
    var mvTextSize: Float = 36f,
    override var row: Int = 3,
    override var column: Int = 3,
    override var alignment: Alignment = Alignment.Center,
    override var degrees: Float = 0f
) : IWaterMaskConfig

interface IWaterMaskConfig {
    var row: Int
    var column: Int
    var alignment: Alignment
    var degrees: Float
}