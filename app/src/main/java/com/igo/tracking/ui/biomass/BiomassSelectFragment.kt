package com.igo.tracking.ui.biomass

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentBiomassSelectBinding
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biomass
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

        binding.bsMapBtn.setOnClickListener{
            findNavController().navigate(
                R.id.action_biomassSelectFragment_to_locFragment,
                bundleOf(PACK_ID to 0)
            )

        }

        // transfer data back to parent Fragment
        binding.bsSubmit.setOnClickListener {

            BiomassFragment.packsNumber ++

            val checkedId = binding.bsRadioGroup.checkedRadioButtonId
            val selectedRadioButton = binding.bsRadioGroup.findViewById<RadioButton>(checkedId)
            val selectedRadioText = selectedRadioButton?.text.toString()


            BiomassFragment.pack.add (Biomass(
                BiomassFragment.packsNumber,
                Date(),
                selectedRadioText,
                binding.bsWeight.text.toString().toDouble(),
                binding.bsMoisture.text.toString().toDouble(),
                binding.bsCarbonDm.text.toString().toDouble(),
                "",
                RAW
            ))

            parentFragmentManager.clearFragmentResult(RESPOND)
            findNavController().popBackStack()
        }





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

}