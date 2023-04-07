package com.jishan.inspiroscope.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.jishan.domain.entitiy.QuoteEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val quote = homeViewModel.quote.collectAsState()
    val wallpaper = homeViewModel.wallpaper.collectAsState()

    val listState = rememberPagerState()

    VerticalPager(
        pageCount = Int.MAX_VALUE, // Infinite pages
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        homeViewModel.loadNextData(page)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            wallpaper.value?.url?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = "Wallpaper",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            quote.value?.let { QuoteCard(it) }
        }
    }
}

@Composable
fun QuoteCard(quoteEntity: QuoteEntity) {
    Column(
        modifier = Modifier.background(color = Color.Black.copy(alpha = 0.3f)).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = quoteEntity.quote,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "- ${quoteEntity.author}",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
