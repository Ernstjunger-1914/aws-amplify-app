package com.ssd.aws_app

import android.graphics.Bitmap
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object UserData {

    private const val TAG: String = "UserData"

    private val _isSignedIn = MutableLiveData<Boolean>(false)
    var isSignedIn: LiveData<Boolean> = _isSignedIn

    fun setSignIn(value: Boolean) {
        // 기본 스레드에서 할당
        _isSignedIn.postValue(value)
    }

    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }

    fun notifyObserver() {
        this._notes.notifyObserver()
    }

    fun notes(): LiveData<MutableList<Note>> = _notes

    fun addNote(n: Note) {
        val notes = _notes.value

        if (notes != null) {
            notes.add(n)
            _notes.notifyObserver()
        } else {
            Log.e(TAG, "addNote: note collection is null")
        }
    }

    fun deleteNote(at: Int): Note? {
        val note = _notes.value?.removeAt(at)
        _notes.notifyObserver()

        return note
    }

    fun resetNote() {
        this._notes.value?.clear()
        _notes.notifyObserver()
    }

    data class Note(
        val id: String,
        val name: String,
        val description: String,
        var imageName: String? = null
    ) {
        override fun toString(): String = name

        var image: Bitmap? = null
    }

}
