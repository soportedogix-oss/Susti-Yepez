package com.idat.appyepezjorge

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.idat.appyepezjorge.databinding.ActivityRegistroBinding
import com.idat.appyepezjorge.db.MascotaDatabase
import com.idat.appyepezjorge.model.Mascota
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var db: MascotaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MascotaDatabase.getDatabase(this)

        binding.btnGuardar.setOnClickListener {
            guardarMascota()
        }

        binding.btnIngresar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun guardarMascota() {

        val codigo = binding.etCodigo.text.toString()
        val nombre = binding.etNombre.text.toString()
        val tipo = binding.etTipo.text.toString()
        val edad = binding.etEdad.text.toString()

        if (codigo.isEmpty() || nombre.isEmpty() || tipo.isEmpty() || edad.isEmpty()) {
            Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val edadInt = edad.toInt()

        if (edadInt <= 0) {
            Toast.makeText(this, "Edad inválida", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val existe = db.mascotaDao().buscarPorCodigo(codigo)

            if (existe != null) {
                runOnUiThread {
                    Toast.makeText(this@RegistroActivity, "Código repetido", Toast.LENGTH_SHORT).show()
                }
            } else {
                db.mascotaDao().insertar(
                    Mascota(codigo, nombre, tipo, edadInt)
                )

                runOnUiThread {
                    Toast.makeText(this@RegistroActivity, "Guardado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}