package com.example.cardissapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.R

import kotlinx.android.synthetic.main.nav_emergencias.view.*

import kotlinx.android.synthetic.main.nav_emergencias.view.txtDir

class DTOEmergAdapter(var listener: DTOEmergAdapter.HomeListener) : RecyclerView.Adapter<DTOEmergAdapter.HomeViewHolder>(){
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
            LayoutInflater.from(parent.context).inflate(R.layout.nav_emergencias, parent, false)
        )

    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
        holder.itemView.btnVerMas.setOnClickListener {
            item?.let{ it1 ->
                listener.onItemDeleted(it1, position)
            }
        }


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
            itemView.txtNro.text = item?.id.toString()
            itemView.txtCodigo.text =  item?.codigo
            itemView.txtDir.text =  item?.domicilio
            itemView.txtMotivo.text =  item?.motivoDeConsulta


        }

    }

}