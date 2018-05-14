package com.mask.pagingdemo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Manokar on 5/14/18.
 */
@Entity
data class Note(
        @PrimaryKey val id:Int,
        val note:String)