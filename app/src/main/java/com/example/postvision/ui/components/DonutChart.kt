package com.example.postvision.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DonutChart(
    data: List<Float>,
    colors: List<Color>,
    percentual: String,
    modifier: Modifier = Modifier
) {
    val total = data.sum()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = modifier) {
            var startAngle = -90f

            for (i in data.indices) {
                val sweepAngle = (data[i] / total) * 360f

                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 65f)
                )

                startAngle += sweepAngle
            }
        }
        Text(
            text = percentual,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.surface,
        )
    }
}

@Preview
@Composable
fun PreviewDonutChart() {
    val myData = listOf(75f, 25f)

    val myColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
    )

    val myPercentual = "75%"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DonutChart(
            data = myData,
            colors = myColors,
            percentual = myPercentual,
            modifier = Modifier.size(75.dp)
        )
    }
}
