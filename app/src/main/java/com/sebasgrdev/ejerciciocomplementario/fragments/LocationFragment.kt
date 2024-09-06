package com.sebasgrdev.ejerciciocomplementario.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sebasgrdev.ejerciciocomplementario.LocationService
import com.sebasgrdev.ejerciciocomplementario.R
import com.sebasgrdev.ejerciciocomplementario.viewModel.LocationViewModel
import com.sebasgrdev.ejerciciocomplementario.viewModel.LocationViewModelFactory
import kotlinx.coroutines.launch

class LocationFragment : Fragment() {

    private val locationViewModel: LocationViewModel by viewModels {
        LocationViewModelFactory(requireActivity().application)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLocationLatitude = view.findViewById<TextView>(R.id.tvLocationLatitude)
        val tvLocationLongitude = view.findViewById<TextView>(R.id.tvLocationLongitude)
        val btnLocation = view.findViewById<Button>(R.id.btnLocation)

        btnLocation.setOnClickListener {
            locationViewModel.fetchLocation()
        }

        locationViewModel.location.observe(viewLifecycleOwner) { location ->
            if (location != null) {
                tvLocationLatitude.text = "Latitude: ${location.latitude}"
                tvLocationLongitude.text = "Longitude: ${location.longitude}"
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.location_enable_required),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

}