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
import com.igo.tracking.databinding.ItemNoteBinding
import com.igo.tracking.model.constants.*
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
    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wf: Wf) {
            binding.iBiomassTitle.text = wf.biopacks[0].bioComment
            binding.iPackId.text = wf.biopacks[0].bioID.toString()
            binding.iBiomassDate.text = wf.biopacks[0].bioDate.toString()
            binding.iBiomassTypeValue.text = wf.biopacks[0].bioType
            binding.iBiomassWeightValue.text = wf.biopacks[0].bioWeight.toString()
            binding.iBiomassMoistureValue.text = wf.biopacks[0].bioMoisture.toString()
            binding.iBiomassCarbonValue.text = wf.biopacks[0].bioCarbonInDm.toString()
            binding.iStatus.text = wf.biopacks[0].bioStatus
            //binding.iBiomassIn.text = wf.biopacks[0].bioTimeIn.toString()
            //binding.iBiomassOut.text = wf.biopacks[0].bioTimeOut.toString()
            binding.iBiomassAddress.text = wf.biopacks[0].bioAddress
            binding.iBiomassLatitudeValue.text = String.format("%.4f", wf.biopacks[0].bioLatLng.latitude)
            binding.iBiomassLongitudeValue.text = String.format("%.4f", wf.biopacks[0].bioLatLng.longitude)
            binding.iBiomassDistance.text = wf.biopacks[0].bioDistance.toString()

            binding.iBiomassGroup.visibility = View.VISIBLE
            binding.iBiomassComment.visibility = View.GONE
            binding.iBiomassCommentValue.visibility = View.GONE

            //itemView.findViewById<TextView>(R.id.item_carbon_dm_title_value).text = wf.avatarUrl
            //itemView.findViewById<ImageView>(R.id.iv_avatar).load(R.drawable.ic_launcher_foreground)
        }
    }

// Далее переопределяем ТРИ обязательных метода:

    // 1. Создаем каждый конкретный новый экземпляр viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 2. Наполняем (связываем) viewHolder с данными (подставляем в шаблон конкретные значения wf)
    override fun onBindViewHolder(holder: NotesRvAdapter.ViewHolder, position: Int) =
        holder.bind(notes[position])

    // 3. Recycler должен знать сколько у него элементов. Данный метод не вызывается из кода
    override fun getItemCount(): Int = notes.size


    fun updateNote(newWfs: ArrayList<Wf>) {
        this.notes = newWfs
    }

}