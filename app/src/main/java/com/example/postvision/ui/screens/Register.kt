package com.example.postvision.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.MainViewModel
import com.example.postvision.R
import com.example.postvision.ui.components.TextInputComponent
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import org.w3c.dom.Text


@Composable
fun WrapperRegister(
    onNavigateToLogin:() -> Unit,
    onNavigateToHome:() -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val alpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f,
        label = "alpha"
    )

    PostVisionTheme() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Box(modifier = Modifier
                .fillMaxSize(),
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
                .padding(start = 24.dp, end = 24.dp, top = 57.dp),

                ) {
                Text(
                    modifier = Modifier
                        .padding(top = 43.dp),
                    text = "Cadastro",
                    fontFamily = Raleway,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.surface
                )

                // E-mail
                TextInputComponent(
                    text = "E-mail",
                    value = email,
                    onValueChange = { email }
                )

                // Senha
                TextInputComponent(
                    text = "Senha",
                    value = password,
                    onValueChange = { password }
                )

                // Nome
                TextInputComponent(
                    text = "Nome",
                    value = name,
                    onValueChange = { name }
                )

                // Sobrenome
                TextInputComponent(
                    text = "Sobrenome",
                    value = lastname,
                    onValueChange = { lastname }
                )

                // Genero
                TextInputComponent(
                    text = "Gênero",
                    value = genre,
                    onValueChange = { genre }
                )

                // Telefone
                TextInputComponent(
                    text = "Telefone",
                    value = telephone,
                    onValueChange = { telephone }
                )

                // CPF
                TextInputComponent(
                    text = "CPF",
                    value = cpf,
                    onValueChange = { cpf }
                )

                Column(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                ){
                    Button(
                        modifier = Modifier
                            .width(345.dp)
                            .height(51.dp)
                            .align(Alignment.CenterHorizontally),
                        onClick = {
                            onNavigateToHome()
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        ) {
                        Text(
                            "Cadastrar",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 13.sp,
                            fontFamily = Raleway,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(),
                            horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            "Já é cadastrado?",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface

                        )
                        Text(
                            "Login",
                            fontFamily = Raleway,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .alpha(alpha)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ){
                                    onNavigateToLogin()
                                },
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewRegister(){
    WrapperRegister(
        onNavigateToLogin = {},
        onNavigateToHome = {}
    )
}