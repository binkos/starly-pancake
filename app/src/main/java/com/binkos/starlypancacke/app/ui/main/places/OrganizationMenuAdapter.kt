package com.binkos.starlypancacke.app.ui.main.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binkos.starlypancacke.app.R
import com.binkos.starlypancacke.domain.model.Food
import com.bumptech.glide.Glide

class OrganizationMenuAdapter(
    private val menuItems: List<Food>
) : RecyclerView.Adapter<MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
}

class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val icon: ImageView = itemView.findViewById(R.id.foodIconImageView)
    private val name: TextView = itemView.findViewById(R.id.nameItemTextView)
    private val price: TextView = itemView.findViewById(R.id.priceItemTextView)
    private val ingredients: TextView = itemView.findViewById(R.id.ingredientsTextView)

    fun bind(food: Food) {
        name.text = food.name
        price.text = "${food.price} BYN"
        val ingredientsString = StringBuilder()
        food.ingredients.forEach {
            ingredientsString.append(it).append(", ")
        }
        ingredients.text = ingredientsString

        Glide
            .with(itemView.context)
            .load(food.icon)
            .centerCrop()
            .into(icon)
    }
}