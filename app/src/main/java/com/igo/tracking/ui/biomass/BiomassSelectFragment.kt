package com.igo.tracking.ui.biomass

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.LatLng
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentBiomassSelectBinding
import com.igo.tracking.model.RepWf.addWf
import com.igo.tracking.model.RepWf.getWfSize
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biopack
import java.util.Date


class BiomassSelectFragment : Fragment() {


    private var _binding: FragmentBiomassSelectBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_biomass_select, container, false)
        _binding = FragmentBiomassSelectBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // on Map
        binding.bsMapBtn.setOnClickListener {
            val index = getWfSize()
            findNavController().navigate(
                R.id.action_biomassSelectFragment_to_locFragment,
                bundleOf(WF_ID to index)
            )
        }

        // back from Map
        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        val id = savedStateHandle?.get<Int>(WF_ID)
        binding.bsSiteComment.text = savedStateHandle?.get<String>(WF_COMMENT)
        binding.bsSiteAddress.text = savedStateHandle?.get<String>(WF_ADDRESS)
        binding.bsSiteLatitudeValue.text = savedStateHandle?.get<Double>(WF_LAT).toString()
        binding.bsSiteLongitudeValue.text = savedStateHandle?.get<Double>(WF_LNG).toString()
        binding.bsSiteDistanceValue.text = savedStateHandle?.get<String>(WF_DISTANCE)

        binding.bsSubmit.setOnClickListener {
            submitNewWf()
        }


        // Progress bars control
        binding.bsWeightSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.bsWeight.requestFocus()
                binding.bsWeight.text =
                    Editable.Factory.getInstance().newEditable(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.bsMoistureSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.bsMoisture.requestFocus()
                binding.bsMoisture.text =
                    Editable.Factory.getInstance().newEditable(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.bsCarbonDmSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Переключить фокус на bs_carbon_dm
                binding.bsCarbonDm.requestFocus()
                binding.bsCarbonDm.text =
                    Editable.Factory.getInstance().newEditable(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun submitNewWf() {
        //BiomassFragment.packsNumber++

        // radio recognizing
        val checkedId = binding.bsRadioGroup.checkedRadioButtonId
        val selectedRadioButton = binding.bsRadioGroup.findViewById<RadioButton>(checkedId)
        val selectedRadioText = selectedRadioButton?.text.toString()

        var lat = binding.bsSiteLatitude.text.toString().toDoubleOrNull()
        var lng = binding.bsSiteLongitudeValue.text.toString().toDoubleOrNull()
        if (lat == null) lat = 0.0
        if (lng == null) lng = 0.0


        val newBiopack = Biopack(
            bioID = 98989,
            bioDate = Date(),
            bioType = selectedRadioText,
            bioWeight = binding.bsWeight.text.toString().toDouble(),
            bioMoisture = binding.bsMoisture.text.toString().toDouble(),
            bioCarbonInDm = binding.bsCarbonDm.text.toString().toDouble(),
            bioComment = "",
            bioStatus = RAW,
            bioTimeIn = Date(),
            bioTimeOut = Date(),
            bioAddress = binding.bsSiteAddress.toString(),
            bioLatLng = LatLng(lat, lng),
            bioDistance = 1.1
        )
        addWf(newBiopack)
        val index = getWfSize()
        Toast.makeText(requireContext(), index.toString(), Toast.LENGTH_LONG).show()
        //BiomassFragment.pack.add(newBiopack)

        //findNavController().previousBackStackEntry?.savedStateHandle?.set(RESPOND, index)
        findNavController().popBackStack()
        //parentFragmentManager.clearFragmentResult(RESPOND, )
        //findNavController().popBackStack()
    }

}