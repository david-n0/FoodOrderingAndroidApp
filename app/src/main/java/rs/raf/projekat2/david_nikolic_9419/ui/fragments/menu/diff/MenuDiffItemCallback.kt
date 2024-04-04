package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.david_nikolic_9419.model.Menu

class MenuDiffItemCallback: DiffUtil.ItemCallback<Menu>(){

    override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
        return oldItem.category == newItem.category
    }
}