package com.mask.pagingdemo.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mask.pagingdemo.Note

/**
 * Created by Manokar on 5/14/18.
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes() : DataSource.Factory<Int, Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note : Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNotes(notes : List<Note>)

}