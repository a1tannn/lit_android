package app.ai.aitan.goodmemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class SwipeAdapter(
    private val mMemoData:List<String>,
    private val mDateData:List<String>,
    private val fragment: Fragment,
    private val mSwipeStack: SwipeStack):
    BaseAdapter(){
    override fun getCount(): Int {
        return mMemoData.size
    }

    override fun getItem(position: Int): Any {
        return mMemoData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View, parent: ViewGroup?): View {
        var convertView: View = convertView
        convertView = LayoutInflater.from(parent!!.context).inflate(R.layout.card_items, parent, false)

        val pref = fragment.requireActivity().getSharedPreferences("Memo", Context.MODE_PRIVATE)

        val textDateView = convertView.findViewById(R.id.memo_date_text) as TextView
        textDateView.setText(mDateData[position])

        val textViewCard = convertView.findViewById(R.id.memo_text) as TextView
        textViewCard.setText(mMemoData[position])

        val editButton = convertView.findViewById<ImageButton>(R.id.edit_button)
        editButton.setOnClickListener {
            val args = Bundle()
            args.putString("Date", mDateData[position])
            args.putString("Memo", mMemoData[position])

            val addFragment = AddFragment()
            addFragment.arguments = args

            val transaction: FragmentTransaction = fragment.requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, addFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val deleteButton = convertView.findViewById<ImageButton>(R.id.delete_button)
        deleteButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
            builder
                .setMessage("${mMemoData[position]}")
                .setTitle("${mDateData[position]}を削除しますか？")
                .setPositiveButton("削除する") { dialog, which ->
                    pref.edit().remove("${mDateData[position]}").apply()
                    Toast.makeText(fragment.context, "${mDateData[position]}を削除しました", Toast.LENGTH_LONG).show()

                    mSwipeStack.swipeTopViewLeft()
                }
                .setNegativeButton("削除しない") { dialog, which ->
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return convertView
    }


}