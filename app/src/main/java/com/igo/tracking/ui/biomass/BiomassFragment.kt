package com.igo.tracking.ui.biomass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.igo.tracking.R
import com.igo.tracking.databinding.FragmentBiomassBinding
import com.igo.tracking.model.FakeNotesRepository
import com.igo.tracking.model.RepWf
import com.igo.tracking.model.RepWf.getListWfs
import com.igo.tracking.model.constants.*
import com.igo.tracking.model.entity.Biopack


class BiomassFragment : Fragment() {


    companion object {
        var packsNumber: Int = 0

        val pack: ArrayList<Biopack> = ArrayList(10)
    }

    private var _binding: FragmentBiomassBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // 1. ADAPTER создаем
    private val mRvAdapter: NotesRvAdapter by lazy { NotesRvAdapter() }

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

        // 2. На "rv в xml" накладываем layout ( можно Grid или любой)
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        // 3. На "rv в xml" накладываем созданный в п.1 адаптер
        binding.rvNotes.adapter = mRvAdapter


        // ВАРИАНТ 1. передаем данные (которые забрали в моделе) из сохраненных
        mRvAdapter.updateNote(getListWfs())



        renderPacks()

        binding.biomassAddBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_nav_biomass_to_biomassSelectFragment,
                bundleOf(WF_ID to "1")
            )
        }


//        // getting data when return from selectFragment
//        parentFragmentManager.setFragmentResultListener(RESPOND, viewLifecycleOwner) { _, data ->
//            binding.biomassMoistureTitle.text = "fd"
//        }
        // (_2) Здесь отлавливаем передаваемые данные при возврате с последующего экрана (по методу _2), БЕЗ ОБРАБОТКИ ДАННЫХ !!!
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(RESPOND)?.observe(viewLifecycleOwner) {
            val index = it
            Toast.makeText(requireContext(), "Random number NOT processed: $index", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderPacks() {
        if (pack.size > 0) {
            binding.biomassInfo1TitleValue.text = pack[0].bioID.toString()
            binding.biomassTypeTitleValue.text = pack[0].bioType
            binding.biomassWeightTitleValue.text = pack[0].bioWeight.toString()
            binding.biomassMoistureTitleValue.text = pack[0].bioMoisture.toString()
            binding.biomassCarbonDmTitleValue.text = pack[0].bioCarbonInDm.toString()
        }
         if (pack.size > 1) {
            binding.biomassInfo2TitleValue.text = pack[1].bioID.toString()
            binding.biomassTypeTitleValue2.text = pack[1].bioType
            binding.biomassWeightTitleValue2.text = pack[1].bioWeight.toString()
            binding.biomassMoistureTitleValue2.text = pack[1].bioMoisture.toString()
            binding.biomassCarbonDmTitleValue2.text = pack[1].bioCarbonInDm.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}