package com.mask.pagingdemo.db

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import com.mask.pagingdemo.Note

/**
 * Created by Manokar on 5/14/18.
 */
@Database(entities = [(Note::class)], version = 1)
abstract class NoteDb : RoomDatabase(){

    companion object {

        val DB_NAME = "notes"
        private var instance : NoteDb? = null

        fun getInstance(context: Context) : NoteDb{
            if(instance == null){
                instance = create(context)
            }
            return instance!!
        }

        private fun create(context: Context) : NoteDb {
            return Room.databaseBuilder(context, NoteDb::class.java, DB_NAME)
                    .build()
        }
    }

    abstract fun getNoteDao() : NoteDao


}