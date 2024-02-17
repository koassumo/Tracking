package com.igo.tracking.ui.biomass

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.igo.tracking.R
import com.igo.tracking.model.entity.Note
import com.igo.tracking.model.entity.Wf


class NotesRvAdapter : RecyclerView.Adapter<NotesRvAdapter.ViewHolder>() {

    //================================================================================================
    // Т.е. по факту убрали свой class MyDiffUtilCallback и поручаем сравнение списка на входе
    // классу AsyncListDiffer
    private val myDiffUtilCallBack = object : DiffUtil.ItemCallback<Wf>() {
        override fun areItemsTheSame(oldItem: Wf, newItem: Wf): Boolean =
            oldItem.biopacks == newItem.biopacks
// тут видимо по каким полям сравнение

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Wf, newItem: Wf): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, myDiffUtilCallBack)

    var notes: MutableList<Wf>
        get() = differ.currentList
        set(value) = differ.submitList(value)
//================================================================================================



    // 0. Определяем CLASS ViewHolder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wf: Wf) {
            itemView.findViewById<TextView>(R.id.item_title).text = wf.biopacks[0].bioComment
            //itemView.findViewById<TextView>(R.id.item_carbon_dm_title_value).text = wf.avatarUrl
            //itemView.findViewById<ImageView>(R.id.iv_avatar).load(R.drawable.ic_launcher_foreground)
        }
    }

// Далее переопределяем ТРИ обязательных метода:

    // 1. Создаем каждый конкретный новый экземпляр viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))


    // 2. Наполняем (связываем) viewHolder с данными (подставляем в шаблон конкретные значения wf)
    override fun onBindViewHolder(holder: NotesRvAdapter.ViewHolder, position: Int) =
        holder.bind(notes[position])

    // 3. Recycler должен знать сколько у него элементов. Данный метод не вызывается из кода
    override fun getItemCount(): Int = notes.size


    fun updateNote(newWfs: ArrayList<Wf>) {
        this.notes = newWfs
    }

}