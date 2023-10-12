package com.jishan.inspiroscope.ui.screen.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.jishan.inspiroscope.ui.screen.home.widgets.FirebaseMessagingNotificationPermissionDialog
import com.jishan.inspiroscope.ui.screen.home.widgets.QuoteAuthorInfoElement
import com.jishan.inspiroscope.ui.screen.theme.entities.Wallpaper

// todo featureese below
/**
 * Favorites and history:
Allow users to save their favorite quotes and wallpapers for easy access. Maintain a history of viewed items, so users can quickly find previously viewed content.

Search functionality:
Implement a search feature that allows users to search for specific quotes or authors.

Categories and tags:
Organize quotes and wallpapers into categories and tags, making it easier for users to find content related to specific topics, themes, or emotions.

Daily quote notifications:
Send daily notifications to users with a new quote or wallpaper, encouraging regular engagement with the app. - done

Quote and wallpaper customization:
Allow users to customize the appearance of quotes (font style, size, and color) and wallpapers (brightness, contrast, and filters). Users could also have the option to use their own images as wallpapers.

Sharing features:
Enable users to share their favorite quotes and wallpapers with friends and family on social media platforms.

User accounts and sync:
Implement user accounts and cloud sync, allowing users to access their favorites and settings across multiple devices.

Dark mode and themes:
Add a dark mode option and allow users to choose from a selection of themes to personalize the app's appearance.

Widgets:
Create home screen widgets that display a new quote or wallpaper each day or allow users to display their favorite quotes on their device's home screen.

In-app purchases and premium content:
Offer premium content or features, such as ad-free experience, exclusive quotes or wallpapers, or additional customization options through in-app purchases.

These features can help improve the user experience and make your app more appealing to a wider audience. Remember to prioritize features based on user needs and preferences, and consider user feedback when refining the app.
Regenerate response
 *
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(selectedWallpaper: Wallpaper?) {

    // code snippet for handling notification permission dialogue - starts here
    val showNotificationDialog = remember { mutableStateOf(false) }

    // Android 13 Api 33 - runtime notification permission has been added
    val notificationPermissionState = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS
    )
    if (showNotificationDialog.value) FirebaseMessagingNotificationPermissionDialog(
        showNotificationDialog = showNotificationDialog,
        notificationPermissionState = notificationPermissionState
    )

    LaunchedEffect(key1 = Unit) {
        if (notificationPermissionState.status.isGranted || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Firebase.messaging.subscribeToTopic("InspiroScope")
        } else showNotificationDialog.value = true
    }

    //ends here

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val data = homeViewModel.data.collectAsState()

    val pagerState = rememberPagerState { Int.MAX_VALUE }

    Box {
        if (selectedWallpaper != null) {
            Image(
                painter = painterResource(selectedWallpaper.imageResId),
                contentDescription = "Wallpaper",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        VerticalPager(
            state = pagerState, modifier = Modifier.fillMaxSize()
        ) { page ->
            LaunchedEffect(key1 = Unit, block = { homeViewModel.loadNextData(page) })
            val dataItem = data.value.getOrNull(page)
            if (dataItem != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (selectedWallpaper == null) {
                        Image(
                            painter = rememberAsyncImagePainter(dataItem.wallpaper.url),
                            contentDescription = "Wallpaper",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    QuoteAuthorInfoElement(dataItem.quote)
                }
            } else {
                // Show an empty screen or a loading indicator if the data is not available
            }
        }
    }
}

