package com.jishan.inspiroscope.ui.screen.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.jishan.inspiroscope.R

@Composable
fun GoPremiumCard(modifier: Modifier = Modifier) {
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