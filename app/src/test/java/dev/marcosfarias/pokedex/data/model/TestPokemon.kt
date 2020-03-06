package dev.marcosfarias.pokedex.data.model

import com.google.gson.annotations.SerializedName
import dev.marcosfarias.pokedex.data.local.entity.PokemonEntity
import dev.marcosfarias.pokedex.data.remote.response.PokemonResponse

data class TestPokemon(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageurl")
    val imageUrl: String,
    @SerializedName("xdescription")
    val xDescription: String,
    @SerializedName("ydescription")
    val yDescription: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("typeofpokemon")
    val typeOfPokemon: List<String>,
    @SerializedName("weaknesses")
    val weaknesses: List<String>,
    @SerializedName("evolutions")
    val evolutions: List<String>,
    @SerializedName("abilities")
    val abilities: List<String>,
    @SerializedName("hp")
    val hp: Int,
    @SerializedName("attack")
    val attack: Int,
    @SerializedName("defense")
    val defense: Int,
    @SerializedName("special_attack")
    val specialAttack: Int,
    @SerializedName("special_defense")
    val specialDefense: Int,
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("male_percentage")
    val malePercentage: String?,
    @SerializedName("female_percentage")
    val femalePercentage: String?,
    @SerializedName("genderless")
    val genderless: Int,
    @SerializedName("cycles")
    val cycles: String,
    @SerializedName("egg_groups")
    val eggGroups: String,
    @SerializedName("evolvedfrom")
    val evolvedFrom: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("base_exp")
    val baseExp: String
) {
    fun toPokemonEntity() = PokemonEntity(
        id,
        name,
        imageUrl,
        xDescription,
        yDescription,
        height,
        category,
        weight,
        typeOfPokemon,
        weaknesses,
        evolutions,
        abilities,
        hp,
        attack,
        defense,
        specialAttack,
        specialDefense,
        speed,
        total,
        malePercentage,
        femalePercentage,
        genderless,
        cycles,
        eggGroups,
        evolvedFrom,
        reason,
        baseExp
    )

    fun toPokemonResponse() = PokemonResponse(
        id,
        name,
        imageUrl,
        xDescription,
        yDescription,
        height,
        category,
        weight,
        typeOfPokemon,
        weaknesses,
        evolutions,
        abilities,
        hp,
        attack,
        defense,
        specialAttack,
        specialDefense,
        speed,
        total,
        malePercentage,
        femalePercentage,
        genderless,
        cycles,
        eggGroups,
        evolvedFrom,
        reason,
        baseExp
    )
}