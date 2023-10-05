package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.ui.screen.theme.Border
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper

@Composable
fun WallPaperCard(
    wallpaper: Wallpaper,
    selectedFont: Font,
    selectedWallpaper: Wallpaper,
    onWallpaperTapped: (Wallpaper) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .height(350.dp)
            .width(190.dp)
            .padding(8.dp),
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
                contentScale = ContentScale.Crop
            )
            Text(
                wallpaper.title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = selectedFont.fontFamily // Updated fontFamily with the selected font
            )
            // Added a border around the selected wallpaper
            if (wallpaper == selectedWallpaper) {
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