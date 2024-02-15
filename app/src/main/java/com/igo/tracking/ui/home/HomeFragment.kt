package com.igo.tracking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.LatLng
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentHomeBinding
import com.igo.tracking.model.RepositoryProject
import com.igo.tracking.model.constants.PACK_ID
import com.igo.tracking.model.entity.Biomass
import com.igo.tracking.model.entity.Proj

class HomeFragment : Fragment() {

    companion object {
        val proj1: Proj = RepositoryProject.getProj(0)
        val proj2: Proj = RepositoryProject.getProj(1)
        private lateinit var factoryLatLang: LatLng // Поле для хранения координаты второй точки

        val pack: ArrayList<Biomass> = ArrayList(10)
    }


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var harvestLatLng: LatLng // Поле для хранения координаты второй точки


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.projPack1.setOnClickListener {
            findNavController().navigate(
                R.id.action_bm_nav_home_to_bm_nav_biomass
            )
        }

        binding.projPack2.setOnClickListener {
            findNavController().navigate(
                R.id.action_bm_nav_home_to_bm_nav_biomass
            )
        }

        binding.projIconLoc.setOnClickListener {
            findNavController().navigate(
                R.id.action_bm_nav_home_to_locFragment,
                bundleOf(PACK_ID to 0)
            )
        }

        binding.projIconLoc2.setOnClickListener {
            findNavController().navigate(
                R.id.action_bm_nav_home_to_locFragment,
                bundleOf(PACK_ID to 1)
            )
        }


    }
}