package dev.marcosfarias.pokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.data.repository.HomeRepository
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>> get() = _menus

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    init {
        _menus.value = homeRepository.getHomeMenus()
        _news.value = homeRepository.getHomeNews()
    }
}
