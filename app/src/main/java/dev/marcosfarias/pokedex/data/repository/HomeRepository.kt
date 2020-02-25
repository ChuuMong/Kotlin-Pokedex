package dev.marcosfarias.pokedex.data.repository

import dev.marcosfarias.pokedex.data.model.MenuType
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News

class HomeRepository {

    fun getHomeMenus() = listOf(
        Menu(1, MenuType.POKEDEX),
        Menu(1, MenuType.MOVES),
        Menu(1, MenuType.ABILITIES),
        Menu(1, MenuType.ITEMS),
        Menu(1, MenuType.LOCATIONS),
        Menu(1, MenuType.TYPE_CHARTS)
    )

    fun getHomeNews() = listOf(
        News(),
        News(),
        News(),
        News(),
        News(),
        News(),
        News(),
        News()
    )
}