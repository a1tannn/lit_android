package app.ai.aitan.goodmemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import app.ai.aitan.goodmemo.databinding.CardItemsBinding
import app.ai.aitan.goodmemo.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    private var _bindingCard: CardItemsBinding? = null

    private lateinit var mMemoData: ArrayList<String>
    private lateinit var mDateData: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        _bindingCard = CardItemsBinding.inflate(inflater, container, false)

        mMemoData = ArrayList()
        mDateData = ArrayList()
        val swipeStack = _binding!!.swipeStack
        swipeStack.adapter = SwipeAdapter(mMemoData, mDateData,this, swipeStack)
        getTextData()

        binding.resetButton.setOnClickListener {
            swipeStack.resetStack()
        }

        return binding.root
    }

    private fun getTextData() {
        val pref: SharedPreferences = requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        val str = pref.all
        for(i in str.toSortedMap()){
            mDateData.add(i.key)
            mMemoData.add(i.value.toString())
        }
    }
}