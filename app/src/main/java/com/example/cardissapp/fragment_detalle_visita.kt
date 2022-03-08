package com.example.cardissapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardissapp.Adapters.DTOEmergAdapter

import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.Models.DTOEstadoVisita
import com.example.cardissapp.Models.Global
import com.example.cardissapp.Network.CardissRepository
import com.example.cardissapp.databinding.FragmentDetalleVisitaBinding
import com.example.cardissapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_detalle_visita.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_detalle_visita.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_detalle_visita: Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentDetalleVisitaBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentDetalleVisitaBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        binding!!.btnHistorial.setOnClickListener {      view ->
            Navigation.findNavController(view).navigate(R.id.fragment_historial) }

        val servicio = CardissRepository()

        servicio.getDetalles(Global.idEmergencia)?.observe(viewLifecycleOwner, {
            if(it != null){
                binding!!.txtNombre.setText(it.nombreYApellido)
                binding!!.txtDomicilio.setText(it.domicilio)
                binding!!.txtTel.setText(it.telefono)
                binding!!.txtEdad.setText(it.edad)
                binding!!.txtObraSoc.setText(it.obrasocial)
                binding!!.txtCod.setText(it.codigo)
                binding!!.txtDni.setText(it.documento)
                binding!!.txtCoseg.setText(it.coseguro)
                binding!!.txtMotivoCons.setText(it.motivoDeConsulta)
                binding!!.txtObserv.setText(it.observaciones)
            }
            else{
                Toast.makeText(context,"Algo salio mal", Toast.LENGTH_LONG).show()
            }
        })

        binding!!.btnVisto.setOnClickListener {
            var dto=DTOEstadoVisita()
            dto.id=Global.idEmergencia
                dto.estado = "Visto"
            servicio.postearEstado(dto)?.observe(viewLifecycleOwner, {
                if(it!=null){
                    Toast.makeText(context,"Estado Actualizado: VISTO", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Algo salio mal", Toast.LENGTH_LONG).show()
                }

            })
        }
        binding!!.btnLlegada.setOnClickListener {
            var dto=DTOEstadoVisita()
            dto.id=Global.idEmergencia
            dto.estado = "Llegada"
            servicio.postearEstado(dto)?.observe(viewLifecycleOwner, {
                if(it!=null){
                    Toast.makeText(context,"Estado Actualizado: LLEGADA", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(context,"Algo salio mal", Toast.LENGTH_LONG).show()
                }

            })
        }
        binding!!.btnLibre.setOnClickListener {
            if(txtDiagnostico.text.toString() !=""){
                var dto=DTOEstadoVisita()
                dto.id=Global.idEmergencia
                dto.estado = "Libre"
                dto.diagnostico= txtDiagnostico.text.toString()
                dto.covid= checkBoxCovid.isChecked
                dto.kit= checkBoxDebeKIT.isChecked
                dto.debe=checkBoxDebe.isChecked
                dto.debeKit=checkBoxDebeKIT.isChecked

                servicio.postearEstado(dto)?.observe(viewLifecycleOwner, {
                    if(it!=null){
                        Toast.makeText(context,"Estado Actualizado: LIBRE", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(context,"Algo salio mal", Toast.LENGTH_LONG).show()
                    }

                })
            }  else{
                Toast.makeText(context,"Completar Diagnóstico", Toast.LENGTH_LONG).show()
            }

        }

        return root
    }



    }












