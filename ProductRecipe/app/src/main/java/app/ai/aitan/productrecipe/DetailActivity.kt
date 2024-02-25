package app.ai.aitan.productrecipe

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import app.ai.aitan.productrecipe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val title = intent.getStringExtra("title") ?: ""
        val memo = intent.getStringExtra("memo") ?: ""
        val count = intent.getIntExtra("count", 0)
        val pos = intent.getIntExtra("pos",0)

        binding.titleText.text = title
        binding.memoText.text = memo

        binding.checkButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java).apply {
                putExtra("title", title)
                putExtra("memo", memo)
                putExtra("Count", count)
                putExtra("pos",pos)
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
                val title = intent.getStringExtra("title") ?: ""
                val memo = intent.getStringExtra("memo") ?: ""
                val count = intent.getIntExtra("count", 0)
                val pos = intent.getIntExtra("pos",0)

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("${title}を削除しますか")
                    .setPositiveButton("削除する") { dialog, which ->
                        val pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
                        pref.edit().remove("$count").apply()

                        val sortedKeys = pref.all.keys.filter { it != "count" }.sortedBy { it.toInt() }
                        val memos = sortedKeys.map { pref.getString(it, "")!! }

                        customAdapter = CustomAdapter(memos)
                        customAdapter.notifyItemRemoved(pos)

                        Toast.makeText(applicationContext, "${title}を削除しました", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }.setNegativeButton("削除しない") { dialog, which ->
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}