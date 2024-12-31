package com.unicoop.nyarticle.ui.home

sealed class HomeEvent {
    data class GetArticles(val period: Int) : HomeEvent()
}