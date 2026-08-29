package com.example.postvision.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SquatRecord(
    val dayOfMonth: Int,
    val totalReps: Int
)

@Composable
fun LineChart(
    data: List<SquatRecord>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFF160B4A) // Aquele azul/roxo escuro do seu layout
) {
    if (data.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()

    val ySteps = listOf(0, 30, 60, 90)
    val maxReps = 90f

    Canvas(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .padding(16.dp)
    ) {
        val leftMargin = 80f
        val bottomMargin = 60f

        val chartWidth = size.width - leftMargin
        val chartHeight = size.height - bottomMargin

        ySteps.forEach { step ->
            val yPos = chartHeight - ((step / maxReps) * chartHeight)

            drawLine(
                color = Color(0xFFEBEBEB),
                start = Offset(leftMargin, yPos),
                end = Offset(size.width, yPos),
                strokeWidth = 2f
            )

            val textLayoutResult = textMeasurer.measure(
                text = step.toString(),
                style = TextStyle(fontSize = 14.sp, color = Color(0xFF4A4A4A))
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = leftMargin - textLayoutResult.size.width - 24f, // Posiciona à esquerda da linha
                    y = yPos - (textLayoutResult.size.height / 2) // Centraliza com a linha
                )
            )
        }

        val spacePerPoint = chartWidth / (data.size - 1).coerceAtLeast(1)

        data.forEachIndexed { index, record ->
            val xPos = leftMargin + (index * spacePerPoint)

            val textLayoutResult = textMeasurer.measure(
                text = record.dayOfMonth.toString(),
                style = TextStyle(fontSize = 14.sp, color = Color(0xFF4A4A4A))
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = xPos - (textLayoutResult.size.width / 2), // Centraliza o texto sob o ponto
                    y = chartHeight + 24f // Joga o texto para baixo da área do gráfico
                )
            )
        }

        val path = Path()
        data.forEachIndexed { index, record ->
            val xPos = leftMargin + (index * spacePerPoint)
            val yPos = chartHeight - ((record.totalReps / maxReps) * chartHeight)

            if (index == 0) {
                path.moveTo(xPos, yPos)
            } else {
                path.lineTo(xPos, yPos)
            }
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
        )

        data.forEachIndexed { index, record ->
            val xPos = leftMargin + (index * spacePerPoint)
            val yPos = chartHeight - ((record.totalReps / maxReps) * chartHeight)

            drawCircle(
                color = lineColor,
                radius = 5.dp.toPx(),
                center = Offset(xPos, yPos)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLineChart(){
    val mockData = listOf(
        SquatRecord(1, 15),
        SquatRecord(3, 30),
        SquatRecord(5, 25),
        SquatRecord(7, 45),
        SquatRecord(9, 40),
        SquatRecord(12, 55),
        SquatRecord(15, 60),
        SquatRecord(18, 80),
        SquatRecord(21, 85)
    )

    LineChart(data = mockData)
}
