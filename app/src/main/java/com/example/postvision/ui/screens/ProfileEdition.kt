package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.components.TextInputComponent
import com.example.postvision.ui.navigation.NavRoutes

@Composable
fun WrapperProfileEdtion(){
    val scrollState = rememberScrollState()

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var datanasc by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    
    PostVisionTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            bottomBar = {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp),
                    onClick = {},
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Iniciar Passo a Passo",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 13.sp,
                        fontFamily = Raleway,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.onBackground
        ){paddingValues ->
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
                    )
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
                        fontSize = 14.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .width(345.dp),
                ) {
                    TextInputComponent(text = "Nome", value= nome, onValueChange = {nome})
                    TextInputComponent(text = "Sobrenome", value= sobrenome, onValueChange = {sobrenome})
                    TextInputComponent(text = "Genero", value= genero, onValueChange = {genero})
                    TextInputComponent(text = "E-mail", value= email, onValueChange = {email})
                    TextInputComponent(text = "Telefone", value= telefone, onValueChange = {telefone})
                    TextInputComponent(text = "CPF", value= cpf, onValueChange = {cpf})
                    TextInputComponent(text = "RG", value= rg, onValueChange = {rg})
                    TextInputComponent(text = "Data de Nascimento", value= datanasc, onValueChange = {datanasc})
                    TextInputComponent(text = "Senha", value= senha, onValueChange = {senha})

                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewProfileEdtion(){
    WrapperProfileEdtion()
}