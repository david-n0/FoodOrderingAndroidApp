package rs.raf.projekat2.david_nikolic_9419.ui.states

import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity

sealed class RecipesState {
    object Loading : RecipesState()
    object DataFetched : RecipesState()
    data class Success(val recipes: List<Recipe>) : RecipesState()
    data class Error(val message: String) : RecipesState()
}