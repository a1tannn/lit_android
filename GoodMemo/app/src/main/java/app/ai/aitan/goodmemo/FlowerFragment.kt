package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ai.aitan.goodmemo.databinding.FragmentFlowerBinding

class FlowerFragment : Fragment() {
    private var _binding: FragmentFlowerBinding? = null
    private val binding get() = _binding!!

    private val imageResources = listOf(
        R.drawable.sprout,
        R.drawable.leaves,
        R.drawable.leaves2,
        R.drawable.bud,
        R.drawable.gerbera
    )
    private lateinit var pref: SharedPreferences

    private var currentImageIndex = 0

    private var memoCount = 0

    private var lastMemoCount: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentFlowerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        memoCount = pref.getInt("MemoCount",0)

        var progressCount = 0

        if(memoCount == 12) {
            progressCount = 99
            binding.growCountText.text = "0"
        }else if(memoCount % 3 != 0) {
            progressCount = (memoCount % 3) * 33
        }
        binding.growCountText.text = "${3 - memoCount % 3}"
        binding.progressBar.setProgress(progressCount,true)
        Log.d("aitan","progressCount = $progressCount")

        changeImage()
    }

    private fun changeImage() {
//        if (memoCount % 3 == 0 && memoCount != lastMemoCount) {
//            currentImageIndex = (memoCount / 3) % imageResources.size
//            binding.imageView.setImageResource(imageResources[currentImageIndex])
//            lastMemoCount = memoCount
//        }else if (lastMemoCount != memoCount)
//            binding.imageView.setImageResource(imageResources[currentImageIndex])
//
        Log.d("aitan","flower in memoCount = $memoCount")
//        Log.d("aitan","flower in lastMemoCount = $lastMemoCount")

        val imageIndex = when (memoCount) {
            in 0..2 -> 0
            in 3..5 -> 1
            in 6..8 -> 2
            in 9..11 -> 3
            12 -> 4
            else -> 0
        }

        binding.imageView.setImageResource(imageResources[imageIndex])
    }

}