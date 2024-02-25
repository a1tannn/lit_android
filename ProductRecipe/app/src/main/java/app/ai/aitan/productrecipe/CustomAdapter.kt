package app.ai.aitan.productrecipe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val memos: List<String>) :
    RecyclerView.Adapter<CustomAdapter.MemoViewHolder>() {

    class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView
        val memoView: TextView

        init {
            titleView = itemView.findViewById(R.id.title_text)?: TextView(itemView.context).apply { text = "" }
            memoView = itemView.findViewById(R.id.memo_text)?: TextView(itemView.context).apply { text = "" }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_memo, parent, false)
        return MemoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = memos[position]
        val parts = memo.split(":")
        val title = parts[0].trim()
        val memoText = if (parts.size > 1) parts[1].trim() else ""
        holder.titleView.text = title
        holder.memoView.text = memoText
    }

    override fun getItemCount() = memos.size
}
