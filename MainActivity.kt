package br.com.tcccomfirestore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val btnProxTela: Button = findViewById(R.id.buttonProxTela)
        val btnAnteriores: Button = findViewById(R.id.buttonResult)

        btnProxTela.setOnClickListener {
            val it = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(it)
        }

        btnAnteriores.setOnClickListener{
            val it = Intent(this@MainActivity, ActivityResultados::class.java)
            startActivity(it)
        }
    }
}