package com.example.postvision.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme

data class DayPerformance(
    val dayLabel: String,
    val correctReps: Int,
    val totalReps: Int
)

@Composable
fun BarChart(
    weeklyData: List<DayPerformance>
){
    PostVisionTheme {
        val context = LocalContext.current

        val barGraphHeight by remember { mutableStateOf(200.dp) }
        val barGraphWidth by remember { mutableStateOf(16.dp) }

        val maxReps = weeklyData.maxOfOrNull { it.totalReps }?.coerceAtLeast(1) ?: 1

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barGraphHeight)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            weeklyData.forEach { dayData ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.weight(1f) // Ocupa todo o espaço vertical disponível acima do texto
                    ) {
                        val heightFractionCorrect = dayData.correctReps.toFloat() / maxReps
                        val heightFractionTotal = dayData.totalReps.toFloat() / maxReps

                        Box(
                            modifier = Modifier
                                .width(barGraphWidth)
                                .fillMaxHeight(heightFractionCorrect)
                                .background(MaterialTheme.colorScheme.primary)
                                .clickable {
                                    Toast.makeText(context, "Corretos: ${dayData.correctReps}", Toast.LENGTH_SHORT).show()
                                }
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Box(
                            modifier = Modifier
                                .width(barGraphWidth)
                                .fillMaxHeight(heightFractionTotal)
                                .background(MaterialTheme.colorScheme.secondary)
                                .clickable {
                                    Toast.makeText(context, "Total: ${dayData.totalReps}", Toast.LENGTH_SHORT).show()
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = dayData.dayLabel,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarChartPreview(){
    // Simulando como os dados chegarão no preview
    BarChart(
        weeklyData = listOf(
            DayPerformance("Dom", 4, 10),
            DayPerformance("Seg", 16, 18),
            DayPerformance("Ter", 15, 25),
            DayPerformance("Qua", 1, 4),
            DayPerformance("Qui", 14, 23),
            DayPerformance("Sex", 28, 29),
            DayPerformance("Sab", 8, 10)
        )
    )
}