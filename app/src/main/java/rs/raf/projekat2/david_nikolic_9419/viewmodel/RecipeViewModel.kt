package rs.raf.projekat2.david_nikolic_9419.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.david_nikolic_9419.data.repository.RecipeRepository
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.model.Resource
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddRecipeState
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel(),
    MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val recipeState: MutableLiveData<RecipesState> = MutableLiveData()
    override val addDone: MutableLiveData<AddRecipeState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                recipeRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    recipeState.value = RecipesState.Success(it)
                },
                {
                    recipeState.value =
                        RecipesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRecipeByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun getDetail(rId: String): RecipeDetailEntity {
        val detail = recipeRepository.getDetail(rId)
        return detail
    }

    override fun getAllRecipes() {
        val subscription = recipeRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipeState.value = RecipesState.Success(it)
                },
                {
                    recipeState.value =
                        RecipesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllRecipes(title: String) {
        val subscription = recipeRepository
            .fetchAll(title)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> recipeState.value = RecipesState.Loading
                        is Resource.Success -> recipeState.value = RecipesState.DataFetched
                        is Resource.Error -> recipeState.value =
                            RecipesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    recipeState.value =
                        RecipesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addRecipe(recipe: Recipe) {
        val subscription = recipeRepository
            .insert(recipe)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddRecipeState.Success
                },
                {
                    addDone.value = AddRecipeState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}