package com.example.postvision.ui.components

import com.example.postvision.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.Raleway


@Composable
fun ProfileField(
        icon: Painter,
        label: String,
        value: String,
        isEditing: Boolean,
        onValueChange: (String) -> Unit
    ){
    Row(
        modifier = Modifier
            .padding(top = 20.dp, start = 18.dp, end = 18.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            contentDescription = "Ícone de $label",
            painter = icon,
        )
        Column(
            modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = label,
                fontSize = 13.sp,
                fontFamily = Raleway,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (isEditing) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            } else {
                Text(
                    text = value,
                    fontSize = 13.sp,
                    fontFamily = Raleway,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileField(){
    ProfileField(
        icon = painterResource(R.drawable.profile_icon_graph),
        label = "Nome",
        value = "Carlos",
        isEditing = true,
        onValueChange = {}
    )
}