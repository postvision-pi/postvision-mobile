package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.R
import com.example.postvision.ui.components.BarChart
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.components.DayPerformance
import com.example.postvision.ui.components.DonutChart
import com.example.postvision.ui.components.LineChart
import com.example.postvision.ui.components.SquatRecord
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperStatistic(
    onBottomTabClick: (String) -> Unit
) {
    // Informações da gráfico de pizza
    val myData = listOf(75f, 25f)

    val myColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
    )

    val myPercentual = "75%"
    //

    // Informações do gráfico de barras
    val weeklyData = listOf(
        DayPerformance("Dom", 2, 5),
        DayPerformance("Seg", 8, 9),
        DayPerformance("Ter", 8, 13),
        DayPerformance("Qua", 1, 2),
        DayPerformance("Qui", 7, 12),
        DayPerformance("Sex", 14, 15),
        DayPerformance("Sab", 4, 5)
    )
    //

    // Informações do gráfico de linha
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
    //

    val scrollState = rememberScrollState()

    PostVisionTheme() {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedRoute = "stats",
                    onNavigate = { route ->
                        if (route == "home") {
                            onBottomTabClick(NavRoutes.HOME) // Usa a rota de estatísticas
                        } else if (route == "profile") {
                            onBottomTabClick(NavRoutes.PROFILE)
                        }
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.onBackground
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.onBackground
            ){
                Column(
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 24.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                    )
                {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    )
                    {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .width(52.dp)
                                .height(52.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            )
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profile_graph),
                                contentDescription = "Imagem from profile"
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .width(21.dp)
                                .fillMaxHeight(),
                        ) {
                            Image(
                                painter = painterResource(R.drawable.notification_icon),
                                contentDescription = "Imagem from profile"
                            )
                        }
                    }

                    // Corpo
                    Column(
                        modifier = Modifier
                            .padding(top = 9.dp)
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        // Escolha
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 6.dp),
                            Arrangement.SpaceAround
                        ) {
                            Card(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(79.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    Arrangement.Center,
                                    Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Todos",
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontSize = 13.sp,
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(134.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    Arrangement.Center,
                                    Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Agachamento",
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontSize = 13.sp,
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(79.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    Arrangement.Center,
                                    Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Flexão",
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontSize = 13.sp,
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .verticalScroll(scrollState)
                                .padding(top = 5.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(143.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .width(265.dp)
                                        .height(95.63.dp)
                                        .align(Alignment.CenterHorizontally)
                                        .offset(y = 23.69.dp),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .width(72.dp)
                                            .height(95.63.dp),
                                        painter = painterResource(R.drawable.streak_graph),
                                        contentDescription = "Image from streak graph"
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(start = 30.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            "Streak",
                                            fontFamily = Raleway,
                                            fontSize = 28.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Text(
                                            "Realize as análises para aumentar seu streak",
                                            fontFamily = Raleway,
                                            fontSize = 13.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                        )
                                    }
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceAround
                                )
                                {
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 29.dp)
                                    ) {
                                        Text(
                                            text = "Último treino",
                                            fontFamily = Raleway,
                                            fontSize = 24.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Agachamento",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        DonutChart(
                                            data = myData,
                                            colors = myColors,
                                            percentual = myPercentual,
                                            modifier = Modifier.size(100.dp)
                                        )
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 15.dp)
                                                .width(189.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                Card(
                                                    modifier = Modifier
                                                        .width(19.dp)
                                                        .height(19.dp),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = MaterialTheme.colorScheme.primary
                                                    )

                                                ){}
                                                Text(
                                                    modifier = Modifier
                                                        .padding(start = 2.dp),
                                                    text = "Corretos",
                                                    color = MaterialTheme.colorScheme.surface,
                                                    fontFamily = Raleway,
                                                    fontSize = 13.sp
                                                )
                                            }

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Card(
                                                    modifier = Modifier
                                                        .width(19.dp)
                                                        .height(19.dp),
                                                    colors = CardDefaults.cardColors(
                                                        containerColor = MaterialTheme.colorScheme.secondary
                                                    )

                                                ){}
                                                Text(
                                                    modifier = Modifier
                                                        .padding(start = 2.dp),
                                                    text = "Incorretos",
                                                    color = MaterialTheme.colorScheme.surface,
                                                    fontFamily = Raleway,
                                                    fontSize = 13.sp
                                                )
                                            }
                                        }
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceAround
                                )
                                {
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 29.dp)
                                    ) {
                                        Text(
                                            text = "Desempenho Semanal",
                                            fontFamily = Raleway,
                                            fontSize = 24.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Agachamento",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        BarChart(
                                            weeklyData
                                        )
                                    }

                                }
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceAround
                                )
                                {
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 29.dp)
                                    ) {
                                        Text(
                                            text = "Comparativo Mensal",
                                            fontFamily = Raleway,
                                            fontSize = 24.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Agachamento",
                                            fontFamily = Raleway,
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        LineChart(
                                            data = mockData
                                        )
                                    }

                                }
                            }
                        }

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewStatistic(){
    WrapperStatistic(
        onBottomTabClick = {}
    )
}
