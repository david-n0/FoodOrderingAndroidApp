package rs.raf.projekat2.david_nikolic_9419.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.david_nikolic_9419.model.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}