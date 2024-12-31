package com.unicoop.data.article.dto


import com.google.gson.annotations.SerializedName
import com.unicoop.domain.article.model.Article

data class ArticleDTO(
    @SerializedName("uri")
    val uri: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("asset_id")
    val assetId: Long? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("published_date")
    val publishedDate: String? = null,
    @SerializedName("updated")
    val updated: String? = null,
    @SerializedName("section")
    val section: String? = null,
    @SerializedName("subsection")
    val subsection: String? = null,
    @SerializedName("nytdsection")
    val nytdsection: String? = null,
    @SerializedName("adx_keywords")
    val adxKeywords: String? = null,
    @SerializedName("column")
    val column: Any? = null,
    @SerializedName("byline")
    val byline: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("abstract")
    val body: String? = null,
    @SerializedName("des_facet")
    val desFacet: List<String?>? = null,
    @SerializedName("org_facet")
    val orgFacet: List<Any?>? = null,
    @SerializedName("per_facet")
    val perFacet: List<Any?>? = null,
    @SerializedName("geo_facet")
    val geoFacet: List<Any?>? = null,
    @SerializedName("media")
    val media: List<Media?>? = null,
    @SerializedName("eta_id")
    val etaId: Int? = null
) {
    data class Media(
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("subtype")
        val subtype: String? = null,
        @SerializedName("caption")
        val caption: String? = null,
        @SerializedName("copyright")
        val copyright: String? = null,
        @SerializedName("approved_for_syndication")
        val approvedForSyndication: Int? = null,
        @SerializedName("media-metadata")
        val mediaMetadata: List<MediaMetadata?>? = null
    ) {
        data class MediaMetadata(
            @SerializedName("url")
            val url: String? = null,
            @SerializedName("format")
            val format: String? = null,
            @SerializedName("height")
            val height: Int? = null,
            @SerializedName("width")
            val width: Int? = null
        )
    }
    fun toArticle()= Article(
        uri = uri ?: "",
        url = url ?: "",
        id = id ?: 0L,
        assetId = assetId ?: 0L,
        source = source ?: "",
        publishedDate = publishedDate ?: "",
        title = title.orEmpty(),
        body = body.orEmpty()
    )
}