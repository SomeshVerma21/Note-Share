package com.vermaji.noteshare.mainUI.home.homeScreen

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridRecyclerViewDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 10
        outRect.left = 20
        outRect.right = 20
        outRect.bottom = 10

    }
}