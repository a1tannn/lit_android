package app.ai.aitan.countchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.countchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var count : Int = 0

        binding.plusButton.setOnClickListener {
            count++
            binding.countText.text = count.toString()
        }
    }
}