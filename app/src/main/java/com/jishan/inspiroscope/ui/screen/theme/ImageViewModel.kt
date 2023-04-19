package com.jishan.inspiroscope.ui.screen.theme

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val _imageBitmap = MutableStateFlow<Bitmap?>(null)
    val imageBitmap = _imageBitmap.asStateFlow()

    private suspend fun setImageBitmap(bitmap: Bitmap) {
        _imageBitmap.emit(bitmap)
    }

    fun loadBlurredImage(context: Context, resourceId: Int, blurRadius: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val requestOptions =
                RequestOptions().transform(FitCenter(), BlurTransformation(blurRadius))
            val blurredBitmap = Glide.with(context)
                .asBitmap()
                .apply(requestOptions)
                .load(resourceId)
                .submit()
                .get()

            setImageBitmap(blurredBitmap)
        }
    }
}
