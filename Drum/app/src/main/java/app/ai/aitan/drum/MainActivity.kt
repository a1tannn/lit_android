package app.ai.aitan.drum

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.drum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mSoundPool: SoundPool
    private lateinit var mSoundID: Array<Int?>

    private val mSoundResource = arrayOf(
        R.raw.cymbal1,
        R.raw.cymbal2,
        R.raw.cymbal3,
        R.raw.tom1,
        R.raw.tom2,
        R.raw.tom3,
        R.raw.hihat,
        R.raw.bass,
        R.raw.snare
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.cymbal1ImageButton.setOnClickListener {
            if(mSoundID[0] != null) {
                mSoundPool.play(mSoundID[0] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.cymbal2ImageButton.setOnClickListener {
            if(mSoundID[1] != null) {
                mSoundPool.play(mSoundID[1] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.cymbal3ImageButton.setOnClickListener {
            if(mSoundID[2] != null) {
                mSoundPool.play(mSoundID[2] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.tom1ImageButton.setOnClickListener {
            if(mSoundID[3] != null) {
                mSoundPool.play(mSoundID[3] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.tom2ImageButton.setOnClickListener {
            if(mSoundID[4] != null) {
                mSoundPool.play(mSoundID[4] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.tom3ImageButton.setOnClickListener {
            if(mSoundID[5] != null) {
                mSoundPool.play(mSoundID[5] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.hihatImageButton.setOnClickListener {
            if(mSoundID[6] != null) {
                mSoundPool.play(mSoundID[6] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.bassImageButton.setOnClickListener {
            if(mSoundID[7] != null) {
                mSoundPool.play(mSoundID[7] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }

        binding.snareImageButton.setOnClickListener {
            if(mSoundID[8] != null) {
                mSoundPool.play(mSoundID[8] as Int, 1.0F, 1.0F, 0, 0, 1.0F)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()

        mSoundPool = SoundPool.Builder().setAudioAttributes(audioAttributes)
            .setMaxStreams(mSoundResource.size).build()

        mSoundID = arrayOfNulls(mSoundResource.size)

        for(i in mSoundResource.indices){
            mSoundID[i] = mSoundPool.load(applicationContext, mSoundResource[i], 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSoundPool.release()
    }
}