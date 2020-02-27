package dev.marcosfarias.pokedex.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getById(id: String?): LiveData<PokemonEntity>

    @Query("SELECT * FROM pokemon")
    fun all(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(pokemon: List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Delete
    fun delete(model: PokemonEntity)
}
