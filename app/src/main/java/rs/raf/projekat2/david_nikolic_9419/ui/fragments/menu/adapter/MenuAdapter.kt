package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.david_nikolic_9419.databinding.LayoutMenuListItemBinding
import rs.raf.projekat2.david_nikolic_9419.model.Menu
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.MenuFragment
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.diff.MenuDiffItemCallback
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.viewholder.MenuViewHolder

class MenuAdapter(
    private val requestManager: RequestManager,
    private val categories: List<Menu>,
    private val fragment: MenuFragment
) : ListAdapter<Menu, MenuViewHolder>(MenuDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemBinding =
            LayoutMenuListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(itemBinding, requestManager, fragment)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount() = categories.size


}