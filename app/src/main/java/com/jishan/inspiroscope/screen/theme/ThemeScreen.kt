package com.jishan.inspiroscope.screen.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeScreen(
    wallpapers: List<Wallpaper>,
    fonts: List<Font>,
    sounds: List<Sound>,
    onWallpaperSelected: (Wallpaper) -> Unit,
    onFontSelected: (Font) -> Unit,
    onSoundSelected: (Sound) -> Unit,
    onUpgradeToPremium: () -> Unit
) {
    LazyColumn {
        // Upgrade to premium card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                backgroundColor = Color.LightGray
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Upgrade to Premium", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.ArrowForward, contentDescription = "Upgrade to Premium")
                }
            }
        }

        // Wallpapers
        item {
            HorizontalList(title = "Wallpapers", items = wallpapers) { wallpaper ->
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 4.dp
                ) {
                    Box(modifier = Modifier.background(wallpaper.color)) {
                        Text(
                            wallpaper.title,
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Fonts
        item {
            HorizontalList(title = "Fonts", items = fonts) { font ->
                Text(
                    font.title,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    fontFamily = font.fontFamily,
                    fontSize = 16.sp
                )
            }
        }

        // Sounds
        item {
            HorizontalList(title = "Sounds", items = sounds) { sound ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    sound.imageResId?.let { painterResource(it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = sound.title,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )
                    }
                    Text(sound.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun <T> HorizontalList(title: String, items: List<T>, itemContent: @Composable (T) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                itemContent(item)
            }
        }
    }
}

// Replace these classes with your actual data models
data class Wallpaper(val title: String, val color: Color)
data class Font(val title: String, val fontFamily: FontFamily)
data class Sound(val title: String, val imageResId: Int?)

// Usage example
@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview() {
    val wallpapers = listOf(
        Wallpaper("Wallpaper 1", Color.Red),
        Wallpaper("Wallpaper 2", Color.Green),
        Wallpaper("Wallpaper 3", Color.Blue),
        Wallpaper("Wallpaper 1", Color.Red),
        Wallpaper("Wallpaper 2", Color.Green),
        Wallpaper("Wallpaper 3", Color.Blue)
    )

    val fonts = listOf(
        Font("Font 1", FontFamily.Serif),
        Font("Font 2", FontFamily.SansSerif),
        Font("Font 3", FontFamily.Monospace)
    )

    val sounds = listOf(
        Sound("Sound 1", null),
        Sound("Sound 2", null),
        Sound("Sound 3", null)
    )

    ThemeScreen(
        wallpapers = wallpapers,
        fonts = fonts,
        sounds = sounds,
        onWallpaperSelected = { wallpaper -> },
        onFontSelected = { font -> },
        onSoundSelected = { sound -> },
        onUpgradeToPremium = { }
    )
}
