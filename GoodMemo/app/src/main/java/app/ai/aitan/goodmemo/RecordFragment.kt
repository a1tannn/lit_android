package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import app.ai.aitan.goodmemo.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private lateinit var binding: FragmentRecordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRecordBinding.inflate(layoutInflater).apply {}

        val pref: SharedPreferences = requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        val str = pref.all
        for(i in str.toSortedMap()){
            binding.memoDateText.text = i.key
            binding.memoText.text = i.value.toString()
        }

        return binding.root
    }
}