package app.ai.aitan.countchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.countchallenge.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val mainIntent: Intent = Intent(this, MainActivity::class.java)

        binding.mainButton.setOnClickListener {
            startActivity(mainIntent)
        }
    }
}