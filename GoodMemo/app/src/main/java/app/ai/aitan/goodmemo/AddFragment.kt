package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.ai.aitan.goodmemo.databinding.FragmentAddBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        val pref: SharedPreferences = requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        var date = LocalDateTime.now()
        binding.dateText.text = "${date.year}年${date.monthValue}月${date.dayOfMonth}日"

        val memoDate = arguments?.getString("Date") ?: ""
        val memoText = arguments?.getString("Memo") ?: ""

//        Log.d("aitan","Date = ${memoDate}, Memo = ${memoText}")
        if(memoDate != "") binding.dateText.text = memoDate

        binding.editText.setText(memoText)

        binding.editText.setOnClickListener {
            val dateText = binding.dateText.text.toString()
            val text = binding.editText.text.toString()

            var formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒")
            var formattedDateTime = date.format(formatter)

            val editor = pref.edit()

            if(dateText == "" && text == ""){
                editor.putString(formattedDateTime,text).apply()
            }else{
                editor.putString(dateText,text).apply()

            }
            Toast.makeText(context, "メモを追加しました", Toast.LENGTH_SHORT).show()

            date = LocalDateTime.now()
            binding.dateText.text = "${date.year}年${date.monthValue}月${date.dayOfMonth}日"
            binding.editText.setText("")
        }

        return binding.root
    }
}