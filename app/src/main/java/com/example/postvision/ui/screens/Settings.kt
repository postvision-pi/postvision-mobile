package com.example.postvision.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun WrapperSettigs(){
    PostVisionTheme(){
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
                        onClick = {},
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
                    )
                }

                Column(
                    modifier = Modifier
                        .width(345.dp)
                        .height(468.dp)
                        .padding(top = 52.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25f)
                    ) {
                        Text(
                            "Acessibilidade",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row(
                            
                        ){
                            Text(
                                "Acessibilidade",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25f)
                    ) {
                        Text(
                            "Acessibilidade",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row(

                        ){
                            Text(
                                "Acessibilidade",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25f)
                    ) {
                        Text(
                            "Acessibilidade",
                            fontSize = 24.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )

                        Row(

                        ){
                            Text(
                                "Acessibilidade",
                                fontSize = 16.sp,
                                fontFamily = Raleway,
                                fontWeight = FontWeight.SemiBold
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
    WrapperSettigs()
}