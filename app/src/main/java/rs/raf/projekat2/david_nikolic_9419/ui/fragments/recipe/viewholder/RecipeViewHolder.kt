package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.viewholder

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.LayoutRecipeListItemBinding
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.RecipeFragment
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipeDetail.RecipeDetailFragment

class RecipeViewHolder(
    private val binding: LayoutRecipeListItemBinding,
    private val glide: RequestManager,
    private val fragment: RecipeFragment
) : RecyclerView.ViewHolder(binding.root)  {

 private lateinit var rId: String

    init {
        // Kada dodamo click listener na containerView to znaci da ce biti registrovan klik
        // na bilo koji deo itema u listi
        binding.fragmentRecipeItemData.setOnClickListener {
            //Toast.makeText(context, "Kliknuto ", Toast.LENGTH_SHORT)
            Log.d("KLIK", "Kliknuto na: ${rId} ")

            val transaction = fragment.childFragmentManager.beginTransaction()
            transaction.add(R.id.recipeFragment, RecipeDetailFragment(rId))
            transaction.commit()
        }
        // Kada hocemo da registrujemo klik samo na odredjenoj komponenti u itemu,
        // dodajemo click listener bas na tu komponentu

    }

    fun bind(recipe: Recipe) {
        rId = recipe.recipe_id
        glide.load(recipe.picture).centerCrop().into(binding.recipePictureIv)
        binding.titleTv.text = recipe.title
        binding.publisherTv.text = recipe.publisher
        binding.scoreTv.text = recipe.score
    }
}
