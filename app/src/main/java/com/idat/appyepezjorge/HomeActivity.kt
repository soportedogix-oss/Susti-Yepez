package com.idat.appyepezjorge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idat.appyepezjorge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment inicial
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ApiFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_api -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, ApiFragment())
                        .commit()
                    true
                }

                R.id.menu_mascotas -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, MascotasFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}