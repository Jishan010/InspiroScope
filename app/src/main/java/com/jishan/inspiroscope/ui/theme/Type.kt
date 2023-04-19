package com.jishan.inspiroscope.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jishan.inspiroscope.R

val DynaPuff = FontFamily(Font(R.font.dyna_puff))
val BreeSerifRegular = FontFamily(Font(R.font.bree_serif_regular))
val DeliciousHandrawn = FontFamily(Font(R.font.delicious_handrawn))
val IndieFlower = FontFamily(Font(R.font.indie_flower))
val MarkScriptRegular = FontFamily(Font(R.font.mark_script_regular))
val VtThreeThreeThreeRegular = FontFamily(Font(R.font.bree_serif_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
