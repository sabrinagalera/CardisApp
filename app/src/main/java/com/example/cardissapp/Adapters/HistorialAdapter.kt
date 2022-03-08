package com.example.cardissapp.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.R
import kotlinx.android.synthetic.main.nav_emergencias.view.*
import kotlinx.android.synthetic.main.nav_historial.view.*

class HistorialAdapter (var listener: HistorialAdapter.HomeListener) : RecyclerView.Adapter<HistorialAdapter.HomeViewHolder>() {
    private var data : ArrayList<DTOEmergencia>?=null

    interface HomeListener{

        fun onItemDeleted(postModel: DTOEmergencia, position: Int)

    }
    fun setData(list: ArrayList<DTOEmergencia>){
        data = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nav_historial, parent, false)
        )

    }
    override fun onBindViewHolder(holder: HistorialAdapter.HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)

    }
    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
    fun addData(postModel: DTOEmergencia) {
        data?.add(0,postModel)
        notifyItemInserted(0)
    }

    fun removeData(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }
    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(item: DTOEmergencia?) {
            itemView.fechaHist.text = item?.dia?.subSequence(0,10)
            itemView.motivoHist.text =  item?.motivoDeConsulta
            itemView.diagnosticoHist.text =  item?.diagnosticoPresuntivo










        }

    }

}