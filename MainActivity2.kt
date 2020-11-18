package br.com.tcccomfirestore

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {

    private val IMAGE_CAPTURE_CODE = 1001;
    private val PERMISSION_CODE = 1000;
    var image_uri: Uri? = null
    var calculatePh = 0.0
    var ph = 0

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar!!.hide()

        val btnVoltar: Button = findViewById(R.id.buttonVoltar)
        val btnCamera: Button = findViewById(R.id.buttonCamera)
        val btnResult: Button = findViewById(R.id.buttonResult)
        val tVInfo1: TextView = findViewById(R.id.tVInfo1)
        val tVInfo2: TextView = findViewById(R.id.tVInfo2)
        val tVInfo3: TextView = findViewById(R.id.tVInfo3)
        val iV: ImageView = findViewById(R.id.imageView)
        val cV: View = findViewById(R.id.colorView)
        var rgb = ""

        btnVoltar.setOnClickListener {
            val it = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(it)

            tVInfo1.text = "Escolha uma imagem da câmera ↑"

        }

        btnResult.setOnClickListener {

            //Mandando dados e indo para outra activity
            val i = Intent(this, MainActivity3::class.java)
            i.putExtra("rgb", rgb)
            i.putExtra("ph", ph)
            startActivity(i)

            val intent = Intent(this, SaveResultActivity::class.java)
            intent.putExtra("", rgb)

            tVInfo1.setText("Tire uma foto com a câmera ↑")

            phCalculation()
        }

        btnCamera.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {

                    val permission = arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    //permissions request
                    requestPermissions(permission, PERMISSION_CODE)

                }
                else{
                    openCamera()
                }
            }

            else{
                openCamera()
            }

            tVInfo1.setText("")
            tVInfo2.setText("Passe o dedo sobre a imagem ↑\n")
            tVInfo3.setText("Aqui aparecerá a cor selecionada ↓")

        }

        iV.isDrawingCacheEnabled = true
        iV.buildDrawingCache(true)

        iV.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                val bitmap = iV.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                //Obtendo valores RGB do pixel tocado
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)

                rgb = "$r, $g, $b"
                calculatePh = "$r$g$b".toDouble()

                //Obtendo valores HEX do pixel tocado
                //val hex = "#" + Integer.toHexString(pixel)

                //Colocando a cor como fundo
                cV.setBackgroundColor(Color.rgb(r, g, b))
                tVInfo3.setTextColor(Color.rgb(r, g, b))
            }

            true
        }
    }//Fim onCreate

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Nova Foto")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Na camera")

        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        //Camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)

        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray

    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PERMISSION FROM POPUP WAS GRANTED
                    openCamera()

                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            imageView.setImageURI(image_uri)
        }
    }

    fun phCalculation() {
        val ph1 = 2152019
        val ph2 = 14522128
        val ph3 = 7121114
        val ph4 = 8557123
        val ph5 = 14461193
        val ph6 = 12457173
        val ph7 = 10979146
        val ph8 = 6524121
        val ph9 = 7121136
        val ph10 = 7042119
        val ph11 = 5643112
        val ph12 = 344547
        val ph13 = 584829

        val phArray = arrayOf(ph1, ph2, ph3, ph4, ph5, ph6, ph7, ph8, ph9, ph10, ph11, ph12, ph13)
        val arrayDiferenca = mutableListOf<Double>() //array que vai armazenar a diferença
        val numero = calculatePh //número que vc deseja comparar

        //percorre o array
        phArray.forEach {
            var diferenca = numero - it

            if (diferenca < 0) diferenca * -1 //aqui estamos deixando todos os numeros positivos
            arrayDiferenca.add((diferenca).toDouble()) //adicionando a diferença em outro array

        }

        //obter o menor valor de um array, nesse caso a menor diferença será o número mais proximo
        val menorDiferenca = arrayDiferenca.min()

        //com base na posicao da menor diferença é possivel saber o valor mais proximo
        val valorProx = phArray[arrayDiferenca.indexOf(menorDiferenca)]

        if (valorProx == ph1) {
            ph = 1
        }
        else if (valorProx == ph1) {
            ph = 1
        }
        else if (valorProx == ph2) {
            ph = 2
        }
        else if (valorProx == ph3) {
            ph = 3
        }
        else if (valorProx == ph4) {
            ph = 4
        }
        else if (valorProx == ph5) {
            ph = 5
        }
        else if (valorProx == ph6) {
            ph = 6
        }
        else if (valorProx == ph7) {
            ph = 7
        }
        else if (valorProx == ph8) {
            ph = 8
        }
        else if (valorProx == ph9) {
            ph = 9
        }
        else if (valorProx == ph10) {
            ph = 10
        }
        else if (valorProx == ph11) {
            ph = 12
        }
        else {
            ph = 13
        }
    }
}
