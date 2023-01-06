package com.in22labs.tnskill.base

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.in22labs.tnskill.R
import com.in22labs.tnskill.utills.LanguageUtils
import com.in22labs.tnskill.utills.MyAppUtils
import kotlinx.coroutines.DisposableHandle

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    public var uriPath: Uri? = null
    private var timerHandler: Handler? = null
    var timerRunnable: Runnable? = null
    var eventDisposable: DisposableHandle? = null

    fun registerForTimer(tv: TextView, time: String) {
        timerHandler = Handler(Looper.getMainLooper())
        timerRunnable = object : Runnable {
            override fun run() {
                val (day, hour, min, sec) = MyAppUtils.getTimeLeft(time)
                when {
                    day > 0 -> {
                        tv.text = getString(R.string.time_left_dhms, day, hour, min, sec)
                    }
                    hour > 0 -> {
                        tv.text = getString(R.string.time_left_hms, hour, min, sec)
                    }
                    min > 0 -> {
                        tv.text = getString(R.string.time_left_ms, min, sec)
                    }
                    else -> {
                        tv.text = getString(R.string.time_left_s, sec)
                    }
                }
                timerHandler?.postDelayed(this, 1000)
            }
        }

        timerHandler?.post(timerRunnable!!)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (timerHandler != null) {
            timerHandler?.removeCallbacks(timerRunnable!!)
        }

        if (eventDisposable != null) {
            eventDisposable?.dispose()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (timerHandler != null) {
            timerHandler?.removeCallbacks(timerRunnable!!)
        }

        if (eventDisposable != null) {
            eventDisposable?.dispose()
        }
    }

    fun getLifeCycleOwner(): LifecycleOwner {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lang: String = LanguageUtils.getLanguage(this)
        LanguageUtils.changeLang(this, lang, object : LanguageUtils.OnLanguageChange {
            override fun onLanguageChanged() {
            }
        })
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase != null)
            super.attachBaseContext(LanguageUtils.onAttach(newBase))
        else
            super.attachBaseContext(newBase)
    }

    override fun onClick(v: View?) {
    }
}