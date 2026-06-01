package com.example.api1.model

import java.io.Serializable

data class ThemeItem(
    val Id: Int,
    val Cat_Id: Int,
    val App_Version: String?,
    val Theme_Name: String,
    val GameobjectName: String?,
    val Is_Preimum: String?,
    val Theme_lang: String?,
    val Theme_Bundle: String?,
    val Thumnail_Big: String?,
    val Thumnail_Small: String?,
    val SoundFile: String?,
    val SoundName: String?
) : Serializable