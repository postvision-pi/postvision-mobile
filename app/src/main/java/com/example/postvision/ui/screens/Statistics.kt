package com.example.postvision.ui.screens

import ProfileIconButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.MainViewModel
import com.example.postvision.R
import com.example.postvision.ui.components.BarChart
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.components.DonutChart
import com.example.postvision.ui.components.FilterChipCard
import com.example.postvision.ui.components.LineChart
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperStatistic(
    viewModel: StatisticsViewModel,
    mainViewModel: MainViewModel,
    userId: String,
    token: String,
    onBottomTabClick: (String) -> Unit,
    onNavigateToProfile: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadUserStatistics(userId, token)
    }

    val correct = viewModel.lastTrainCorrect.toFloat()
    val incorrect = viewModel.lastTrainIncorrect.toFloat()
    val myData = if (correct == 0f && incorrect == 0f) listOf(1f, 0f) else listOf(correct, incorrect)
    val myColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary
    )
    val myPercentual = viewModel.lastTrainAccuracy

    val scrollState = rememberScrollState()

    PostVisionTheme {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedRoute = "stats",
                    onNavigate = { route ->
                        if (route == "home") {
                            onBottomTabClick(NavRoutes.HOME)
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
            ) {
                if (viewModel.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 22.dp, horizontal = 24.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Header
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            ProfileIconButton(
                                firstName = mainViewModel.loggedUser?.firstName ?: "U",
                                lastName = mainViewModel.loggedUser?.lastName ?: "",
                                modifier = Modifier.size(52.dp),
                                onClick = { onNavigateToProfile() },
                                fontSize = 20.sp,
                            )
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .width(21.dp)
                                    .fillMaxHeight()
                            ) {
                                Image(painter = painterResource(R.drawable.notification_icon), contentDescription = "Notification")
                            }
                        }

                        // === BOTÕES DE FILTRO ("Todos", "Agachamento", "Flexão") ===
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 6.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            FilterChipCard(
                                label = "Todos",
                                isSelected = viewModel.selectedFilter == "Todos",
                                onClick = { viewModel.applyFilter("Todos") }
                            )
                            FilterChipCard(
                                label = "Agachamento",
                                isSelected = viewModel.selectedFilter == "Agachamento",
                                onClick = { viewModel.applyFilter("Agachamento") }
                            )
                            FilterChipCard(
                                label = "Flexão",
                                isSelected = viewModel.selectedFilter == "Flexão",
                                onClick = { viewModel.applyFilter("Flexão") }
                            )
                        }

                        // Corpo Rolável
                        Column(
                            modifier = Modifier
                                .verticalScroll(scrollState)
                                .padding(top = 5.dp)
                        ) {
                            // Card 1: Streak
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(143.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
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
                                        modifier = Modifier.size(72.dp, 95.63.dp),
                                        painter = painterResource(R.drawable.streak_graph),
                                        contentDescription = "Streak"
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(start = 30.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text("Streak", fontFamily = Raleway, fontSize = 28.sp, color = MaterialTheme.colorScheme.surface, fontWeight = FontWeight.Bold)
                                        Text("Realize as análises para aumentar seu streak", fontFamily = Raleway, fontSize = 13.sp, color = MaterialTheme.colorScheme.surface)
                                    }
                                }
                            }

                            // Card 2: ÚLTIMO TREINO
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceAround
                                ) {
                                    Column(modifier = Modifier.padding(start = 29.dp)) {
                                        Text(text = "Último treino", fontFamily = Raleway, fontSize = 24.sp, color = MaterialTheme.colorScheme.surface, fontWeight = FontWeight.Bold)
                                        Text(text = viewModel.selectedFilter, fontFamily = Raleway, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Normal)
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
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
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Card(modifier = Modifier.size(19.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {}
                                                Text(modifier = Modifier.padding(start = 2.dp), text = "Corretos", color = MaterialTheme.colorScheme.surface, fontFamily = Raleway, fontSize = 13.sp)
                                            }
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Card(modifier = Modifier.size(19.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)) {}
                                                Text(modifier = Modifier.padding(start = 2.dp), text = "Incorretos", color = MaterialTheme.colorScheme.surface, fontFamily = Raleway, fontSize = 13.sp)
                                            }
                                        }
                                    }
                                }
                            }

                            // Card 3: DESEMPENHO SEMANAL (DINÂMICO)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {
                                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
                                    Column(modifier = Modifier.padding(start = 29.dp)) {
                                        Text(text = "Desempenho Semanal", fontFamily = Raleway, fontSize = 24.sp, color = MaterialTheme.colorScheme.surface, fontWeight = FontWeight.Bold)
                                        Text(text = viewModel.selectedFilter, fontFamily = Raleway, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Normal)
                                    }
                                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                        BarChart(viewModel.weeklyPerformanceList)
                                    }
                                }
                            }

                            // Card 4: COMPARATIVO MENSAL (DINÂMICO)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(270.dp)
                                    .padding(top = 13.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {
                                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
                                    Column(modifier = Modifier.padding(start = 29.dp)) {
                                        Text(text = "Comparativo Mensal", fontFamily = Raleway, fontSize = 24.sp, color = MaterialTheme.colorScheme.surface, fontWeight = FontWeight.Bold)
                                        Text(text = viewModel.selectedFilter, fontFamily = Raleway, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Normal)
                                    }
                                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                        LineChart(data = viewModel.monthlyRecordList)
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

/*
@Preview
@Composable
fun MobilePreviewStatistic(){
    WrapperStatistic(
        onBottomTabClick = {}
    )
}
*/