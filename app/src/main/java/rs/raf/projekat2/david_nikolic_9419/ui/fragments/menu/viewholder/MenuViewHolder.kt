package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.LayoutMenuListItemBinding
import rs.raf.projekat2.david_nikolic_9419.model.Menu
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.MenuFragment
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.RecipeFragment

class MenuViewHolder(
    private val binding: LayoutMenuListItemBinding,
    private val glide: RequestManager,
    private val fragment: MenuFragment
) : RecyclerView.ViewHolder(binding.root) {

    init {
        // Kada dodamo click listener na containerView to znaci da ce biti registrovan klik
        // na bilo koji deo itema u listi
        binding.fragmentItemData.setOnClickListener {

            Log.d("KLIK", "Kliknuto na: ${binding.categoryTv.text} ")

            val transaction = fragment.childFragmentManager.beginTransaction()
            transaction.add(R.id.menuFragment, RecipeFragment(binding.categoryTv.text.toString()))
            transaction.commit()
        }


    }

    fun bind(menu: Menu) {
        glide.load(menu.picture).centerCrop().into(binding.menuPictureIv)
        binding.categoryTv.text = menu.category
    }
}
