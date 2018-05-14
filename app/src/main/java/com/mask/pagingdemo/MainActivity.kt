package com.mask.pagingdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mask.pagingdemo.arch.MyViewModel
import com.mask.pagingdemo.arch.MyViewModelFactory
import com.mask.pagingdemo.db.NoteDb
import com.mask.pagingdemo.paging.NoteBoundryCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

/*    var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0*/

    private var isRequestInProgress = false

    private lateinit var adapter : NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MyViewModelFactory(this@MainActivity, NoteBoundryCallback(object : NoteBoundryCallback.MyCallback{
            override fun loadItem() {
                val list = adapter.currentList
                val note = Note(-1, "")
                list?.add(note)
                adapter.submitList(list)

                if(isRequestInProgress) return

                isRequestInProgress = true

                insertNewData()
            }
        }))

        val viewModel = ViewModelProviders.of(this, factory)
                .get(MyViewModel::class.java)

        switchNetworkScrolling.setOnCheckedChangeListener { buttonView, isChecked ->

        }

        switchNetworkScrolling.isChecked = true

        adapter = NotesListAdapter()
        recyclerNotes.layoutManager = LinearLayoutManager(this@MainActivity)

        recyclerNotes.adapter = adapter

        viewModel.notesList.observe(this, Observer<PagedList<Note>> {
            Toast.makeText(baseContext, "Db loaded"+it?.size, Toast.LENGTH_SHORT).show()
            isRequestInProgress = false
            adapter.submitList(it)
        })

//        val layoutManager = recyclerNotes.layoutManager as LinearLayoutManager

        /*recyclerNotes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    visibleItemCount = recyclerView.layoutManager.childCount
                    totalItemCount = recyclerView.layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false

                        }
                    }
                }
            }
        })*/

        btnUpdate.setOnClickListener {
            insertNewData( )
        }


    }

    private fun insertNewData() {
        val adapter = recyclerNotes.adapter as NotesListAdapter

        val list = mutableListOf<Note>()

        Toast.makeText(this@MainActivity, "Count "+adapter.itemCount,Toast.LENGTH_SHORT).show()
        for(i in adapter.itemCount..adapter.itemCount+100){
            val note = Note(i, "Note "+i)
            list.add(note)
        }

        AsyncTask.execute({
            NoteDb.getInstance(this@MainActivity).getNoteDao().insertAllNotes(list)
        })
    }
}
