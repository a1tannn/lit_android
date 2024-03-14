package app.ai.aitan.goodmemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import kotlin.random.Random

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private var memoCount:Int = 0

    private val hint = arrayOf<String>("ラッキーだったことは？","今日できたことは？","感謝した/されたことは？","体調はどう？","今日何食べた？","何か感じたことは？")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref: SharedPreferences = requireActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val prefMemo: SharedPreferences = requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        var date = LocalDateTime.now()
        binding.dateText.text = "${date.year}年${date.monthValue}月${date.dayOfMonth}日"

        val memoDate = arguments?.getString("Date") ?: ""
        val memoText = arguments?.getString("Memo") ?: ""

        if(memoDate != "") binding.dateText.text = memoDate

        binding.editText.setText(memoText)

        memoCount = pref.getInt("MemoCount",0)
        Log.d("aitan","add in memoCount = $memoCount")

        binding.hintText.setOnClickListener {
            val random: Int = Random.nextInt(hint.size)
            binding.hintText.text = "ヒント:${hint[random]}"
        }

        binding.editText.setOnClickListener {
            val dateText = binding.dateText.text.toString()
            val text = binding.editText.text.toString()

            val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒")
            val formattedDateTime = date.format(formatter)

            val editor = prefMemo.edit()

            if(text == "")
                return@setOnClickListener
            else if(dateText == "${date.year}年${date.monthValue}月${date.dayOfMonth}日")
            {
                editor.putString(formattedDateTime,text).apply()
                memoCount++
                if(memoCount > 12) memoCount = 1
                pref.edit().putInt("MemoCount",memoCount).apply()
                Toast.makeText(context, "メモを追加しました", Toast.LENGTH_SHORT).show()
            }else{
                editor.putString(dateText,text).apply()
                Toast.makeText(context, "メモを編集しました", Toast.LENGTH_SHORT).show()
            }

            date = LocalDateTime.now()
            binding.dateText.text = "${date.year}年${date.monthValue}月${date.dayOfMonth}日"
            binding.editText.setText("")

            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}