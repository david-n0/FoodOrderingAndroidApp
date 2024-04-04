package rs.raf.projekat2.david_nikolic_9419.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var imeTxt: EditText

    private lateinit var pinTv: EditText
    private lateinit var prijavaBtn: Button

    private val pinKod = "0000"

    private lateinit var sharedPreferences: SharedPreferences
    private var ulogovan = false
    private var ulogovanCheck = false

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initView()
        initListeners()
    }

    private fun initView() {
        imeTxt = binding.imeTv
        pinTv = binding.pinTv
        prijavaBtn = binding.prijavaBtn
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        ulogovan = false
        ulogovanCheck = sharedPreferences.getBoolean("ULOGOVAN", false)
    }

    private fun initListeners() {
        if (ulogovanCheck) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        prijavaBtn.setOnClickListener {
            val ime = imeTxt.text.toString()

            val pin = pinTv.text.toString()

            if (ime.isNotEmpty()) {
                if (pin.isNotEmpty()) {
                    if (pin.length == 4) {
                        if (pin == pinKod) {

                            ulogovan = true

                            val editor = sharedPreferences.edit()
                            editor.putString("IME", ime)
                            editor.putString("PIN", pin)
                            editor.putBoolean("ULOGOVAN", ulogovan)
                            editor.apply()

                            Toast.makeText(
                                this@LoginActivity,
                                R.string.prijava_uspela,
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                R.string.pin_pogresan,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            R.string.pin_duzina,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, R.string.pin_prazan, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this@LoginActivity, R.string.prijava_nije_uspela, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}