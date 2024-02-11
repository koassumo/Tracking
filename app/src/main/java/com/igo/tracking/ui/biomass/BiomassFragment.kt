package com.igo.tracking.ui.biomass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentBiomassBinding
import com.igo.tracking.model.constants.*


class BiomassFragment : Fragment() {

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
//


        binding.biomassAddBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_nav_biomass_to_biomassSelectFragment,
                bundleOf(PACK_ID to "1")
            )
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}