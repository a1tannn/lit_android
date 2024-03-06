package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ai.aitan.goodmemo.databinding.FragmentAddBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater).apply {}
        super.onCreateView(inflater, container, savedInstanceState)

        val pref: SharedPreferences = requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        val date = LocalDateTime.now()
        binding.dateText.text = "${date.year}年${date.monthValue}月${date.dayOfMonth}日"
        val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒")
        val formattedDateTime = date.format(formatter)

        binding.editText.setOnClickListener {
            val text = binding.editText.text.toString()

            val editor = pref.edit()
            editor.putString("$formattedDateTime",text)
            editor.apply()
        }

        return binding.root
    }
}