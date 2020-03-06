package dev.marcosfarias.pokedex.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.marcosfarias.pokedex.data.model.Pokemon
import dev.marcosfarias.pokedex.utils.ListStringConverter

@Entity(tableName = "Pokemon")
@TypeConverters(ListStringConverter::class)
data class PokemonEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    val name: String? = null,
    val imageurl: String? = null,
    val xdescription: String? = null,
    val ydescription: String? = null,
    val height: String? = null,
    val category: String? = null,
    val weight: String? = null,
    val typeofpokemon: List<String>? = null,
    val weaknesses: List<String>? = null,
    val evolutions: List<String>? = null,
    val abilities: List<String>? = null,
    val hp: Int? = null,
    val attack: Int? = null,
    val defense: Int? = null,
    val special_attack: Int? = null,
    val special_defense: Int? = null,
    val speed: Int? = null,
    val total: Int? = null,
    val male_percentage: String? = null,
    val female_percentage: String? = null,
    val genderless: Int? = null,
    val cycles: String? = null,
    val egg_groups: String? = null,
    val evolvedfrom: String? = null,
    val reason: String? = null,
    val base_exp: String? = null
) {
    companion object {
        fun create(pokemon: Pokemon) =
            PokemonEntity(
                pokemon.id,
                pokemon.name,
                pokemon.imageUrl,
                pokemon.xDescription,
                pokemon.yDescription,
                pokemon.height,
                pokemon.category,
                pokemon.weight,
                pokemon.typeOfPokemon,
                pokemon.weaknesses,
                pokemon.evolutions,
                pokemon.abilities,
                pokemon.hp,
                pokemon.attack,
                pokemon.defense,
                pokemon.specialAttack,
                pokemon.specialDefense,
                pokemon.speed,
                pokemon.total,
                pokemon.malePercentage,
                pokemon.femalePercentage,
                pokemon.genderless,
                pokemon.cycles,
                pokemon.eggGroups,
                pokemon.evolvedFrom,
                pokemon.reason,
                pokemon.baseExp
            )
    }

    fun toEntity() = Pokemon(
        id,
        name!!,
        imageurl!!,
        xdescription!!,
        ydescription!!,
        height!!,
        category!!,
        weight!!,
        typeofpokemon ?: emptyList(),
        weaknesses ?: emptyList(),
        evolutions ?: emptyList(),
        abilities ?: emptyList(),
        hp!!,
        attack!!,
        defense!!,
        special_attack!!,
        special_defense!!,
        speed!!,
        total!!,
        male_percentage,
        female_percentage,
        genderless!!,
        cycles!!,
        egg_groups!!,
        evolvedfrom!!,
        reason!!,
        base_exp!!
    )
}
