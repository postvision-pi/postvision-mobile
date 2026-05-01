package com.example.postvision.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.Raleway

@Composable
fun TextInputComponent(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
    ){
        Text(
            text = text,
            fontSize = 13.sp,
            fontFamily = Raleway,
            color = MaterialTheme.colorScheme.onSurface
        )
        BasicTextField(
            value = value,
            onValueChange = {onValueChange},
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
}

@Preview
@Composable
fun PreviewTextInputComponent(){
    TextInputComponent(
        text = "",
        value = "",
        onValueChange = {}
    )
}