package com.kan.salads

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kan.salads.activity.saladdetails.SaladDetailActivityArgs
import kotlinx.android.synthetic.main.list_layout.view.*

class SaladViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var itemViewModel: ShoppingCartItemViewModel? = null

    fun bind(item: ShoppingCartItemViewModel) {
        itemViewModel = item
        itemView.textView.text = item.salad.name
        if (item.selected) {
            setSelected()
        } else {
            setDeSelected()
        }
        itemView.setOnClickListener {
            onClicked(item)
        }
        itemView.viewButton.setOnClickListener {
            SaladDetailActivityArgs(item.salad.uuid).launch(itemView.context)
        }
    }

    private fun onClicked(item: ShoppingCartItemViewModel) {
        if (item.selected) {
            onItemDeSelected(item.salad.uuid)
            setDeSelected()
        } else {
            onItemSelected(item.salad.uuid)
            setSelected()
        }
        item.selected = !item.selected
    }

    private fun setSelected() {
        itemView.imageView.visibility = View.VISIBLE
    }

    private fun setDeSelected() {
        itemView.imageView.visibility = View.INVISIBLE
    }

    var onItemSelected: (itemId: String) -> Unit = { println("In viewholder") }
    var onItemDeSelected: (itemId: String) -> Unit = {}
}