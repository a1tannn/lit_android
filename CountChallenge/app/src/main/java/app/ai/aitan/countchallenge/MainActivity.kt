package app.ai.aitan.countchallenge

import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.countchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var soundPool: SoundPool

    private val soundResource = R.raw.system39
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var count : Int = 0

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()

        soundPool.load(applicationContext, soundResource,0)


        binding.plusButton.setOnClickListener {
            count++
            binding.countText.text = count.toString()
            when{
                count%2==0 -> binding.countText.setTextColor(Color.BLUE)
                count%2!=0 -> {
                    binding.countText.setTextColor(Color.RED)
                    soundPool.play(soundResource, 1.0F, 1.0F, 0, 0, 1.0F)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}