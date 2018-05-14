package com.mask.pagingdemo

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Manokar on 5/14/18.
 */
class NotesListAdapter : PagedListAdapter<Note, NotesListAdapter.NoteVH>(DIFF_CALLBACK) {

    companion object {

        val PROGRESS= 1
        val CONTENT= 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>(){
            override fun areContentsTheSame(oldItem: Note?, newItem: Note?): Boolean {
                return oldItem === newItem
            }

            override fun areItemsTheSame(oldItem: Note?, newItem: Note?): Boolean {
                return oldItem?.id == newItem?.id
            }
        }
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        if(holder.itemViewType == PROGRESS){
        } else{
            val note = getItem(position)
            if(note == null){
                holder.clear()
            }else{
                holder.bind(note)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return  if(getItem(position)?.id == -1){
            PROGRESS
        }else{
            CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {

        val view: View?
        if(viewType == PROGRESS){
            view = LayoutInflater.from(parent.context).inflate(R.layout.layout_progress_bar, parent, false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.layout_note_item, parent, false)
        }
        return NoteVH(view)
    }

    class NoteVH(view: View) : RecyclerView.ViewHolder(view){

        private val tvNote = view.findViewById<TextView>(R.id.tvNote)

        fun bind(note:Note){
            tvNote.text = note.note
            Log.d("VH", "Pos "+note.id+" Text : "+note.note)
        }

        fun clear() {
            tvNote.text = ""
        }
    }

}