package rs.raf.projekat2.david_nikolic_9419.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.david_nikolic_9419.data.datasources.local.RecipeDataBase
import rs.raf.projekat2.david_nikolic_9419.data.datasources.remote.RecipeService
import rs.raf.projekat2.david_nikolic_9419.data.repository.RecipeRepository
import rs.raf.projekat2.david_nikolic_9419.data.repository.RecipeRepositoryImpl
import rs.raf.projekat2.david_nikolic_9419.viewmodel.RecipeViewModel

val recipeModule = module {

    viewModel { RecipeViewModel(recipeRepository = get()) }

    single<RecipeRepository> { RecipeRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<RecipeDataBase>().getRecipeDao() }

    single<RecipeService> { create(retrofit = get()) }

}

