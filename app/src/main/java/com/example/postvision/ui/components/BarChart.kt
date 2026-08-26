package com.example.postvision.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postvision.ui.theme.PostVisionTheme

@Composable
fun BarChart(
    barValue: List<Float>,
    xAxisScale: List<String>,
    total_amount: Int
){
    PostVisionTheme() {
        val context = LocalContext.current
        // BarGraph Dimensions
        val barGraphHeight by remember { mutableStateOf(200.dp) }
        val barGraphWidth by remember { mutableStateOf(20.dp) }
        // Scale Dimensions
        val scaleYAxisWidth by remember { mutableStateOf(50.dp) }
        val scaleLineWidth by remember { mutableStateOf(2.dp) }

        Column(
            modifier = Modifier
                .padding(50.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barGraphHeight),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                // scale Y-Axis
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(scaleYAxisWidth),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = total_amount.toString())
                        Spacer(modifier = Modifier.fillMaxHeight())
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = (total_amount / 2).toString())
                        Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                    }

                }

                // graph
                barValue.forEach {
                    Box(
                        modifier = Modifier
                            .padding(start = barGraphWidth, bottom = 5.dp)
                            .width(barGraphWidth)
                            .fillMaxHeight(it)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable {
                                Toast
                                    .makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                    )
                }

            }

            // X-Axis Line
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(scaleLineWidth)
                    .background(Color.Black)
            )

            // Scale X-Axis
            Row(
                modifier = Modifier
                    .padding(start = scaleYAxisWidth + barGraphWidth + scaleLineWidth)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(barGraphWidth)
            ) {

                xAxisScale.forEach {
                    Text(
                        modifier = Modifier.width(barGraphWidth),
                        text = it,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }
    }
}

@Preview
@Composable
fun BarChartPreview(){
    BarChart(
        barValue = listOf(0.5f,0.6f,0.2f,0.7f,0.8f),
        xAxisScale = listOf("A", "B", "C", "D", "E"),
        total_amount = 1000
    )
}

