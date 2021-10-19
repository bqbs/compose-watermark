package com.github.bqbs.compose.watermask

import androidx.compose.ui.graphics.Color

data class WaterMaskConfig(
    var mvColor: Color = Color.LightGray,
    var mvTextSize: Float = 24f,
    var mvCount: Int = 3,
    var mvOrientation: Orientation = Orientation.BOTTOM_RIGHT,
)