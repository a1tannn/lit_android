package app.ai.aitan.productrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        var list = arrayOf("test1", "test2", "test3", "test4")
        for (i in 0..<list.size){
            addMemo(list[i])
        }

        var memo: String
        binding.addButton.setOnClickListener {
            if(binding.editText.text != null){
                memo = binding.editText.text.toString()
                addMemo(memo)
            }
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