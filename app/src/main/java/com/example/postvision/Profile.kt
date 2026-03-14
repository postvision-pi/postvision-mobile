package com.example.postvision

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway

@Composable
fun WrapperProfile(){
    PostVisionTheme() {
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
                Column(modifier = Modifier.padding(top = 30.dp)) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .width(345.dp)
                            .height(57.dp)
                            ,
                        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
                    ){

                        Image(
                            painter = painterResource(R.drawable.cam_graph),
                            contentDescription = "Image from cam graph"
                        )


                        Text(
                            text = "Minhas Atividades"
                        )

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