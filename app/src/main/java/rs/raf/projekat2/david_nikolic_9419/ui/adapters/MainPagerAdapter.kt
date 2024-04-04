package rs.raf.projekat2.david_nikolic_9419.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.MenuFragment
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.RecipeFragment


class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
    }


    override fun getItem(position: Int): Fragment {
        return when (position) {
            FRAGMENT_1 -> MenuFragment()
            else -> MenuFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }


}