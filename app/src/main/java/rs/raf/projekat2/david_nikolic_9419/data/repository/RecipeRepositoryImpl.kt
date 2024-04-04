package rs.raf.projekat2.david_nikolic_9419.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.david_nikolic_9419.data.datasources.local.RecipeDao
import rs.raf.projekat2.david_nikolic_9419.data.datasources.remote.RecipeService
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity
import rs.raf.projekat2.david_nikolic_9419.model.Resource
import timber.log.Timber

class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao,
    private val remoteDataSource: RecipeService
) : RecipeRepository {

    override fun fetchAll(title: String): Observable<Resource<Unit>> {

        return remoteDataSource
            .getAll(title)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.recipes.map {
                    RecipeEntity(
                        it._id,
                        it.recipe_id,
                        it.publisher,
                        it.title,
                        it.social_rank,
                        it.image_url
                    )
                }
                localDataSource.deleteAndInsertAll(entities)

            }
            .map {
                Resource.Success(Unit)
            }

    }

    fun fetchDetail(detail: String): RecipeEntity {
        return remoteDataSource
            .getDetail(detail)

    }

    override fun getAll(): Observable<List<Recipe>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Recipe(it._id, it.recipe_id,it.image_url, it.title, it.publisher, it.social_rank)
                }
            }
    }

    override fun getDetail(rId: String): RecipeDetailEntity {
        return localDataSource
            .getDetail(rId)

    }

    override fun getAllByName(name: String): Observable<List<Recipe>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Recipe(it._id, it.recipe_id,it.image_url, it.title, it.publisher, it.social_rank)
                }
            }
    }


    override fun insert(recipe: Recipe): Completable {
        val recipeEntity =
            RecipeEntity(
                recipe._id,
                recipe.recipe_id,
                recipe.publisher,
                recipe.title,
                recipe.score,
                recipe.picture
            )
        return localDataSource
            .insert(recipeEntity)
    }

}