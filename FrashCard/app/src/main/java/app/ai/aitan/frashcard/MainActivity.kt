package app.ai.aitan.frashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import app.ai.aitan.frashcard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val happy = Word(R.drawable.randy_happy_image, "嬉しい", "happy")
        val normal = Word(R.drawable.randy_normal_image, "普通", "normal")
        val sad = Word(R.drawable.randy_sad_image, "悲しい", "sad")

        addWord(happy)
        addWord(normal)
        addWord(sad)
    }
    private fun addWord(word: Word){
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL

        val nameTextView = TextView(this)
        nameTextView.text = word.name
        nameTextView.textSize = 30.0f

        val imageView = ImageView(this)
        imageView.setImageResource(word.resID)

        val nameEnTextView = TextView(this)
        nameEnTextView.text = word.nameEn
        nameEnTextView.textSize = 30.0f

        layout.addView(nameTextView)
        layout.addView(imageView)
        layout.addView(nameEnTextView)

        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.gravity = Gravity.CENTER_VERTICAL
        nameTextView.layoutParams = lp

        val lp2 = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp2.gravity = Gravity.CENTER_VERTICAL
        nameEnTextView.layoutParams = lp2

        binding.container.addView(layout)
    }
}