package id.ac.ukdw.fastmenu.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tasktrackerapp.R
import com.example.tasktrackerapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import id.ac.ukdw.fastmenu.view.SignInActivity
import id.ac.ukdw.fastmenu.view.navigation.HomeFragment
import id.ac.ukdw.fastmenu.view.navigation.ScanFragment
import id.ac.ukdw.fastmenu.view.navigation.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        // Check if user is signed in
        val user = Firebase.auth.currentUser
        if (user == null) {
            // Redirect to SignInActivity if not signed in
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> replaceFragment(HomeFragment())
                R.id.scan_menu -> replaceFragment(ScanFragment())
                R.id.profile_menu -> replaceFragment(SettingsFragment())
                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
