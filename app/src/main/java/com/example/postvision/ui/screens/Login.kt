package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postvision.MainViewModel
import com.example.postvision.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WrapperLogin(
    viewModel: MainViewModel,
    onNavigateToHome: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    PostVisionTheme {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground)
        {
            Box(modifier = Modifier
                .fillMaxSize()
            ){
                Image(
                    modifier = Modifier
                        .width(350.dp)
                        .height(350.dp)
                        .offset(x = 105.dp, y = (-75).dp)
                        .align(Alignment.TopEnd),

                    painter = painterResource(R.drawable.head_graph),
                    contentDescription = "Image from head graph"
                )
                Image(
                    modifier = Modifier
                        .width(295.dp)
                        .height(201.dp)
                        .offset(x = (-75).dp, y = 5.dp)
                        .align(Alignment.BottomStart),

                    painter = painterResource(R.drawable.eye_two_graph),
                    contentDescription = "Image from head graph"
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .offset(y = 258.dp)){
                Text(
                    "Login",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.surface
                )

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 47.dp)){
                    Text(
                        "E-mail",
                        fontSize = 13.sp,
                        fontFamily = Raleway,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    BasicTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                            .padding(horizontal = 20.dp, vertical = 12.dp), // Ajuste o texto manualmente
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 67.dp)){
                    Text(
                        "Senha",
                        fontSize = 13.sp,
                        fontFamily = Raleway,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    BasicTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                            .padding(horizontal = 20.dp, vertical = 12.dp), // Ajuste o texto manualmente
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                }

                Column(
                    modifier = Modifier.offset(y = 151.dp)
                        .fillMaxWidth(),
                ){
                    Button(
                        modifier = Modifier
                            .width(345.dp)
                            .height(51.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = { viewModel.performLogin(email, password, onNavigateToHome) },
                        enabled = !viewModel.isLoading,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),

                        ) {
                        if(viewModel.isLoading)
                            CircularProgressIndicator()
                        else
                            Text(
                                "Entrar",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 13.sp,
                                fontFamily = Raleway,
                                fontWeight = FontWeight.SemiBold
                            )
                    }

                    Row(modifier = Modifier
                        .width(195.dp)
                        .offset(y = 20.dp)
                        .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Text(
                            "Não tem conta?",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface

                        )
                        Text(
                            "Cadastre-se",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewLogin(){
    val fakeViewModel: MainViewModel = viewModel()
    WrapperLogin(
        viewModel = fakeViewModel,
        onNavigateToHome = {}
    )
}