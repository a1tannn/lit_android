package app.ai.aitan.goodmemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.goodmemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
}