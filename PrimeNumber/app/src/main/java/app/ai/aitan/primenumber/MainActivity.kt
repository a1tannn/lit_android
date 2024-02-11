package app.ai.aitan.primenumber

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import app.ai.aitan.primenumber.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val random: Random = Random()
    val quest: IntArray = IntArray(QUESTION_COUNT)
    var point: Int = 0
    var answerCount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        for(i in 0 until QUESTION_COUNT){
            val num = random.nextInt(1000)
            Log.d("Number", "Question$num")
            quest[i] = num
        }

        binding.textView.text = quest[answerCount].toString()
        binding.textView.setTextColor(Color.BLACK)

        binding.maruButton.setOnClickListener {
            var answer = true
            val number = quest[answerCount]

            for(i in 2 until number){
                if(number % i == 0){
                    answer = false
                    break
                }
            }

            if(answer) {
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
            }

            if(answer){
                point++
                Log.d("maru", "correct$number")
            }else{
                Log.d("maru","incorrect$number")
            }
            answerCount++

            if(answerCount == QUESTION_COUNT){
                binding.textView.text = point.toString() + "Score"
                binding.textView.setTextColor(Color.RED)

                point = 0
                answerCount = 0
            }else{
                binding.textView.text = quest[answerCount].toString()
                binding.textView.setTextColor(Color.BLACK)
            }
        }

        binding.badButton.setOnClickListener {
            var answer = false
            val number = quest[answerCount]

            for(i in 2 until number){
                if(number % i == 0){
                    answer = true
                    break
                }
            }

            if(answer) {
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
            }

            if(answer){
                point++
                Log.d("bad", "correct$number")
            }else{
                Log.d("bad","incorrect$number")
            }
            answerCount++

            if(answerCount == QUESTION_COUNT){
                binding.textView.text = "Score : " + point.toString()
                binding.textView.setTextColor(Color.RED)

                point = 0
                answerCount = 0
            }else{
                binding.textView.text = quest[answerCount].toString()
                binding.textView.setTextColor(Color.BLACK)
            }
        }
    }

    companion object{
        private const val QUESTION_COUNT: Int = 10
    }
}