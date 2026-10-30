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

    // Limites do Eixo Y e X
    val ySteps = listOf(0, 30, 60, 90)
    val maxReps = 90f
    val xLabels = listOf(1, 3, 5, 7, 9, 12, 15, 18, 21, 24, 27, 30)
    val maxDays = 30f

    // === O SEGREDO PARA LIMPAR O GRÁFICO ===
    // Agrupa os dias próximos para os 12 rótulos do eixo X.
    // Garante que desenhamos no máximo 12 bolinhas perfeitamente alinhadas!
    val cleanData = data.groupBy { record ->
        xLabels.minByOrNull { kotlin.math.abs(it - record.dayOfMonth) } ?: record.dayOfMonth
    }.map { (nearestLabel, records) ->
        SquatRecord(dayOfMonth = nearestLabel, totalReps = records.maxOf { it.totalReps })
    }.sortedBy { it.dayOfMonth }

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

        // 1. DESENHA LINHAS DE FUNDO E TEXTOS DO EIXO Y
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
                    x = leftMargin - textLayoutResult.size.width - 24f,
                    y = yPos - (textLayoutResult.size.height / 2)
                )
            )
        }

        val spacePerDay = chartWidth / (maxDays - 1)

        // 2. DESENHA OS RÓTULOS DO EIXO X (1, 3, 5... 30) FIXOS
        xLabels.forEach { day ->
            val xPos = leftMargin + ((day - 1) * spacePerDay)
            val textLayoutResult = textMeasurer.measure(
                text = day.toString(),
                style = TextStyle(fontSize = 12.sp, color = Color(0xFF4A4A4A))
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = xPos - (textLayoutResult.size.width / 2),
                    y = chartHeight + 24f
                )
            )
        }

        // 3. DESENHA A LINHA DO GRÁFICO (COM OS DADOS LIMPOS)
        val path = Path()
        cleanData.forEachIndexed { index, record ->
            val safeDay = record.dayOfMonth.coerceIn(1, 30)
            val safeReps = record.totalReps.toFloat().coerceAtMost(maxReps)

            val xPos = leftMargin + ((safeDay - 1) * spacePerDay)
            val yPos = chartHeight - ((safeReps / maxReps) * chartHeight)

            if (index == 0) {
                path.moveTo(xPos, yPos)
            } else {
                path.lineTo(xPos, yPos)
            }
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round) // Linha ligeiramente mais fina
        )

        // 4. DESENHA AS BOLINHAS DOS PONTOS
        cleanData.forEach { record ->
            val safeDay = record.dayOfMonth.coerceIn(1, 30)
            val safeReps = record.totalReps.toFloat().coerceAtMost(maxReps)

            val xPos = leftMargin + ((safeDay - 1) * spacePerDay)
            val yPos = chartHeight - ((safeReps / maxReps) * chartHeight)

            drawCircle(
                color = lineColor,
                radius = 4.dp.toPx(), // Reduzido para ficar mais espaçado e limpo
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
        SquatRecord(21, 85),
        SquatRecord(30, 90)
    )

    LineChart(data = mockData)
}