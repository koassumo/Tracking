package com.igo.tracking.ui.transport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentTransportBinding
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biopack


class TransportFragment : Fragment() {


    companion object {
        var packsNumber: Int = 0

        val pack: ArrayList<Biopack> = ArrayList(10)
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
                bundleOf(WF_ID to "1")
            )
        }

    }

    private fun renderPacks() {
        if (pack.size > 0) {
            binding.transInfo1TitleValue.text = pack[0].bioID.toString()
            binding.transTypeTitleValue.text = pack[0].bioType
            binding.transWeightTitleValue.text = pack[0].bioWeight.toString()
            binding.transMoistureTitleValue.text = pack[0].bioMoisture.toString()
            binding.transCarbonDmTitleValue.text = pack[0].bioCarbonInDm.toString()
        }
         if (pack.size > 1) {
            binding.transInfo2TitleValue.text = pack[1].bioID.toString()
            binding.transTypeTitleValue2.text = pack[1].bioType
            binding.transWeightTitleValue2.text = pack[1].bioWeight.toString()
            binding.transMoistureTitleValue2.text = pack[1].bioMoisture.toString()
            binding.transCarbonDmTitleValue2.text = pack[1].bioCarbonInDm.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}