package app.ai.aitan.productrecipe

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ai.aitan.productrecipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        pref = getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sortedKeys = pref.all.keys.filter { it != "count" }.sortedBy { it.toInt() }
        val memos = sortedKeys.map { pref.getString(it, "")!! }


        customAdapter = CustomAdapter(memos)
        recyclerView.adapter = customAdapter

        binding.addButton.setOnClickListener {
            val addIntent: Intent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        pref = getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sortedKeys = pref.all.keys.filter { it != "count" }.sortedBy { it.toInt() }
        val memos = sortedKeys.map { pref.getString(it, "")!! }

        customAdapter = CustomAdapter(memos)
        recyclerView.adapter = customAdapter
        customAdapter.notifyDataSetChanged()

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, recyclerView) { view, position ->
                val memoText = memos[position]
                val key = sortedKeys[position].toInt()
                val (title, memo) = memoText.split(":").map { it.trim() }

                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("title", title)
                    putExtra("memo", memo)
                    putExtra("count", key)
                    putExtra("pos",position)
                }
                startActivity(intent)
            }
        )
    }


}