package rs.raf.projekat2.david_nikolic_9419.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)


        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}