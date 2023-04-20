package com.jishan.inspiroscope.ui.screen.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.R
import com.jishan.inspiroscope.ui.theme.BreeSerifRegular
import com.jishan.inspiroscope.ui.theme.DeliciousHandrawn
import com.jishan.inspiroscope.ui.theme.DynaPuff
import com.jishan.inspiroscope.ui.theme.IndieFlower
import com.jishan.inspiroscope.ui.theme.MarkScriptRegular
import com.jishan.inspiroscope.ui.theme.VtThreeThreeThreeRegular
import com.jishan.inspiroscope.utils.GlideBlurImage

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
    // Remember the selected wallpaper
    val selectedWallpaper = remember { mutableStateOf(wallpapers.first()) }

    // Update the selected wallpaper when the user taps on a wallpaper
    val onWallpaperTapped: (Wallpaper) -> Unit = { wallpaper ->
        selectedWallpaper.value = wallpaper
        onWallpaperSelected(wallpaper)
    }

    // Remember the selected font
    val selectedFont = remember { mutableStateOf(fonts.first()) }

    // Update the selected font when the user taps on a font
    val onFontTapped: (Font) -> Unit = { font ->
        selectedFont.value = font
        onFontSelected(font)
    }

    // Set the background image to the selected wallpaper
    Box(modifier = Modifier.fillMaxSize()) {
        GlideBlurImage(
            resourceId = selectedWallpaper.value.imageResId,
            modifier = Modifier.fillMaxSize(),
            blurRadius = 10 // Change the value of the blur radius
        )

        // Add a header with the title "Themes"
        Column {
            Text(
                "Themes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                        Box {
                            Image(
                                painter = painterResource(R.drawable.upgrade_to_premium_background),
                                contentDescription = "Wallpaper Thumbnail",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .height(80.dp)
                                    .wrapContentSize(),
                                contentScale = ContentScale.Crop
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = "Go Premium",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(R.drawable.baseline_chevron_right_24),
                                    contentDescription = "Wallpaper Thumbnail",
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }

                // Wallpapers
                item {
                    HorizontalList(title = "Wallpapers", items = wallpapers) { wallpaper ->
                        Card(
                            modifier = Modifier
                                .height(350.dp)
                                .width(190.dp)
                                .padding(8.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = 4.dp
                        ) {
                            Box(
                                modifier = Modifier.clickable {
                                    onWallpaperTapped(wallpaper) // Invoke onWallpaperTapped when wallpaper is clicked
                                }
                            ) {
                                Image(
                                    painter = painterResource(wallpaper.imageResId),
                                    contentDescription = "Wallpaper Thumbnail",
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .height(350.dp)
                                        .width(190.dp),
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    wallpaper.title,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = selectedFont.value.fontFamily // Update fontFamily with the selected font
                                )
                                // Add a border around the selected wallpaper
                                if (wallpaper == selectedWallpaper.value) {
                                    Border(
                                        modifier = Modifier
                                            .height(350.dp)
                                            .width(190.dp)
                                            .align(Alignment.Center),
                                        color = Color.White,
                                        width = 2.dp,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                // Fonts
                item {
                    HorizontalList(title = "Fonts", items = fonts) { font ->
                        Box(
                            modifier = Modifier.then(
                                if (font.fontFamily == selectedFont.value.fontFamily) {
                                    Modifier
                                        .wrapContentSize()
                                        .border(
                                            width = 2.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(5.dp)
                                        )
                                } else Modifier
                            )
                        ) {
                            Text(
                                font.title,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 8.dp)
                                    .clickable { onFontTapped(font) }, // Invoke onFontTapped when font is clicked
                                fontFamily = font.fontFamily,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                // Sounds
                item {
                    HorizontalList(title = "Sounds", items = sounds) { sound ->
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
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
    }
}

@Composable
fun Border(modifier: Modifier, color: Color, width: Dp, shape: Shape) {
    val borderModifier = modifier.border(width, color, shape)
    Box(modifier = borderModifier)
}

@Composable
fun <T> HorizontalList(title: String, items: List<T>, itemContent: @Composable (T) -> Unit) {
    Column {
        Text(
            title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(items) { item ->
                Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                    itemContent(item)
                }
            }
        }
    }
}

// Replace these classes with your actual data models
data class Wallpaper(
    val title: String,
    val color: Color,
    val imageResId: Int = R.drawable.first_wallpaper
)

data class Font(val title: String, val fontFamily: FontFamily)

data class Sound(val title: String, val imageResId: Int?)

// Usage example
@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview() {
    val wallpapers = listOf(
        Wallpaper("Wallpaper 1", Color.Red, R.drawable.first_wallpaper),
        Wallpaper("Wallpaper 2", Color.Green, R.drawable.second_wallpaper),
        Wallpaper("Wallpaper 1", Color.Red, R.drawable.fourth_wallpaper),
        Wallpaper("Wallpaper 3", Color.Blue, R.drawable.sixth_wallpaper),
        Wallpaper("Wallpaper 1", Color.Red, R.drawable.seventh_wallpaper),
        Wallpaper("Wallpaper 2", Color.Green, R.drawable.eighth_wallaper),
        Wallpaper("Wallpaper 3", Color.Blue, R.drawable.ninth_wallpaper)
    )

    val fonts = listOf(
        Font("Dyna Puff", DynaPuff),
        Font("Bree Serif", BreeSerifRegular),
        Font("Delicious Handrawn", DeliciousHandrawn),
        Font("Indie Flower", IndieFlower),
        Font("Mark Script", MarkScriptRegular),
        Font("Vt333", VtThreeThreeThreeRegular)
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
