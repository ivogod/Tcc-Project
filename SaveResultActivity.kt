package br.com.tcccomfirestore

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.toast_custom_sucesso.*


class SaveResultActivity : AppCompatActivity() {

    private lateinit var rgb: String
    private lateinit var ph: String
    private lateinit var desc: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_result)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Salvar resultado"

        val textPh: TextView = findViewById(R.id.textViewPh2)
        val textRgb: TextView = findViewById(R.id.textViewRgb2)
        desc = findViewById(R.id.editTextDesc)

        ph = "7 (Neutro)"

        //Pegando dados de outra activity
        rgb = intent.getStringExtra("").toString()

        //Alterando o texto da TextView
        textRgb.text = " Código RGB: $rgb"
        textPh.text = "pH = $ph"

    }//Fim do onCreate

    private fun toastSucesso() {
        val viewSucesso = layoutInflater.inflate(
            R.layout.toast_custom_sucesso,
            container_toast_sucesso
        )

        val toast_sucesso = Toast(this)
        toast_sucesso.view = viewSucesso
        toast_sucesso.duration = Toast.LENGTH_LONG
        toast_sucesso.show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.save_result_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }//Fim do onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_result -> {
                saveResult()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }//Fim do onOptionsItemSelected

    private fun saveResult() {
        val ph = ph
        val desc = desc!!.text.toString()

        rgb = intent.getStringExtra("").toString()
        val rgbcode: String = "Código RGB: $rgb"

        val i = Intent(this@SaveResultActivity, ActivityResultados::class.java)

        if (desc.trim().isEmpty()) {
            Toast.makeText(this, "Insira uma descrição!", Toast.LENGTH_SHORT)
                .show()
            return

        } else {

            val phRef = FirebaseFirestore.getInstance()
                .collection("resultados_anteriores")
            phRef.add(Results(ph, desc, rgbcode))
            toastSucesso()
            startActivity(i)

        }
    }//Fim do saveResult
}