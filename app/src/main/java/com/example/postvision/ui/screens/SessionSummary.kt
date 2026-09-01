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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.R
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperSessionSummary(){
    PostVisionTheme() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
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
                    ) { }
                }
            }
        }
    }
}

    @Preview
@Composable
fun PreviewSessionSummary(){
    WrapperSessionSummary()
}