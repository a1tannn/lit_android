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
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        count = pref.getInt("count",0)

        val str = intent.getStringExtra("String")
        val num = intent.getIntExtra("Num",0)
        Log.d("aitan","$str $num")
        binding.memoText.setText(str)

        binding.checkButton.setOnClickListener {
            val memo = binding.memoText.text.toString()
            if(memo.isNotEmpty()){
                val editor = pref.edit()
                if(str == null || num == 0){
                    count += 1
                    editor.putInt("count",count)
                    editor.putString("$count", memo).apply()
                }else{
                    editor.putString("$num", memo).apply()
                }
                finish()
            }
        }

        binding.deleteButton.setOnClickListener {
            val editor = pref.edit()
            if(str == null || num == 0){
                finish()
            }else{
                editor.remove("$num")
                count -= 1
                editor.putInt("count",count).apply()
                Log.d("aitan","count =  $count")
                finish()
            }
        }
    }
}