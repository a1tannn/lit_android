package app.ai.aitan.count

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.count.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var count:Int = 0

        binding.plusButton.setOnClickListener {
            count++
            updateText(count)
        }

        binding.minusButton.setOnClickListener {
            count--
            updateText(count)
        }

        binding.clearButton.setOnClickListener {
            count = 0
            updateText(count)
        }
    }
    fun updateText(number: Int){
        binding.textView.text = number.toString()
        if(number!=0 && number%3==0){
            binding.textView.setTextColor(Color.GREEN)
        }else{
            binding.textView.setTextColor(Color.BLACK)
        }
    }
}