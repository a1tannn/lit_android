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
import app.ai.aitan.productrecipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.addButton.setOnClickListener {
            val addIntent: Intent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.container.removeAllViews()
        val pref: SharedPreferences = getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        val count = pref.getInt("count",0)
        for(i in 1..count){
            val memo = pref.getString("$i","Nodata")
            addMemo(memo.toString(),i)
            Log.d("aitan","$i")
        }
    }

    private fun addMemo(memo: String, index: Int){
        val memoTextView = TextView(this)
        memoTextView.text = memo
        memoTextView.textSize = 30.0f

        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.gravity = Gravity.CENTER_VERTICAL
        memoTextView.layoutParams = lp

        binding.container.addView(memoTextView)

        memoTextView.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java).apply {
                putExtra("String", memo)
                putExtra("Num", index)
                Log.d("aitan","$index $memo")
            }
            startActivity(intent)
        }
    }
}