package app.ai.aitan.productrecipe

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    private val recyclerView: RecyclerView,
    private val onItemClick: (view: View, position: Int) -> Unit
): RecyclerView.SimpleOnItemTouchListener() {

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val childView = rv.findChildViewUnder(e.x, e.y)
        if (childView != null && gestureDetector.onTouchEvent(e)) {
            val position = rv.getChildAdapterPosition(childView)
            if (position != RecyclerView.NO_POSITION) {
                onItemClick.invoke(childView, position)
                return true
            }
        }
        return false
    }

}