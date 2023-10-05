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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jishan.inspiroscope.R
import com.jishan.inspiroscope.ui.screen.theme.Border
import com.jishan.inspiroscope.ui.screen.theme.entities.Font
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper
import com.jishan.inspiroscope.ui.theme.DynaPuff

@Composable
fun WallPaperCard(
    wallpaper: Wallpaper,
    selectedFont: Font,
    selectedWallpaper: Wallpaper,
    onWallpaperTapped: (Wallpaper) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .height(293.dp)
            .width(159.dp)
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.clickable {
            onWallpaperTapped(wallpaper) // Invoke onWallpaperTapped when wallpaper is clicked
        }) {

            // Use rememberImagePainter to load and display the image , this helps to reduce the scaling of original high sized image
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current).data(wallpaper.imageResId)
                    .size(Size(159, 293)) // Set the target size to load the image at.
                    .build()
            )

            if (painter.state is AsyncImagePainter.State.Success) {
                Image(
                    painter = painter,
                    contentDescription = "Wallpaper Thumbnail",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(293.dp)
                        .width(159.dp),
                    contentScale = ContentScale.Crop
                )
            }

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
                        .height(293.dp)
                        .width(159.dp)
                        .align(Alignment.Center),
                    color = Color.White,
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun WallpaperCardPreview() {
    val wallpaper = Wallpaper("Wallpaper 1", R.drawable.first_wallpaper)
    val selectedFont = Font("Dyna Puff", DynaPuff)
    WallPaperCard(wallpaper = wallpaper,
        selectedWallpaper = wallpaper,
        selectedFont = selectedFont,
        onWallpaperTapped = { })
}