package com.example.postvision.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.R
import com.example.postvision.ui.theme.Raleway

@Composable
fun BottomNavBar(
    selectedRoute: String,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center // <--- ISSO VAI CENTRALIZAR SUA ROW NA MARRA!
    ) {
        // BARRA DE NAVEGAÇÃO INFERIOR
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(70.dp)
                .offset(y = -14.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,

                ) {
                NavItem(
                    "Início",
                    R.drawable.home_graph,
                    selectedRoute == "home"
                ) { onNavigate("home") }
                NavItem(
                    "Perfil",
                    R.drawable.profile_graph,
                    selectedRoute == "profile"
                ) { onNavigate("profile") }
                NavItem(
                    "Stats",
                    R.drawable.stats_graph,
                    selectedRoute == "stats"
                ) { onNavigate("stats") }
            }
        }
    }
}

@Composable
fun NavItem(
    label: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) Color(0x52DEDEFF) else Color.Transparent

    Card(
        modifier = Modifier
            .width(53.dp)
            .height(60.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(iconRes),
                contentDescription = label
            )
            Text(
                text = label,
                fontFamily = Raleway,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.surface,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Preview
@Composable
fun NavBarPreview(){
    BottomNavBar(
        selectedRoute = "",
        onNavigate = {}
    )
}