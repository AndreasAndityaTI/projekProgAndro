package id.ac.ukdw.fastmenu.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.fastmenu.MenuUtama
import id.ac.ukdw.menuapp.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        val splashDurationMillis = 1500L // 1.5 seconds
        supportActionBar?.title=null
        // Create a thread to wait for the specified duration
        val splashThread = object : Thread() {
            override fun run() {
                try {
                    sleep(splashDurationMillis)
                } catch (e: InterruptedException) {
                    // Handle interruptions if necessary
                    e.printStackTrace()
                } finally {
                    // After the splash duration, start the main activity
                    startActivity(Intent(applicationContext, MenuUtama::class.java))
                    finish() // Close the splash activity so the user can't go back to it
                }
            }
        }

        // Start the thread
        splashThread.start()
    }
}