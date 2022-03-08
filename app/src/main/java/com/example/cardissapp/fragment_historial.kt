package com.example.cardissapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.example.cardissapp.Adapters.DTOEmergAdapter
import com.example.cardissapp.Adapters.HistorialAdapter
import com.example.cardissapp.Models.DTOEmergencia
import com.example.cardissapp.Models.DTOMovil
import com.example.cardissapp.Models.Global
import com.example.cardissapp.Network.CardissRepository
import com.example.cardissapp.R
import com.example.cardissapp.databinding.FragmentHistorialBinding
import com.example.cardissapp.databinding.FragmentHomeBinding


class fragment_historial : Fragment(), HistorialAdapter.HomeListener {


    private val binding get() = _binding!!
    private var _binding: FragmentHistorialBinding? = null
    private lateinit var adapter: HistorialAdapter

    /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistorialBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        initAdapter()

        var servicio = CardissRepository()

        servicio.getHistorial(Global.idEmergencia)?.observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it as ArrayList<DTOEmergencia>)

            } else {
                Toast.makeText(context, "Algo salio mal", Toast.LENGTH_LONG).show()
            }


        })


        return root
    }

    private fun initAdapter() {
        adapter = HistorialAdapter(this)
        binding!!.listaHistorial.layoutManager = LinearLayoutManager(context)
        binding!!.listaHistorial.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemDeleted(postModel: DTOEmergencia, position: Int) {

    }

}