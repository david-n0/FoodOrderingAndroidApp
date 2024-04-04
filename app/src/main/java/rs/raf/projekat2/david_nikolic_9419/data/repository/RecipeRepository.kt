package rs.raf.projekat2.david_nikolic_9419.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.david_nikolic_9419.model.Recipe
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.model.Resource


interface RecipeRepository {

    fun fetchAll(title: String): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Recipe>>
    fun getDetail(rId: String): RecipeDetailEntity
    fun getAllByName(name: String): Observable<List<Recipe>>
    fun insert(recipe: Recipe): Completable


}