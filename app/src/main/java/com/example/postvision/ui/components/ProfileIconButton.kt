import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.screens.ProfileViewModel
import com.example.postvision.ui.theme.Raleway
import androidx.compose.ui.unit.TextUnit

fun getInitials(name: String): String {
    return name.trim().split("\\s+".toRegex())
        .take(2)
        .mapNotNull { it.firstOrNull()?.uppercase() }
        .joinToString("")
}

@Composable
fun ProfileIconButton(
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    fontSize: TextUnit = 32.sp
) {
    val userName = "$firstName $lastName"
    val initials = getInitials(userName)

    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(100.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = Raleway,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}