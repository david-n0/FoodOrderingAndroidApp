package rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.FragmentMenuBinding
import rs.raf.projekat2.david_nikolic_9419.model.Menu
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.menu.adapter.MenuAdapter
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState
import androidx.lifecycle.Observer
import rs.raf.projekat2.david_nikolic_9419.viewmodel.MenuViewModel
import rs.raf.projekat2.david_nikolic_9419.viewmodel.RecipeViewModel
import timber.log.Timber

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val menuViewModel: MainContract.ViewModel by sharedViewModel<RecipeViewModel>()

    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        //initObservers()
    }

    private fun initUi() {
        initListeners()
        initRecycler()
    }

    private fun initListeners() {
        binding.inputEt.doAfterTextChanged {
            val filter = it.toString()

            menuViewModel.getRecipeByName(filter)
        }

    }

    private fun initRecycler() {
        binding.MenuListRv.layoutManager = LinearLayoutManager(this.context)
        menuAdapter = MenuAdapter(Glide.with(this), createCategories(), this)
        binding.MenuListRv.adapter = menuAdapter

    }

    private fun createCategories(): List<Menu> {
        val category = mutableListOf<Menu>()
        category.add(Menu(1, R.drawable.barbeque, "Barbeque"))
        category.add(Menu(2, R.drawable.breakfast, "Breakfast"))
        category.add(Menu(3, R.drawable.chicken, "Chicken"))
        category.add(Menu(4, R.drawable.beef, "Beef"))
        category.add(Menu(5, R.drawable.brunch, "Brunch"))
        category.add(Menu(6, R.drawable.dinner, "Dinner"))
        category.add(Menu(7, R.drawable.wine, "Wine"))
        category.add(Menu(8, R.drawable.italian, "Italian"))

        return category
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}