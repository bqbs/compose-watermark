package com.github.bqbs.compose.watermark

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.bqbs.compose.lib.watermark.WaterMarkConfig
import com.github.bqbs.compose.lib.watermark.waterMark
import com.github.bqbs.compose.watermark.ui.theme.WaterMarkInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMarkInComposeTheme {
                // A surface container using the 'background' color from the theme
                var degrees = remember {
                    mutableStateOf<Float>(0f)
                }
                var expanded by remember { mutableStateOf(false) }
                var alignment by remember { mutableStateOf(Alignment.Center) }
                val alignments = listOf(
                    "Alignment.TopStart" to Alignment.TopStart,
                    "Alignment.TopCenter" to Alignment.TopCenter,
                    "Alignment.TopEnd" to Alignment.TopEnd,
                    "Alignment.CenterStart" to Alignment.CenterStart,
                    "Alignment.Center" to Alignment.Center,
                    "Alignment.CenterEnd" to Alignment.CenterEnd,
                    "Alignment.BottomStart" to Alignment.BottomStart,
                    "Alignment.BottomCenter" to Alignment.BottomCenter,
                    "Alignment.BottomEnd" to Alignment.BottomEnd,
                )

                Surface(color = MaterialTheme.colors.background) {
                    Column {

                        Button(onClick = { expanded = !expanded }) {
                            Text("Change Alignment")
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null,
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.wrapContentSize()
                        ) {
                            alignments.forEach { label ->
                                DropdownMenuItem(
                                    onClick = {
                                        expanded = false
                                        alignment = label.second
                                    },
                                ) {
                                    Text(text = label.first)
                                }
                            }
                        }

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
                                                alignment = alignment,
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
                                                alignment = alignment,
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
                                                alignment = alignment,
                                                degrees = degrees.value,
                                                paddingH = 20f.dp.value
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
                                                alignment = alignment,
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
                                                alignment = alignment,
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
                                                alignment = alignment,
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

fun Alignment.getName(): String = this.toString()
