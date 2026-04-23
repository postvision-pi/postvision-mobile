package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperProfile(
    onBottomTabClick: (String) -> Unit
){
    val scrollState = rememberScrollState()

    PostVisionTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground,

        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BottomNavBar(
                            selectedRoute = "profile",
                            onNavigate = { route ->
                                if (route == "home") {
                                    onBottomTabClick(NavRoutes.HOME) // Usa a rota de estatísticas
                                } else if (route == "stats") {
                                    // Se tiver uma rota de perfil, coloque aqui
                                    onBottomTabClick(NavRoutes.STATYSTICS)
                                }
                            }
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.onBackground
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 24.dp)
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Perfil",
                            fontSize = 16.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(
                            modifier = Modifier
                                .width(61.04.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                contentDescription = "Image from symbol for alter dates",
                                painter = painterResource(R.drawable.alter_graph),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Image(
                                contentDescription = "Image from configuration",
                                painter = painterResource(R.drawable.config_graph),
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 67.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(100.dp),
                            shape = RoundedCornerShape(50.dp),
                        ) {

                        }
                        Text(
                            text = "Lorem",
                            fontFamily = Raleway,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .width(345.dp)
                            .height(57.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(
                            text = "Minhas Atividades",
                            fontSize = 16.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Image(
                            painter = painterResource(R.drawable.recent_graph),
                            contentDescription = "Image from cam graph"
                        )

                    }


                    Card(
                        modifier = Modifier
                            .width(345.dp)
                            .height(383.dp)
                            .padding(top = 30.dp)
                            .verticalScroll(scrollState),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        shape = RoundedCornerShape(20.dp)

                    ) {
                        // NOME
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.profile_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Nome",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Carlos",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // SOBRENOME
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.profile_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Sobrenome",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Silva",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // GÊNERO
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.profile_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Gênero",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Masculino",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // EMAIL
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.email_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Email",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "renanzanettio@gmail.com",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // TELEFONE
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.phone_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Telefone",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "(13)99605-4131",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // CPF
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.idcard_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "CPF",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "190.203.400-14",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // RG
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.idcard_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "RG",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "190.203.400-14",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // DATA DE NASCIMENTO
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.data_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Data de Nascimento",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "02/03/2006",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // SENHA
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp),
                                contentDescription = "Image from profile icon",
                                painter = painterResource(R.drawable.password_icon_graph),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = "Senha",
                                    fontSize = 13.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "***********",
                                    fontSize = 13.sp,
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
    }


@Preview
@Composable
fun MobilePreviewProfile(){
    WrapperProfile(
        onBottomTabClick = {}
    )
}