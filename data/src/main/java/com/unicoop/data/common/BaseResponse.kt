package com.unicoop.data.common

open class BaseResponse(
    open val status: String? = null,
    open val copyright: String? = null,
    open val numResults: Int? = null,
)