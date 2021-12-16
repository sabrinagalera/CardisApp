package com.example.cardissapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardissapp.Adapters.DTOEmergAdapter
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.Models.DTOMovil
import com.example.cardissapp.Models.Global
import com.example.cardissapp.Network.CardissRepository
import com.example.cardissapp.R
import com.example.cardissapp.databinding.FragmentHomeBinding


class HomeFragment() : Fragment(), DTOEmergAdapter.HomeListener {


    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: DTOEmergAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)



        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        initAdapter()

    var servicio = CardissRepository()
        var mov = DTOMovil(movil1 = Global.movil.toInt())
        servicio.listarEmergencias(mov)?.observe(viewLifecycleOwner){
            if (it != null) {

                adapter.setData(it as ArrayList<DTOEmergencia>)

            } else {
                Toast.makeText(context,"algo salio mal", Toast.LENGTH_LONG)


            }

        }


        return root
    }

    private fun initAdapter() {
        adapter = DTOEmergAdapter(this)
        binding!!.listaEmergencias.layoutManager = LinearLayoutManager(context)
        binding!!.listaEmergencias.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onItemDeleted(postModel: DTOEmergencia, position: Int) {

        Global.idEmergencia = postModel.id
        NavHostFragment.findNavController(this)
            .navigate(R.id.fragment_detalle_visita)

      
    }
}