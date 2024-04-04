package rs.raf.projekat2.david_nikolic_9419.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.david_nikolic_9419.model.RecipeDetailEntity
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity

@Dao
abstract class RecipeDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: RecipeEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RecipeEntity>): Completable

    @Query("SELECT * FROM recipes")
    abstract fun getAll(): Observable<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE recipe_id LIKE :detail")
    abstract fun getDetail(detail: String): RecipeDetailEntity

    @Query("DELETE FROM recipes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<RecipeEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM recipes WHERE title LIKE :title")
    abstract fun getByName(title: String): Observable<List<RecipeEntity>>


}