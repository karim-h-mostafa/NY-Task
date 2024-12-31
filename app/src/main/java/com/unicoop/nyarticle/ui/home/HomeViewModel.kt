package com.unicoop.nyarticle.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unicoop.core.doIfLoading
import com.unicoop.core.doIfSuccess
import com.unicoop.core.doIfError
import com.unicoop.domain.article.model.Article
import com.unicoop.domain.article.usecase.GetArticlesUseCase
import com.unicoop.domain.article.usecase.GetCombinedArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getCombinedArticles: GetCombinedArticles
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private fun updateState(update: HomeState.() -> HomeState) = viewModelScope.launch {
        _state.emit(state.value.update())
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetArticles -> getArticles(event.period)
        }
    }

    private fun getArticles(period: Int) = viewModelScope.launch {
        updateLoading(true)
        getArticlesUseCase(period).combine(
            getArticlesUseCase(period + 2)
        ) { firstCall, secondCall ->
            getCombinedArticles(firstCall, secondCall).collect {

                with(it) {
                    doIfSuccess {
                        pushArticles(it)
                    }
                    doIfError {
                        updateError(it.message ?: "Unknown Error")
                    }
                }
                updateLoading(false)
            }
        }
    }

    private fun updateLoading(isLoading: Boolean) = updateState {
        copy(isLoading = isLoading)
    }

    private fun pushArticles(articles: List<Article>) = updateState {
        copy(articles = articles)
    }

    private fun updateError(error: String) = updateState {
        copy(error = error)
    }
}