package com.task.psinteractive.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowInfo(
    val imageUrl: String?,
    val startTime: Long?,
    val endTime: Long?,
    val title: String?,
    val episodeTitle: String?
)