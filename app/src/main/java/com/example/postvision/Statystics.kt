package com.example.postvision

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperStatystics() {
    val scrollState = rememberScrollState()

    PostVisionTheme() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground
        )
        {
            Column(modifier = Modifier
                .padding(vertical = 22.dp, horizontal = 24.dp)
                .fillMaxHeight()
            )
            {
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
                    ) {
                        Image(
                            painter = painterResource(R.drawable.imagem),
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
                            .padding(top = 22.dp),
                        Arrangement.SpaceAround
                    ){
                        Card(
                            modifier = Modifier
                                .height(30.dp)
                                .width(79.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )

                        ){
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

                        ){
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

                        ){
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
                            .height(609.dp)
                    ) {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(143.dp)
                            .padding(top = 13.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )) {
                            Row(modifier = Modifier
                                .width(265.dp)
                                .height(95.63.dp)
                                .align(Alignment.CenterHorizontally)
                                .offset(y = 23.69.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    modifier = Modifier
                                        .width(72.dp)
                                        .height(95.63.dp),
                                    painter = painterResource(R.drawable.streak_graph),
                                    contentDescription = "Image from streak graph"
                                )
                                Column(modifier = Modifier
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
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                            .padding(top = 13.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )) {

                        }

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                            .padding(top = 13.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )) {

                        }

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                            .padding(top = 13.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )) {

                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewStatystics(){
    WrapperStatystics()
}
