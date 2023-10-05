package com.jishan.inspiroscope.ui.screen.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.R
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Sound
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper
import com.jishan.inspiroscope.ui.screen.theme.widgets.FontsElement
import com.jishan.inspiroscope.ui.screen.theme.widgets.GoPremiumCard
import com.jishan.inspiroscope.ui.screen.theme.widgets.SoundElement
import com.jishan.inspiroscope.ui.screen.theme.widgets.WallPaperCard
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
    onUpgradeToPremium: () -> Unit,
    modifier: Modifier = Modifier
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

    // Remember the selected sound
    val selectedSound = remember { mutableStateOf(sounds.first()) }

    // Update the selected sound when the user taps on a sound
    val onSoundTapped: (Sound) -> Unit = { sound ->
        selectedSound.value = sound
        onSoundSelected(sound)
    }

    // Set the background image to the selected wallpaper
    Box(modifier = Modifier.fillMaxSize()) {
        GlideBlurImage(
            resourceId = selectedWallpaper.value.imageResId,
            modifier = Modifier.fillMaxSize(),
            blurRadius = 10 // Change the value of the blur radius
        )

        // Add a header with the title "Themes"
        Column(
            modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                "Themes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Upgrade to premium card
            GoPremiumCard()
            // Wallpapers
            HorizontalListSection(title = "Wallpapers", items = wallpapers) { wallpaper ->
                WallPaperCard(
                    wallpaper = wallpaper,
                    selectedWallpaper = selectedWallpaper.value,
                    selectedFont = selectedFont.value,
                    onWallpaperTapped = onWallpaperTapped
                )
            }
            // Sounds
            HorizontalListSection(title = "Sounds", items = sounds) { sound ->
                SoundElement(
                    sound = sound,
                    onSoundTapped = onSoundTapped,
                    selectedSound = selectedSound.value,
                    selectedFont = selectedFont.value
                )
            }
            // Fonts
            HorizontalListSection(title = "Fonts", items = fonts) { font ->
                FontsElement(font = font, selectedFont = selectedFont.value, onFontTapped)
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
fun <T> HorizontalListSection(title: String, items: List<T>, itemContent: @Composable (T) -> Unit) {
    Column {
        Text(
            title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
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

// Usage example
@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview() {
    val wallpapers = listOf(
        Wallpaper("Wallpaper 1", Color.Red, R.drawable.first_wallpaper),
        Wallpaper("Wallpaper 2", Color.Green, R.drawable.second_wallpaper),
        Wallpaper("Wallpaper 3", Color.Red, R.drawable.fourth_wallpaper),
        Wallpaper("Wallpaper 4", Color.Blue, R.drawable.sixth_wallpaper),
        Wallpaper("Wallpaper 5", Color.Red, R.drawable.seventh_wallpaper),
        Wallpaper("Wallpaper 6", Color.Green, R.drawable.eighth_wallaper),
        Wallpaper("Wallpaper 7", Color.Blue, R.drawable.ninth_wallpaper)
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
        Sound("Sound 1", R.drawable.first_sound),
        Sound("Sound 2", R.drawable.second_sound),
        Sound("Sound 3", R.drawable.third_sound),
        Sound("Sound 4", R.drawable.fourth_sound),
        Sound("Sound 5", R.drawable.fifth_sound),
        Sound("Sound 6", R.drawable.sixth_sound),
        Sound("Sound 7", R.drawable.seventh_sound),
    )

    ThemeScreen(wallpapers = wallpapers,
        fonts = fonts,
        sounds = sounds,
        onWallpaperSelected = { wallpaper -> },
        onFontSelected = { font -> },
        onSoundSelected = { sound -> },
        onUpgradeToPremium = { })
}
