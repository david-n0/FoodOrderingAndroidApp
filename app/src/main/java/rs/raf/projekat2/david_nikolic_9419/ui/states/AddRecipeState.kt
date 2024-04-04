package rs.raf.projekat2.david_nikolic_9419.ui.states

sealed class AddRecipeState {
    object Success: AddRecipeState()
    data class Error(val message: String): AddRecipeState()
}