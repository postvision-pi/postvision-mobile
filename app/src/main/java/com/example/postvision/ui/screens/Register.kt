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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.MainViewModel
import com.example.postvision.R
import com.example.postvision.ui.components.TextInputComponent
import com.example.postvision.ui.model.User
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway


@Composable
fun WrapperRegister(
    viewModel: MainViewModel,
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
    var birthDate by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    val isBusy = viewModel.isRegistering || viewModel.isLoading
    val errorMessage = localError ?: viewModel.registerError

    // Limpa erro de uma tentativa anterior ao entrar na tela
    LaunchedEffect(Unit) {
        viewModel.clearRegisterError()
    }

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
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, top = 57.dp, bottom = 24.dp),

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
                    onValueChange = { email = it },
                    keyboardType = KeyboardType.Email
                )

                // Senha
                TextInputComponent(
                    text = "Senha",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )

                // Nome
                TextInputComponent(
                    text = "Nome",
                    value = name,
                    onValueChange = { name = it }
                )

                // Sobrenome
                TextInputComponent(
                    text = "Sobrenome",
                    value = lastname,
                    onValueChange = { lastname = it }
                )

                // Genero
                TextInputComponent(
                    text = "Gênero",
                    value = genre,
                    onValueChange = { genre = it }
                )

                // Telefone
                TextInputComponent(
                    text = "Telefone",
                    value = telephone,
                    onValueChange = { telephone = it },
                    keyboardType = KeyboardType.Phone
                )

                // CPF
                TextInputComponent(
                    text = "CPF",
                    value = cpf,
                    onValueChange = { cpf = it },
                    keyboardType = KeyboardType.Number
                )

                // Data de nascimento
                TextInputComponent(
                    text = "Data de Nascimento",
                    value = birthDate,
                    onValueChange = { birthDate = formatBirthDate(it) },
                    keyboardType = KeyboardType.Number
                )

                if (errorMessage != null) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = errorMessage,
                        fontFamily = Raleway,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

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
                        enabled = !isBusy,
                        onClick = {
                            val cleanEmail = email.trim()
                            val cleanName = name.trim()
                            val cleanLastname = lastname.trim()

                            val apiBirthDate = toApiDate(birthDate)

                            localError = when {
                                cleanEmail.isEmpty() || password.isEmpty() ||
                                    cleanName.isEmpty() || cleanLastname.isEmpty() ||
                                    genre.isBlank() || telephone.isBlank() || cpf.isBlank() ||
                                    birthDate.isBlank() ->
                                    "Preencha todos os campos."
                                !android.util.Patterns.EMAIL_ADDRESS.matcher(cleanEmail).matches() ->
                                    "Informe um e-mail válido."
                                apiBirthDate == null ->
                                    "Informe uma data de nascimento válida (dd/mm/aaaa)."
                                else -> null
                            }

                            if (localError == null) {
                                viewModel.performRegister(
                                    user = User(
                                        firstName = cleanName,
                                        lastName = cleanLastname,
                                        email = cleanEmail,
                                        password = password,
                                        birthDate = apiBirthDate,
                                        cpf = cpf.trim(),
                                        gender = genre.trim(),
                                        phone = telephone.trim()
                                    ),
                                    onSuccess = onNavigateToHome
                                )
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        ) {
                        if (isBusy) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.background
                            )
                        } else {
                            Text(
                                "Cadastrar",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 13.sp,
                                fontFamily = Raleway,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
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

// Deixa só números e aplica a máscara dd/mm/aaaa
private fun formatBirthDate(input: String): String {
    val digits = input.filter { it.isDigit() }.take(8)
    return buildString {
        digits.forEachIndexed { i, c ->
            if (i == 2 || i == 4) append('/')
            append(c)
        }
    }
}

// Converte dd/mm/aaaa para aaaa-mm-dd (formato da API). Retorna null se for inválida.
private fun toApiDate(input: String): String? {
    val digits = input.filter { it.isDigit() }
    if (digits.length != 8) return null
    val day = digits.substring(0, 2).toInt()
    val month = digits.substring(2, 4).toInt()
    val year = digits.substring(4, 8).toInt()
    if (day !in 1..31 || month !in 1..12 || year < 1900) return null
    return "%04d-%02d-%02d".format(year, month, day)
}

@Preview
@Composable
fun PreviewRegister(){
    WrapperRegister(
        viewModel = MainViewModel(),
        onNavigateToLogin = {},
        onNavigateToHome = {}
    )
}
