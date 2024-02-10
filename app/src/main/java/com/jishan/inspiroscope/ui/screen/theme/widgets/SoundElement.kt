package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
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
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound

@Composable
fun SoundElement(
    sound: Sound,
    modifier: Modifier = Modifier,
    selectedSound: Sound,
    selectedFont: Font,
    onSoundTapped: (Sound) -> Unit,
) {
    val isSelected = sound == selectedSound

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val imageModifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .size(80.dp)
            .clip(CircleShape)
            .clickable {
                onSoundTapped(sound)
            }

        Box(
            modifier = if (isSelected) {
                imageModifier.border(1.dp, Color.White, CircleShape)
            } else {
                imageModifier
            },
        ) {
            // Use rememberImagePainter to load and display the image
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(sound.imageResId)
                    .size(Size(150, 150)) // Set the target size to load the image at.
                    .build(),
            )

            // Display the Image only when it's in the Success state
            when (painter.state) {
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = painter,
                        contentDescription = sound.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                else -> {
                    /* Display nothing for other states (e.g., Loading) */
                }
            }

            // Display CircularProgressIndicator while loading
            if (painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(36.dp) // Adjust the size of the CircularProgressIndicator
                        .align(Alignment.Center),
                )
            }
        }
        Text(
            text = sound.title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = selectedFont.fontFamily,
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}
