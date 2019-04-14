package com.demo.nyarticles.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticlesModel {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result>? = emptyList()

    inner class MediaMetadatum {

        @SerializedName("url")
        @Expose
        var url : String? = null
        @SerializedName("format")
        @Expose
        var format : String ? = null
        @SerializedName("height")
        @Expose
        var height: Int ?= null;
        @SerializedName("width")
        @Expose
        var width : Int?= null;
    }

    inner class Medium {

        @SerializedName("type")
        @Expose
        var type: String ? = null
        @SerializedName("subtype")
        @Expose
        var subtype: String ? = null
        @SerializedName("caption")
        @Expose
        var caption: String ? = null
        @SerializedName("copyright")
        @Expose
        var copyright: String ? = null
        @SerializedName("approved_for_syndication")
        @Expose
        var approvedForSyndication: Int ? = null
        @SerializedName("media-metadata")
        @Expose
        var mediaMetadata : List<MediaMetadatum> = emptyList();

    }

    inner class Result{
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("adx_keywords")
        @Expose
        var adxKeywords: String? = null
        @SerializedName("column")
        @Expose
        var column: Any? = null
        @SerializedName("section")
        @Expose
        var section: String? = null
        @SerializedName("byline")
        @Expose
        var byline: String? = null
        @SerializedName("type")
        @Expose
        var type: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("abstract")
        @Expose
        var _abstract: String? = null
        @SerializedName("published_date")
        @Expose
        var publishedDate: String? = null
        @SerializedName("source")
        @Expose
        var source: String? = null

        @SerializedName("media")
        @Expose
        var media: List<Medium>? = null
    }
}