package com.task.psinteractive.data

import android.app.Application
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.task.psinteractive.R
import org.json.JSONObject
import java.lang.reflect.Type

object JsonParser {
    private const val OBJECT_SHOWS = "shows"
    private const val OBJECT_AT = "at"

    private fun readRawJson(context: Application) =
        context.resources.openRawResource(R.raw.data)
            .bufferedReader().use {
                it.readText()
            }

    fun getList(context: Application, sortAsc: Boolean?): List<ShowInfo> {
        val jsonString = readRawJson(context)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listMyData: Type = Types.newParameterizedType(
            MutableList::class.java,
            ShowInfo::class.java
        )
        val adapter: JsonAdapter<List<ShowInfo>> = moshi.adapter(listMyData)
        val jObject = JSONObject(jsonString)
        val showsObject = jObject.getJSONObject(OBJECT_SHOWS)
        val showsArray = showsObject.getJSONArray(OBJECT_AT)
        var showsList = adapter.fromJson(showsArray.toString()) as List<ShowInfo>
        // return the list with duplicates removed
        showsList = showsList
            .toSet()
            .toMutableList()

        // sort the list
        if (sortAsc == true)
            showsList = showsList.sortedBy {
                it.startTime
            }
        else if (sortAsc == false)
            showsList = showsList.sortedByDescending {
                it.startTime
            }

        return showsList
    }
}