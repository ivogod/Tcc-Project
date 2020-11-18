package br.com.tcccomfirestore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ActivityResultados : AppCompatActivity() {

    lateinit var adapter: ResultsAdapter
    private val db = FirebaseFirestore.getInstance()
    private val phRef = db.collection("resultados_anteriores")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val btnHome: FloatingActionButton = findViewById(R.id.floatingActionButtonHome)

        btnHome.setOnClickListener{
            val it = Intent(this@ActivityResultados, MainActivity::class.java)
            startActivity(it)

        }

        setUpRecyclerView()

    }//Fim do onCreate

    private fun setUpRecyclerView() {
        val query: Query = phRef//.orderBy("priority", Query.Direction.DESCENDING)
        val options: FirestoreRecyclerOptions<Results> = FirestoreRecyclerOptions
            .Builder<Results>()
            .setQuery(query, Results::class.java)
            .build()

        adapter = ResultsAdapter(options)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()

    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()

    }
}