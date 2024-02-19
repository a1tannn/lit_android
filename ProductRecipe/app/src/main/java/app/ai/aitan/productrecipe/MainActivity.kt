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

    private val list: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        list.add("test1")
        list.add("test2")
        list.add("test3")
        list.add("test4")

        binding.addButton.setOnClickListener {
            val addIntent: Intent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        val pref: SharedPreferences = getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
        val memo: String = pref.getString("Memo","Nodata").toString()
        list.add(memo)
        for(i in list){
            addMemo(i)
            Log.d("aitan",i)
        }
    }

    private fun addMemo(memo: String){
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


    }
}