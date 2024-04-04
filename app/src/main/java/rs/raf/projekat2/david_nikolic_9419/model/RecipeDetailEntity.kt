package rs.raf.projekat2.david_nikolic_9419.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeDetailEntity(
    @PrimaryKey
    val _id: String,
    val recipe_id: String,
    val ingrediants: String,
    val publisher: String,
    val title: String,
    val social_rank: String,
    val image_url: String
)