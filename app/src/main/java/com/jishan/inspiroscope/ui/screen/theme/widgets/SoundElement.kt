package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound

@Composable
fun SoundElement(
    sound: Sound,
    modifier: Modifier = Modifier,
    selectedSound: Sound,
    onSoundTapped: (Sound) -> Unit
) {

    val isSelected = sound == selectedSound

    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val imageModifier = Modifier
            .size(88.dp)
            .clip(CircleShape)
            .clickable {
                onSoundTapped(sound)
            }

        // Use rememberImagePainter to load and display the image
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(sound.imageResId)
                .size(Size(80, 80)) // Set the target size to load the image at.
                .build()
        )

        if (painter.state is AsyncImagePainter.State.Success) {
            // This will be executed during the first composition if the image is in the memory cache.
        }

        Image(
            painter = painter,
            contentDescription = sound.title,
            contentScale = ContentScale.Crop,
            modifier = if (isSelected) {
                imageModifier.border(1.dp, Color.White, CircleShape)
            } else {
                imageModifier
            }
        )
        Text(sound.title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}