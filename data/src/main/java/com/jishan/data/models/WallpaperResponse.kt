package com.jishan.data.models

data class WallpaperResponse(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val promoted_at: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val description: String?,
    val alt_description: String?,
    val urls: Urls,
    val user: User
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User(
    val id: String,
    val username: String,
    val name: String
)

data class Wallpaper(
    val id: String,
    val url: String,
    val photographer: String
) {
    constructor(wallpaperResponse: WallpaperResponse) : this(
        id = wallpaperResponse.id,
        url = wallpaperResponse.urls.regular,
        photographer = wallpaperResponse.user.name
    )
}
