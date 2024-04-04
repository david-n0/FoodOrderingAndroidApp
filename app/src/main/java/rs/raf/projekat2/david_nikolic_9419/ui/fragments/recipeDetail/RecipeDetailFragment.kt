package rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipeDetail

import androidx.lifecycle.ViewModelProvider
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
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.david_nikolic_9419.R
import rs.raf.projekat2.david_nikolic_9419.databinding.FragmentRecipeBinding
import rs.raf.projekat2.david_nikolic_9419.databinding.FragmentRecipeDetailBinding
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity
import rs.raf.projekat2.david_nikolic_9419.ui.fragments.recipe.adapter.RecipeAdapter
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState
import rs.raf.projekat2.david_nikolic_9419.viewmodel.RecipeViewModel
import timber.log.Timber

class RecipeDetailFragment(detailId: String) : Fragment(R.layout.fragment_recipe_detail) {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: MainContract.ViewModel by sharedViewModel<RecipeViewModel>()

    private lateinit var recipeAdapter: RecipeAdapter
    private var detailId = detailId

    private lateinit var detailEntity : RecipeDetailEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        binding.titleTv.text = detailEntity.title
        binding.ingredientsTv.text = detailEntity.ingrediants
       // binding.recipePictureIv = detailEntity.image_url
        binding.publisherTv.text = detailEntity.publisher
        binding.scoreTv.text = "100"

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
        //recipeAdapter = RecipeAdapter(Glide.with(this), null)

        initListeners()
    }

    private fun initListeners() {
        binding.saveRecipeBtn.setOnClickListener(){
            val filter = it.toString()
            detailViewModel.getRecipeByName(filter)
        }

    }

    private fun initObservers() {
        detailViewModel.recipeState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
          //  renderState(it)
        })

        detailEntity = detailViewModel.getDetail(detailId)


    }



    private fun showLoadingState(loading: Boolean) {
        binding.saveRecipeBtn.isVisible = !loading
        binding.fragmentRecipeItemData.isVisible = !loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}