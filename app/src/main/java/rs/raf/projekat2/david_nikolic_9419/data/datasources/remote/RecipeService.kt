package rs.raf.projekat2.david_nikolic_9419.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity
import rs.raf.projekat2.david_nikolic_9419.model.RecipeResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface RecipeService {

    @GET("search")
    fun getAll(@Query("q") title: String , @Query("page") page: Int = 1 ): Observable<RecipeResponse>

    @GET("get")
    fun getDetail(@Query("rId") rId: String ): RecipeEntity



}