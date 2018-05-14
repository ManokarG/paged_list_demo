package com.mask.pagingdemo.arch

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.PagedList
import android.content.Context
import com.mask.pagingdemo.db.NoteDao
import com.mask.pagingdemo.db.NoteDb
import com.mask.pagingdemo.paging.NoteBoundryCallback

/**
 * Created by Manokar on 5/14/18.
 */
class MyViewModelFactory(val context:Context, val callback:NoteBoundryCallback) : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(NoteDb.getInstance(context).getNoteDao(), callback) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}