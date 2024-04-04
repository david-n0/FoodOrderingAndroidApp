package rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.FragmentRecipeBinding
import rs.raf.projekat2.david_nikolic_9419.model.Menu
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.adapter.RecipeAdapter
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState
import rs.raf.projekat2.david_nikolic_9419.viewmodel.RecipeViewModel
import timber.log.Timber

class RecipeFragment(title: String) : Fragment(R.layout.fragment_recipe) {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private val recipeViewModel: MainContract.ViewModel by sharedViewModel<RecipeViewModel>()

    private lateinit var recipeAdapter: RecipeAdapter
    private var title = title

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initListeners()
        initRecycler()
    }

    private fun initListeners() {
        binding.inputEt.doAfterTextChanged {
            val filter = it.toString()
            recipeViewModel.getRecipeByName(filter)
        }

    }

    private fun initRecycler() {
        binding.recipeListRv.layoutManager = LinearLayoutManager(this.context)
        recipeAdapter = RecipeAdapter(Glide.with(this), this)
        binding.recipeListRv.adapter = recipeAdapter

    }

    private fun initObservers() {
        recipeViewModel.recipeState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        recipeViewModel.getAllRecipes()

        recipeViewModel.fetchAllRecipes(title)


    }

    private fun renderState(state: RecipesState) {
        when (state) {
            is RecipesState.Success -> {
                showLoadingState(false)
                recipeAdapter.submitList(state.recipes)
            }
            is RecipesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                    .show()
            }
            is RecipesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputEt.isVisible = !loading
        binding.recipeListRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}