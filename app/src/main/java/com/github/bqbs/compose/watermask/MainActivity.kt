package com.github.bqbs.compose.watermask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import com.github.bqbs.compose.lib.watermask.WaterMaskConfig
import com.github.bqbs.compose.lib.watermask.waterMask
import com.github.bqbs.compose.watermask.ui.theme.WaterMaskInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMaskInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color(0xffff3d00))
                                .fillMaxWidth()
                                .weight(1f)
                                .waterMask(
                                    true,
                                    config = WaterMaskConfig(
                                        maskText = "@一窝鸡尼斯",
                                        mvTextColor = Color(0xffeeeeee),
                                        row = 3,
                                        column = 3,
                                        alignment = Alignment.TopStart
                                    )
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Android")
                        }
                        Row(
                            modifier = Modifier
                                .background(Color(0xff212121))
                                .fillMaxWidth()
                                .weight(1f)
                                .waterMask(
                                    true,
                                    config = WaterMaskConfig(
                                        maskText = "@一窝鸡尼斯",
                                        mvTextColor = Color(0xffeeeeee),
                                        row = 1,
                                        column = 1,
                                        alignment = Alignment.BottomStart

                                    )
                                )
                        ) {
                            Text("Android")
                        }
                        Row(
                            modifier = Modifier
                                .background(Color(0xff64dd17))
                                .fillMaxWidth()
                                .weight(1f)
                                .waterMask(
                                    true,
                                    config = WaterMaskConfig(
                                        maskText = "@一窝鸡尼斯",
                                        mvTextColor = Color(0xffeeeeee),
                                        row = 3,
                                        column = 3,
                                        alignment = Alignment.TopStart

                                    )
                                )
                        ) {
                            Text("Android")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(Color(0xff00e5ff))
                                .waterMask(
                                    true,
                                    config = WaterMaskConfig(
                                        maskText = "@一窝鸡尼斯",
                                        row = 3,
                                        column = 3,
                                        alignment = Alignment.TopStart
                                    )
                                )
                        ) {
                            Button(onClick = {
                                Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Text(text = "Click")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WaterMaskInComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Text("Android")
        }

    }
}