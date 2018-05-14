package com.mask.pagingdemo.arch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.mask.pagingdemo.Note
import com.mask.pagingdemo.db.NoteDao
import com.mask.pagingdemo.paging.NoteBoundryCallback

/**
 * Created by Manokar on 5/14/18.
 */
class MyViewModel(notesDao:NoteDao, callback:NoteBoundryCallback) : ViewModel(){

    val notesList : LiveData<PagedList<Note>> = LivePagedListBuilder(notesDao.getNotes(), 20)
            .setBoundaryCallback(callback)
            .build()
}