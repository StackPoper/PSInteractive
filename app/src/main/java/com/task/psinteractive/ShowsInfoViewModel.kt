package com.task.psinteractive

import android.app.Application
import androidx.lifecycle.*
import com.task.psinteractive.data.JsonParser
import com.task.psinteractive.data.ShowInfo
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the fragment
 */
class ShowsInfoViewModel(application: Application): AndroidViewModel(application) {

    private val _shows = MutableLiveData<List<ShowInfo>>()
    val shows: LiveData<List<ShowInfo>> = _shows

    init {
        getShowsInfo(null)
    }

    // Calls the json parser and updates the data that will be displayed. Makes sure all exceptions will be attended accordingly.
    fun getShowsInfo(sortAsc: Boolean?) {
        viewModelScope.launch {
            try {
                _shows.value = JsonParser.getList(getApplication<Application>(), sortAsc)
            } catch (t: Throwable) {
                val showInfo = ShowInfo(null, 0, 0, null, null )
                _shows.value = listOf(showInfo)
            }
        }
    }
}