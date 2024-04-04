package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.david_nikolic_9419.model.Menu
import rs.raf.projekat2.david_nikolic_9419.model.Recipe

class RecipeDiffItemCallback: DiffUtil.ItemCallback<Recipe>(){

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.title == newItem.title && oldItem.publisher == newItem.publisher
    }
}