package dev.marcosfarias.pokedex.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val news = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemCount(): Int {
        return news.size
    }

    private fun getItem(position: Int) = news[position]

    fun addAll(items: List<News>) {
        news.clear()
        news.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_navigation_home_to_navigation_news_detail)
            }
        }

        fun bindView(item: News) {
//            itemView.textViewName.text = item.title
        }
    }

}
