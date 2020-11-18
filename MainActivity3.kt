package br.com.tcccomfirestore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {

    private lateinit var rgb: String
    private var ph = 0.0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //Escondendo a ActionBar
        supportActionBar!!.hide()

        //Ligação XML -> Class
        val btnHome: FloatingActionButton = findViewById(R.id.buttonHome)
        val btnSave: FloatingActionButton = findViewById(R.id.buttonSave)

        var tipo = ""

        if (ph <= 6.0) {
            tipo = "Ácido"
        }
        else if (ph >= 8.0) {
            tipo = "Base"
        }
        else {
            tipo = "Neutro"
        }

        //Pegando dados de outra activity
        rgb = intent.getStringExtra("rgb").toString()
        ph = intent.getStringExtra("ph").toString().toDouble()

        //Alterando o texto da TextView
        textViewRgb.text = "Código RGB: " + rgb
        textViewPh.text = "pH = $ph | $tipo"

        //Navegando entre activities
        btnHome.setOnClickListener {
            val it = Intent(this@MainActivity3, MainActivity::class.java)
            startActivity(it)
        }

        btnSave.setOnClickListener {
            val i = Intent(this, SaveResultActivity::class.java)
            i.putExtra("", rgb)
            startActivity(i)
        }

    }//Fim onCreate
}