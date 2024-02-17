package app.myoji.nickname.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import app.myoji.nickname.viewpager.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        binding.viewPager.adapter = ViewPagerAdapter(this)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val maxItemCount: Int = binding.viewPager.adapter?.itemCount ?:0

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.pageNumberTextView.text = "${position + 1} / $maxItemCount"

                when(position){
                    maxItemCount - 1 -> binding.nextButton.text = "Finish"
                    else -> binding.nextButton.text = "Next"
                }
            }
        })

        binding.nextButton.setOnClickListener {
            if(binding.viewPager.currentItem == maxItemCount - 1){
                finish()
            }else {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
        }

        binding.backButton.setOnClickListener {
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
        }
    }
}