package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound

@Composable
fun SoundElement(sound: Sound,modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        sound.imageResId?.let { painterResource(it) }?.let {
            Image(
                painter = it,
                contentDescription = sound.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
        }
        Text(sound.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}