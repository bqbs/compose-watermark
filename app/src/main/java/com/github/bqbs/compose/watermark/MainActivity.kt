package com.github.bqbs.compose.watermark

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.bqbs.compose.lib.watermark.WaterMarkConfig
import com.github.bqbs.compose.lib.watermark.waterMark
import com.github.bqbs.compose.watermark.ui.theme.WaterMarkInComposeTheme
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMarkInComposeTheme {
                // A surface container using the 'background' color from the theme
                var degrees = remember {
                    mutableStateOf<Float>(0f)
                }
                Surface(color = MaterialTheme.colors.background) {
                    Column {

                        Text(text = "Slide to change the degrees(curr=${degrees.value.toInt()})")

                        Slider(
                            value = degrees.value / 90 * 0.5f + 0.5f,
                            onValueChange = {

                                degrees.value =
                                    ((it - 0.5f) / 0.5f * 90)
                            })
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xff64dd17))
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .waterMark(
                                            visible = false,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 3,
                                                column = 3,
                                                alignment = Alignment.TopStart,
                                                degrees = degrees.value

                                            )
                                        )
                                ) {
                                    Text("Watermark not visible")
                                }

                                Row(
                                    modifier = Modifier
                                        .background(Color(0xffff3d00))
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 3,
                                                column = 3,
                                                alignment = Alignment.TopStart,
                                                degrees = degrees.value
                                            )
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("Android(TopStart)")
                                }
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xff212121))
                                        .fillMaxWidth()
                                        .height(200.dp)

                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 1,
                                                column = 1,
                                                alignment = Alignment.Center,
                                                degrees = degrees.value

                                            )
                                        )
                                ) {
                                    Text(text = "Android(Alignment.Center)")
                                }
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xff64dd17))
                                        .fillMaxWidth()
                                        .height(200.dp)

                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 3,
                                                column = 3,
                                                alignment = Alignment.TopEnd,
                                                degrees = degrees.value

                                            )
                                        )
                                ) {
                                    Text("Android(TopEnd)")
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .background(Color(0xffff3d00))
                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                row = 3,
                                                column = 3,
                                                alignment = Alignment.Center,
                                                degrees = degrees.value
                                            )
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        Text(text = "Android(Alignment.Center)")

                                        Button(onClick = {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Click",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }) {
                                            Text(text = "Click")
                                        }
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xff212121))
                                        .fillMaxWidth()
                                        .height(200.dp)

                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                maskText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 2,
                                                column = 2,
                                                alignment = Alignment.BottomEnd,
                                                degrees = degrees.value

                                            )
                                        )
                                ) {
                                    Text("Android(Alignment.BottomEnd)")
                                }
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
    WaterMarkInComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Text("Android")
        }

    }
}