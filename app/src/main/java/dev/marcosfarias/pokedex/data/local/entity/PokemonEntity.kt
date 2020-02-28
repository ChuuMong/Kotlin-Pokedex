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
    val abilities: List<String>? = null,
    val attack: Int? = null,
    val base_exp: String? = null,
    val category: String? = null,
    val cycles: String? = null,
    val defense: Int? = null,
    val egg_groups: String? = null,
    val evolutions: List<String>? = null,
    val evolvedfrom: String? = null,
    val female_percentage: String? = null,
    val genderless: Int? = null,
    val height: String? = null,
    val hp: Int? = null,
    val imageurl: String? = null,
    val male_percentage: String? = null,
    val name: String? = null,
    val reason: String? = null,
    val special_attack: Int? = null,
    val special_defense: Int? = null,
    val speed: Int? = null,
    val total: Int? = null,
    val typeofpokemon: List<String>? = null,
    val weaknesses: List<String>? = null,
    val weight: String? = null,
    val xdescription: String? = null,
    val ydescription: String? = null
) {
    companion object {
        fun create(pokemon: Pokemon) =
            PokemonEntity(
                pokemon.id,
                pokemon.abilities,
                pokemon.attack,
                pokemon.baseExp,
                pokemon.category,
                pokemon.cycles,
                pokemon.defense,
                pokemon.eggGroups,
                pokemon.evolutions,
                pokemon.evolvedFrom,
                pokemon.femalePercentage,
                pokemon.genderless,
                pokemon.height,
                pokemon.hp,
                pokemon.imageUrl,
                pokemon.malePercentage,
                pokemon.name,
                pokemon.reason,
                pokemon.specialAttack,
                pokemon.specialDefense,
                pokemon.speed,
                pokemon.total,
                pokemon.typeOfPokemon,
                pokemon.weaknesses,
                pokemon.weight,
                pokemon.xDescription,
                pokemon.yDescription
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
