import androidx.lifecycle.LiveData
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.ui.states.AddRecipeState
import rs.raf.projekat2.david_nikolic_9419.ui.states.RecipesState

interface MainContract {

    interface ViewModel {

        val recipeState: LiveData<RecipesState>
        val addDone: LiveData<AddRecipeState>

        fun getRecipeByName(name: String)
        fun getAllRecipes()
        fun getDetail(rId: String): RecipeDetailEntity
        fun fetchAllRecipes(title : String)
        fun addRecipe(recipe: Recipe)
    }

}