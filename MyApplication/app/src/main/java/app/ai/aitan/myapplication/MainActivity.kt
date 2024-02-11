package app.ai.aitan.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.button.setOnClickListener {
            binding.textView.text = "Clicked!"
        }
    }

}