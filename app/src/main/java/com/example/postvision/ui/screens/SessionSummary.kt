package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postvision.MainViewModel
import com.example.postvision.R
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.components.DonutChart
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperSessionSummary(
    viewModel: MainViewModel,
    onNavigateToChooseExercise: () -> Unit,
    onBottomTabClick: (String) -> Unit
){
    // Informações da gráfico de pizza
    val myData = listOf(75f, 25f)

    val myColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
    )

    val myPercentual = "75%"
    //

    PostVisionTheme(

    ) {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedRoute = "home",
                    onNavigate = { route ->
                        if (route == "stats") {
                            onBottomTabClick(NavRoutes.STATISTICS) // Usa a rota de estatísticas
                        } else if (route == "profile") {
                            // Se tiver uma rota de perfil, coloque aqui
                            onBottomTabClick(NavRoutes.PROFILE)
                        }
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.onBackground
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.onBackground
            )
            {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 22.dp)
                ){
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    )
                    {
                        IconButton(onClick = {},
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
                        IconButton(onClick = {},
                            modifier = Modifier
                                .width(21.dp)
                                .fillMaxHeight(),
                        ){
                            Image(
                                painter = painterResource(R.drawable.notification_icon),
                                contentDescription = "Imagem from profile"
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 31.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 22.dp, top = 8.dp, end = 22.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ){
                                Image(
                                    painterResource(R.drawable.checkmark_graph),
                                    contentDescription = "checkmark icon"
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                ){
                                    Text(
                                        text = "Sessão Finalizada!",
                                        fontSize = 24.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "Você concluiu seu treino com sucesso.",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(75.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                ),

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.3f)
                                        .background(Color(0xFFF1F1FB)),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        modifier = Modifier
                                            .padding(start = 5.dp),
                                        text = "Agachamento",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Image(
                                            painterResource(R.drawable.bag_graph),
                                            contentDescription = "Symbol from date",
                                            modifier = Modifier
                                                .padding(end = 3.dp)
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(end = 5.dp),
                                            text = "24/02/2026",
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Image(
                                            painterResource(R.drawable.clock_graph),
                                            contentDescription = "Image from time",
                                            modifier = Modifier
                                                .padding(start = 5.dp),
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(start = 7.dp),
                                            text = "Duração",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.surface,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }

                                    Text(
                                        modifier = Modifier
                                            .padding(end = 5.dp),
                                        text = "5:30",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 13.dp)
                    ){
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(47.5f),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painterResource(R.drawable.weight_graph),
                                    contentDescription = "Symbol for weight",
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp),
                                    text = "Agachemento",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 18.dp, end = 10.dp)
                                    .fillMaxWidth()
                            ){
                                HorizontalDivider(thickness = 1.dp)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Repetições:",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Text(
                                        text = "48",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                HorizontalDivider(thickness = 1.dp)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Duração:",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Text(
                                        text = "5:30",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                HorizontalDivider(thickness = 1.dp)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Média:",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Text(
                                        text = "6,8seg",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                HorizontalDivider(thickness = 1.dp)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Streak:",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                    Text(
                                        text = "7",
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                HorizontalDivider(thickness = 1.dp)
                            }
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(5f)
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(47.5f),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painterResource(R.drawable.chart_graph),
                                    contentDescription = "Symbol for chart",
                                    modifier = Modifier
                                        .padding(start = 13.dp),
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp),
                                    text = "Gráfico",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 19.dp),
                                horizontalArrangement = Arrangement.Center
                            ){
                                DonutChart(
                                    data = myData,
                                    colors = myColors,
                                    percentual = myPercentual,
                                    modifier = Modifier.size(65.dp)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.SpaceAround
                            ){
                                Text(
                                    text = "48",
                                    fontSize = 32.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.Bold
                                )

                                Row(){
                                    Image(
                                        painterResource(R.drawable.right_background_graph),
                                        contentDescription = "symbol from right",
                                        modifier = Modifier
                                            .padding(end = 4.dp)
                                    )
                                    Text(
                                        text = "36",
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Row(){
                                    Image(
                                        painterResource(R.drawable.wrong_background_graph),
                                        contentDescription = "symbol from right",
                                        modifier = Modifier
                                            .padding(end = 4.dp)
                                    )
                                    Text(
                                        text = "12",
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.surface,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(top = 13.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 22.dp, end = 22.dp, top = 16.dp),
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painterResource(R.drawable.eye_graph),
                                    contentDescription = "Imagem from graph eye",
                                    modifier = Modifier
                                        .size(27.dp),
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 9.dp),
                                    text = "Ótimo trabalho!",
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 5.dp)
                            ){
                                Text(
                                    text = "Durante a sessão, foram observados pontos a melhorar no agachamento. Evite inclinar o tronco para frente, mantendo a coluna alinhada. Controle o avanço dos joelhos, alinhando-os aos pés, e busque maior profundidade, descendo até a linha das coxas.",
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.surface,
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 15.sp,
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .offset(y = 23.dp)
                    ){
                        Button(
                            modifier = Modifier
                                .weight(47.5f)
                                .fillMaxHeight(),
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ){
                                Image(
                                    painterResource(R.drawable.plus_button_graph),
                                    contentDescription = "Symbol from statistics button",
                                    modifier = Modifier
                                        .size(20.dp),
                                )
                                Text(
                                    text = "Nova Sessão",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.background,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .weight(5f)
                        )
                        Button(
                            modifier = Modifier
                                .weight(47.5f)
                                .fillMaxHeight(),
                            onClick = {},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ){
                                Image(
                                    painterResource(R.drawable.statistic_button_graph),
                                    contentDescription = "Symbol from statistics button",
                                    modifier = Modifier
                                        .size(20.dp),
                                )
                                Text(
                                    text = "Ver Estatísticas",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.background,
                                    fontWeight = FontWeight.Medium,
                                )
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
fun PreviewSessionSummary(){
    val fakeViewModel: MainViewModel = viewModel()
    WrapperSessionSummary(
        viewModel = fakeViewModel,
        onNavigateToChooseExercise = {},
        onBottomTabClick = {_ ->}
    )
}