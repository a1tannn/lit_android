package app.ai.aitan.countchallenge

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.ai.aitan.countchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var soundPool: SoundPool

    private var soundResource: Int = 0

    private var count : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val num = pref.getInt("Count",0)
        count = num
        binding.countText.text = count.toString()

        binding.plusButton.setOnClickListener {
            count++
            binding.countText.text = count.toString()
            when{
                count%2==0 -> binding.countText.setTextColor(Color.BLUE)
                count%2!=0 -> {
                    binding.countText.setTextColor(Color.RED)
                    soundPool.play(soundResource,1.0F, 1.0F, 0, 0, 1.0F)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1).build()

        soundResource = soundPool.load(applicationContext, R.raw.system39, 0)
    }

    override fun onStop() {
        super.onStop()

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("Count",count)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}