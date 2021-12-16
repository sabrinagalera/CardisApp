package com.example.cardissapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.R
import kotlinx.android.synthetic.main.fragment_detalle_visita.view.*
import kotlinx.android.synthetic.main.nav_emergencias.view.*

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
            /*itemView.txtNombre.text = item?.nombreYApellido
            itemView.txtDomicilio.text = item?.domicilio
            itemView.txtTel.text = item?.telefono
            itemView.txtEdad.text = item?.edad.toString()
            itemView.txtObraSoc.text = item?.obrasocial
            itemView.txtCodigo.text =item?.codigo
            itemView.txtDni.text = item?.documento
            itemView.txtMotivoCons.text=item?.motivoDeConsulta*/
            //itemView.txtDiagnostico.text=item?.diagnosticoPresuntivo






        }

    }

}