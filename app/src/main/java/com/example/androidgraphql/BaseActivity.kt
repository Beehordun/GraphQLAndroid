package com.example.androidgraphql

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidgraphql.di.AppComponent


abstract class BaseActivity : AppCompatActivity() {

    val appComponent: AppComponent
        get() = (application as MainApplication).appComponent

    protected abstract fun performInjection()

    override fun onCreate(savedInstanceState: Bundle?) {
        performInjection()
        super.onCreate(savedInstanceState)
    }
}
