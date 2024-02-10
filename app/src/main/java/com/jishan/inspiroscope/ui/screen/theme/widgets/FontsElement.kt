package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.ui.screen.theme.entities.Font

@Composable
fun FontsElement(font: Font, selectedFont: Font, onFontTapped: (Font) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.then(
            if (font.fontFamily == selectedFont.fontFamily) {
                Modifier
                    .wrapContentSize()
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(5.dp),
                    )
            } else {
                Modifier
            },
        ),
    ) {
        Text(
            font.title,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable { onFontTapped(font) }, // Invokes onFontTapped when font is clicked
            fontFamily = font.fontFamily,
            fontSize = 16.sp,
        )
    }
}
