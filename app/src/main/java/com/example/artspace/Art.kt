package com.example.artspace

import androidx.annotation.DrawableRes

data class Art(
    var name: String,
    var painter: String,
    var year: Int,
    @DrawableRes var image: Int
)
