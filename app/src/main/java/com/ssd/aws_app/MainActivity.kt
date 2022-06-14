package com.ssd.aws_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView(item_list)
    }

    // recycler view는 cell 목록
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        UserData.notes().observe(
            this,
            Observer<MutableList<UserData.Note>> { notes ->
                Log.d(TAG, "Note observer received ${notes.size} notes")

                recyclerView.adapter = NoteRecyclerViewAdapter(notes)
            })
    }

}