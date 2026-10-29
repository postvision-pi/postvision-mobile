package com.example.postvision.ui.screens

import ProfileIconButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postvision.R
import com.example.postvision.ui.components.BottomNavBar
import com.example.postvision.ui.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import com.example.postvision.ui.components.ProfileField

@Composable
fun WrapperProfile(
    viewModel: ProfileViewModel,
    userEmail: String,
    token: String,
    onBottomTabClick: (String) -> Unit,
    onNavigateToSettings: () -> Unit
){
    val scrollState = rememberScrollState()
    val userName = viewModel.firstName + " " + viewModel.lastName

    if (userEmail.isNotEmpty() && token.isNotEmpty()) {
        viewModel.loadUserProfile(userEmail, token)
    }

    PostVisionTheme {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        selectedRoute = "profile",
                        onNavigate = { route ->
                            if (route == "home") {
                                onBottomTabClick(NavRoutes.HOME)
                            } else if (route == "stats") {
                                onBottomTabClick(NavRoutes.STATISTICS)
                            }
                        }
                    )
                },
                containerColor = MaterialTheme.colorScheme.onBackground
            ) {innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.onBackground
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 24.dp)
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
                                    .clickable(
                                        onClick = onNavigateToSettings
                                    )
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 67.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfileIconButton(
                            firstName = viewModel.firstName,
                            lastName = viewModel.lastName,
                            modifier = Modifier.size(100.dp)
                        )
                        Text(
                            text = viewModel.firstName,
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
                        ProfileField(
                            icon = painterResource(R.drawable.profile_icon_graph),
                            label = "Nome",
                            value = viewModel.firstName,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.firstName = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.profile_icon_graph),
                            label = "Sobrenome",
                            value = viewModel.lastName,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.lastName = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.profile_icon_graph),
                            label = "Gênero",
                            value = viewModel.gender,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.gender = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.email_icon_graph),
                            label = "Email",
                            value = viewModel.email,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.email = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.phone_icon_graph),
                            label = "Telefone",
                            value = viewModel.phone,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.phone = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.idcard_icon_graph),
                            label = "CPF",
                            value = viewModel.cpf,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.cpf = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.data_icon_graph),
                            label = "Data de Nascimento",
                            value = viewModel.birthDate,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.birthDate = it }
                        )
                        ProfileField(
                            icon = painterResource(R.drawable.password_icon_graph),
                            label = "Senha",
                            value = viewModel.password,
                            isEditing = viewModel.isEditing,
                            onValueChange = { viewModel.password = it }
                        )

                        Button(
                            onClick = {
                                if (viewModel.isEditing) viewModel.updateUserProfile()
                                else viewModel.isEditing = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            enabled = !viewModel.isLoading,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            if (viewModel.isLoading) {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                            } else {
                                Text(if (viewModel.isEditing) "Salvar" else "Editar Perfil", color = MaterialTheme.colorScheme.background)
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
    val fakeViewModel: ProfileViewModel = viewModel()
    WrapperProfile(
        viewModel = fakeViewModel,
        userEmail = "",
        token = "",
        onBottomTabClick = {},
        onNavigateToSettings = {}
    )
}