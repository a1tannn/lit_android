package app.ai.aitan.goodmemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.goodmemo.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
}