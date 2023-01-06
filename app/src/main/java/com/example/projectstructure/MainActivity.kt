package com.example.projectstructure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.in22labs.tnskill.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}