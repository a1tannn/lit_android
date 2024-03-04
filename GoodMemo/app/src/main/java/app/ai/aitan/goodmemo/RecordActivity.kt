package app.ai.aitan.goodmemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.ai.aitan.goodmemo.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }
}