package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jishan.inspiroscope.ui.screen.theme.ImageViewModel

@Composable
fun GlideBlurImage(
    resourceId: Int,
    modifier: Modifier,
    blurRadius: Int
) {
    val context = LocalContext.current
    val imageViewModel = viewModel<ImageViewModel>()
    imageViewModel.loadBlurredImage(context, resourceId, blurRadius)

    val imageBitmap by imageViewModel.imageBitmap.collectAsState()

    imageBitmap?.let { bitmap ->
        androidx.compose.foundation.Image(
            painter = BitmapPainter(bitmap.asImageBitmap()),
            contentDescription = "Blurred Background",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    }
}
