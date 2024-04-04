package rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.david_nikolic_9419.databinding.LayoutRecipeListItemBinding
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.diff.RecipeDiffItemCallback
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.viewholder.RecipeViewHolder
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.RecipeFragment

class RecipeAdapter(
        private val requestManager: RequestManager,
        private val fragment: RecipeFragment
) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding =
                LayoutRecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding, requestManager, fragment)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}