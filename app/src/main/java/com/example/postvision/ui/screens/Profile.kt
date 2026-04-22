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
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperProfile(){
    val scrollState = rememberScrollState()

    PostVisionTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground,

        ){
            Column(modifier = Modifier
                .padding(vertical = 22.dp, horizontal = 24.dp)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                    )
                    Row(
                        modifier = Modifier
                            .width(61.04.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
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

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 67.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        modifier = Modifier
                            .size(100.dp),
                        shape = RoundedCornerShape(50.dp),
                    ){

                    }
                    Text(
                        text = "Lorem",
                        fontFamily = Raleway,
                        fontSize = 14.sp
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
                        text = "Minhas Atividades"
                    )
                    Image(
                        painter = painterResource(R.drawable.recent_graph),
                        contentDescription = "Image from cam graph"
                    )

                    }


                    Card(
                        modifier = Modifier
                            .width(345.dp)
                            .height(520.dp)
                            .padding(top = 30.dp)
                            .verticalScroll(scrollState),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )

                    ){
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 18.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                               Image(
                                   modifier = Modifier
                                       .size(24.dp),
                                    contentDescription = "Image from profile icon",
                                    painter = painterResource(R.drawable.profile_icon),
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
                    }
                }

            }
        }
    }


@Preview
@Composable
fun MobilePreviewProfile(){
    WrapperProfile()
}