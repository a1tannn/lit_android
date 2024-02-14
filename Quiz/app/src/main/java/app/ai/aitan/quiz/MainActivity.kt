package app.ai.aitan.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val quizintent: Intent = Intent(this, QuizActivity::class.java)

        binding.startButton.setOnClickListener {
            startActivity(quizintent)
        }
    }
}