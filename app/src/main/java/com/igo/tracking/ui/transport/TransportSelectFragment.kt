package com.igo.tracking.ui.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentTransportSelectBinding
import com.igo.tracking.model.constants.*


class TransportSelectFragment : Fragment() {


    private var _binding: FragmentTransportSelectBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transport_select, container, false)
        _binding = FragmentTransportSelectBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // transfer data back to parent Fragment
//        binding.bsSubmit.setOnClickListener {
//
//            BiomassFragment.packsNumber ++
//
//            val checkedId = binding.bsRadioGroup.checkedRadioButtonId
//            val selectedRadioButton = binding.bsRadioGroup.findViewById<RadioButton>(checkedId)
//            val selectedRadioText = selectedRadioButton?.text.toString()
//
//
//            BiomassFragment.pack.add (Biomass(
//                BiomassFragment.packsNumber,
//                Date(),
//                selectedRadioText,
//                binding.bsWeight.text.toString().toDouble(),
//                binding.bsMoisture.text.toString().toDouble(),
//                binding.bsCarbonDm.text.toString().toDouble(),
//                "",
//                RAW
//            ))

//            parentFragmentManager.clearFragmentResult(RESPOND)
//            findNavController().popBackStack()
        }







}