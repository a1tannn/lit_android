package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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

    private var memoCount = 0
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

            if (!pref.getBoolean("dialogShown", false)) {
                showMyDialog()
            }
        }else if(memoCount % 3 != 0) {
            progressCount = (memoCount % 3) * 33
            binding.growCountText.text = "${3 - memoCount % 3}"
        }else{
            binding.growCountText.text = "${3 - memoCount % 3}"

            pref.edit().putBoolean("dialogShown",false).apply()
        }
        binding.progressBar.setProgress(progressCount,true)

        Log.d("aitan","memoCount = $memoCount")
        Log.d("aitan","progressCount = $progressCount")

        changeImage()
    }

    private fun changeImage() {
        Log.d("aitan","flower in memoCount = $memoCount")

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

    private fun showMyDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setTitle("おめでとうございます！")
            .setMessage("花が咲きました！" +
                    "引き続き、ポジティブにいきましょう！")
            .setPositiveButton("OK") { dialog, which ->
                with(pref.edit()) {
                    putBoolean("dialogShown", true)
                    apply()
                }
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}