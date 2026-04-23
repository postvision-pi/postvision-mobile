package com.example.postvision.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun WrapperProfileEdtion(){
    PostVisionTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground,

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
                        fontSize = 14.sp
                    )
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