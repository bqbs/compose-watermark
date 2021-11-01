package com.github.bqbs.compose.watermark

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.bqbs.compose.lib.watermark.WaterMarkConfig
import com.github.bqbs.compose.lib.watermark.waterMark
import com.github.bqbs.compose.watermark.ui.theme.WaterMarkInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMarkInComposeTheme {
                // A surface container using the 'background' color from the theme
                var degrees by remember { mutableStateOf(0f) }
                var expanded by remember { mutableStateOf(false) }
                var alignmentPair by remember { mutableStateOf<Pair<String, Alignment?>>("Change Alignment" to null) }
                var isVisible by remember { mutableStateOf(true) }
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
                        Box(
                            modifier = Modifier.padding(20.dp),
                        ) {

                            Text(text = "WaterMarkConfig")
                            Button(
                                onClick = { expanded = !expanded }) {
                                Text(alignmentPair.first)
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = null,
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {
                                alignments.forEach { label ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            alignmentPair = label
                                        },
                                    ) {
                                        Text(text = label.first)
                                    }
                                }
                            }
                        }


                        Text(text = "Slide to change the degrees(curr=${degrees.toInt()})")
                        Slider(
                            value = degrees / 90 * 0.5f + 0.5f,
                            onValueChange = {

                                degrees =
                                    ((it - 0.5f) / 0.5f * 90)
                            })

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .background(Color(0xff64dd17))
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .waterMark(
                                            visible = isVisible,
                                            config = WaterMarkConfig(
                                                markText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 3,
                                                column = 3,
                                                alignment = alignmentPair.second
                                                    ?: Alignment.Center,
                                                degrees = degrees
                                            )
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Row(horizontalArrangement = Arrangement.Center) {

                                        Text(
                                            text = "人法地 地法天 天法道 道法孜然\uD83C\uDF57\uD83C\uDF57",
                                        )
                                    }

                                    Row(
                                        modifier = Modifier.padding(20.dp),
                                    ) {
                                        Checkbox(checked = isVisible, onCheckedChange = {
                                            isVisible = it
                                        })
                                        Text(text = "Show WaterMark")
                                    }


                                }

                                var size by remember { mutableStateOf(IntSize.Zero) }
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xff212121))
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                markText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 1,
                                                column = 1,
                                                alignment = alignmentPair.second
                                                    ?: Alignment.Center,
                                                degrees = degrees,
                                                paddingHorizontal = 40f.dp.value,
                                                paddingVertical = 40f.dp.value
                                            )
                                        )
                                        .onSizeChanged { size = it }
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = rememberImagePainter("https://picsum.photos/${size.width}/${size.height}"),
                                        contentDescription = "",
                                        alignment = Alignment.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .background(Color(0xffff3d00))
                                        .waterMark(
                                            true,
                                            config = WaterMarkConfig(
                                                markText = "@一窝鸡尼斯",
                                                row = 3,
                                                column = 3,
                                                alignment = alignmentPair.second
                                                    ?: Alignment.Center,
                                                degrees = degrees
                                            )
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        Text(text = "Android")

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
                                                markText = "@一窝鸡尼斯",
                                                mvTextColor = Color(0xffeeeeee),
                                                row = 2,
                                                column = 2,
                                                alignment = alignmentPair.second
                                                    ?: Alignment.Center,
                                                degrees = degrees

                                            )
                                        )
                                ) {
                                    Text("Android")
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