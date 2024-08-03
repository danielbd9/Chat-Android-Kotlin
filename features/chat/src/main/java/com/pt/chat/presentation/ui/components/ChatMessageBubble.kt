package com.pt.chat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.core.utils.theme.getUserColor
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MessageBubble(
    message: Message,
    user: User,
    navController: NavController,
    modifier: Modifier = Modifier,
    isSent: Boolean = false
) {
    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start
    ) {
        if (!isSent) {
            AsyncImage(
                model = user.avatarId,
                contentDescription = user.name,
                modifier = Modifier
                    .size(40.dp)
                    .background(getUserColor(user.id), CircleShape)
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(
                    if (isSent) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.inverseSurface,
                    RoundedCornerShape(8.dp)
                )
                .clickable {
                    if (message.attachments?.isNotEmpty() == true) {
                        val encodedUrl = URLEncoder.encode(message.attachments[0].url, StandardCharsets.UTF_8.toString())
                        navController.navigate("attachment/$encodedUrl")
                    }
                }
                .padding(8.dp)
        ) {
            Column {
                if (!isSent) {
                    Text(text = user.name.orEmpty(), style = MaterialTheme.typography.bodySmall, color = Color.Green)
                }
                Text(text = message.content.orEmpty(), color = Color.White)

                if (message.attachments?.isNotEmpty() == true) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Anexo",
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(20.dp)
                            .padding(top = 4.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}