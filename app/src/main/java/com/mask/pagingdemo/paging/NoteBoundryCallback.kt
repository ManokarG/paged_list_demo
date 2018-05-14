package com.mask.pagingdemo.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.mask.pagingdemo.Note

/**
 * Created by Manokar on 5/14/18.
 */
class NoteBoundryCallback(val callback:MyCallback) : PagedList.BoundaryCallback<Note>() {

    companion object {
        val NETWORK_PAGE_SIZE = 50
    }

    // keep the last requested page.
// When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        callback.loadItem()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Note) {
        callback.loadItem()
    }

    interface MyCallback{
        fun loadItem()
    }
}