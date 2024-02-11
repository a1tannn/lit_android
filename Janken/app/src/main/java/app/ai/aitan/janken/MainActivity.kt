package app.ai.aitan.janken

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.janken.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.guuButton.setOnClickListener {
            binding.playerText.text = "あなたの手はグーです"
            val number:Int = Random.nextInt(3)
            when (number) {
                0 -> {
                    binding.cpuImage.setImageResource(R.drawable.guu)
                    binding.resultText.text = "引き分けです"
                    binding.resultText.setTextColor(Color.GRAY)
                }
                1 -> {
                    binding.cpuImage.setImageResource(R.drawable.choki)
                    binding.resultText.text = "あなたの勝ちです"
                    binding.resultText.setTextColor(Color.RED)
                }
                2 -> {
                    binding.cpuImage.setImageResource(R.drawable.paa)
                    binding.resultText.text = "あなたの負けです"
                    binding.resultText.setTextColor(Color.BLUE)
                }
            }
        }

        binding.chokiButton.setOnClickListener {
            binding.playerText.text = "あなたの手はチョキです"
            val number:Int = Random.nextInt(3)
            when (number) {
                0 -> {
                    binding.cpuImage.setImageResource(R.drawable.guu)
                    binding.resultText.text = "あなたの負けです"
                    binding.resultText.setTextColor(Color.BLUE)
                }
                1 -> {
                    binding.cpuImage.setImageResource(R.drawable.choki)
                    binding.resultText.text = "引き分けです"
                    binding.resultText.setTextColor(Color.GRAY)
                }
                2 -> {
                    binding.cpuImage.setImageResource(R.drawable.paa)
                    binding.resultText.text = "あなたの勝ちです"
                    binding.resultText.setTextColor(Color.RED)
                }
            }
        }

        binding.paaButton.setOnClickListener {
            binding.playerText.text = "あなたの手はパーです"
            val number:Int = Random.nextInt(3)
            when (number) {
                0 -> {
                    binding.cpuImage.setImageResource(R.drawable.guu)
                    binding.resultText.text = "あなたの勝ちです"
                    binding.resultText.setTextColor(Color.RED)
                }
                1 -> {
                    binding.cpuImage.setImageResource(R.drawable.choki)
                    binding.resultText.text = "あなたの負けです"
                    binding.resultText.setTextColor(Color.BLUE)
                }
                2 -> {
                    binding.cpuImage.setImageResource(R.drawable.paa)
                    binding.resultText.text = "引き分けです"
                    binding.resultText.setTextColor(Color.GRAY)
                }
            }
        }
    }
}