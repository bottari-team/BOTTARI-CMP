package com.bottari.bottari

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
