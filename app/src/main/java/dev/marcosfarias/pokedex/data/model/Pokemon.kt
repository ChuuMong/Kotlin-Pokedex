package dev.marcosfarias.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val xDescription: String,
    val yDescription: String,
    val height: String,
    val category: String,
    val weight: String,
    val typeOfPokemon: List<String>,
    val weaknesses: List<String>,
    val evolutions: List<String>,
    val abilities: List<String>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int,
    val malePercentage: String?,
    val femalePercentage: String?,
    val genderless: Int,
    val cycles: String,
    val eggGroups: String,
    val evolvedFrom: String,
    val reason: String,
    val baseExp: String
)