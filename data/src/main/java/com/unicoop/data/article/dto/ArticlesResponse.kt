package com.unicoop.data.article.dto

import com.google.gson.annotations.SerializedName
import com.unicoop.data.common.BaseResponse

data class ArticlesResponse(
    @SerializedName("result")
    val data: List<ArticleDTO>? = null
) : BaseResponse()