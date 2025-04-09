package com.swimconnect.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.swimconnect.app.mobileapp.generated.resources.Res
import com.swimconnect.app.mobileapp.generated.resources.poppins_bold
import com.swimconnect.app.mobileapp.generated.resources.poppins_medium
import com.swimconnect.app.mobileapp.generated.resources.poppins_regular
import com.swimconnect.app.mobileapp.generated.resources.poppins_semi_bold
import org.jetbrains.compose.resources.Font

@Composable
fun getPoppinsFontFamily(): FontFamily {
    return FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semi_bold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )
}