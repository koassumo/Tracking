package com.igo.tracking.ui.biomass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentBiomassBinding
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biomass


class BiomassFragment : Fragment() {


    companion object {
        var packsNumber: Int = 0

        val pack: ArrayList<Biomass> = ArrayList(10)
    }

    private var _binding: FragmentBiomassBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val biomassViewModel =
            ViewModelProvider(this).get(BiomassViewModel::class.java)

        _binding = FragmentBiomassBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        biomassViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderPacks()

        binding.biomassAddBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_nav_biomass_to_biomassSelectFragment,
                bundleOf(PACK_ID to "1")
            )
        }


        // getting data when return from selectFragment
        parentFragmentManager.setFragmentResultListener(RESPOND, viewLifecycleOwner) { _, data ->
            binding.biomassMoistureTitle.text = "fd"
        }

    }

    private fun renderPacks() {
        if (pack.size > 0) {
            binding.biomassInfo1TitleValue.text = pack[0].biomassID.toString()
            binding.biomassTypeTitleValue.text = pack[0].biomassType
            binding.biomassWeightTitleValue.text = pack[0].biomassWeight.toString()
            binding.biomassMoistureTitleValue.text = pack[0].biomassMoisture.toString()
            binding.biomassCarbonDmTitleValue.text = pack[0].biomassCarbonInDm.toString()
        }
         if (pack.size > 1) {
            binding.biomassInfo2TitleValue.text = pack[1].biomassID.toString()
            binding.biomassTypeTitleValue2.text = pack[1].biomassType
            binding.biomassWeightTitleValue2.text = pack[1].biomassWeight.toString()
            binding.biomassMoistureTitleValue2.text = pack[1].biomassMoisture.toString()
            binding.biomassCarbonDmTitleValue2.text = pack[1].biomassCarbonInDm.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}