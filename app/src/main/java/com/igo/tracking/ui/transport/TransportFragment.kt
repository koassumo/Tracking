package com.igo.tracking.ui.transport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentTransportBinding
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biomass


class TransportFragment : Fragment() {


    companion object {
        var packsNumber: Int = 0

        val pack: ArrayList<Biomass> = ArrayList(10)
   }

    private var _binding: FragmentTransportBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderPacks()

        binding.transAddBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_bm_nav_trans_to_transportSelectFragment2,
                bundleOf(PACK_ID to "1")
            )
        }

    }

    private fun renderPacks() {
        if (pack.size > 0) {
            binding.transInfo1TitleValue.text = pack[0].biomassID.toString()
            binding.transTypeTitleValue.text = pack[0].biomassType
            binding.transWeightTitleValue.text = pack[0].biomassWeight.toString()
            binding.transMoistureTitleValue.text = pack[0].biomassMoisture.toString()
            binding.transCarbonDmTitleValue.text = pack[0].biomassCarbonInDm.toString()
        }
         if (pack.size > 1) {
            binding.transInfo2TitleValue.text = pack[1].biomassID.toString()
            binding.transTypeTitleValue2.text = pack[1].biomassType
            binding.transWeightTitleValue2.text = pack[1].biomassWeight.toString()
            binding.transMoistureTitleValue2.text = pack[1].biomassMoisture.toString()
            binding.transCarbonDmTitleValue2.text = pack[1].biomassCarbonInDm.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}