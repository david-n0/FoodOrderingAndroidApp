package rs.raf.projekat2.david_nikolic_9419.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.david_nikolic_9419.data.repository.RecipeRepository
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.Resource
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddRecipeState
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MenuViewModel(
    private val recipeRepository: RecipeRepository
) {


}