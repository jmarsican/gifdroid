package com.javiermarsicano.gifdroid.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID


abstract class BaseActivity : AppCompatActivity() {

    private lateinit var activityId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityId(savedInstanceState)
    }

    private fun initActivityId(savedInstanceState: Bundle?) {
        savedInstanceState?.getString(KEY_ACTIVITY_ID, null)?.let { id ->
            activityId = id
        } ?: run {
            activityId = UUID.randomUUID().toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_ACTIVITY_ID, activityId)
        super.onSaveInstanceState(outState)
    }

    fun hideKeyboard() {
        currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {
        private const val KEY_ACTIVITY_ID = "key_activity_id"
    }
}
