package app.ai.aitan.productrecipe

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.ai.aitan.productrecipe.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        val textTitle = intent.getStringExtra("title") ?: ""
        val textMemo = intent.getStringExtra("memo") ?: ""
        val cnt = intent.getIntExtra("Count", 0)
        val pos = intent.getIntExtra("pos",0)

        binding.titleText.setText(textTitle)
        binding.memoText.setText(textMemo)


        binding.checkButton.setOnClickListener {
            val title = binding.titleText.text.toString()
            val memo = binding.memoText.text.toString()

            val editor = pref.edit()
            val count = pref.getInt("count", 0)

            if(textTitle == "" || textMemo == ""){
                editor.putString(count.toString(), "$title: $memo")
                editor.putInt("count", count + 1)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                editor.putString(cnt.toString(), "$title: $memo").apply()
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("title", title)
                    putExtra("memo", memo)
                    putExtra("count", cnt)
                    putExtra("pos",pos)
                }
                startActivity(intent)
            }
        }
    }
}