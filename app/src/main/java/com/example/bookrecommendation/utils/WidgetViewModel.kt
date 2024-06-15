package com.example.bookrecommendation.utils

import androidx.annotation.LayoutRes

interface WidgetViewModel {
    @LayoutRes
    fun layoutId(): Int
}