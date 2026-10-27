package com.example.postvision.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperSettings(
    onNavigateProfile: () -> Unit
){
    PostVisionTheme{
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Button(modifier = Modifier
                        .width(364.dp)
                        .height(51.dp)
                        .offset(y = -36.dp)
                        .align(Alignment.Center),
                        onClick = {
                            onNavigateProfile()
                        },
                        shape = RoundedCornerShape(10.dp)


                    ) {
                        Text(
                            "Salvar",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.onBackground,
        ) { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .padding(vertical = 22.dp, horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Configurações",
                        fontSize = 16.sp,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(
                    modifier = Modifier
                        .width(345.dp)
                        .height(468.dp)
                        .padding(top = 52.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            "Acessibilidade",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                        ){
                            Text(
                                "Alterar tema",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Tamanho da fonte",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Idioma",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            "Privacidade e Dados",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                        ){
                            Text(
                                "Histórico de análises realizadas",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Baixar relatório de desempenho",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Limpar dados armazenados",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            "Ajuda e Suporte",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                        ){
                            Text(
                                "FAQ",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Enviar feedback",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Reportar problema",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Row(

                        ){
                            Text(
                                "Contato do suporte",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSettings(){
    WrapperSettings(
        onNavigateProfile = {}
    )
}