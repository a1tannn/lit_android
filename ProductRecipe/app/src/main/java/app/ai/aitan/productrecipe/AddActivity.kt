package app.ai.aitan.productrecipe

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.productrecipe.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        var memo: String
        binding.checkButton.setOnClickListener {
            memo = binding.memoText.text.toString()

            if(memo.isNotEmpty()){
                val editor = pref.edit()
                editor.putString("Memo", memo)
                editor.apply()
            }

            val memoIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(memoIntent)
        }
    }
}