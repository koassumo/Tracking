package com.igo.tracking.ui.pyrolysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.igo.tracking.databinding.FragmentPyrolysisBinding
import com.igo.tracking.ui.ProgressManager

class PyrolysisFragment : Fragment() {

    private var _binding: FragmentPyrolysisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val pyrolysisViewModel =
//            ViewModelProvider(this).get(PyrolysisViewModel::class.java)

        _binding = FragmentPyrolysisBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textSlideshow
//        pyrolysisViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val progressBar = binding.pyrProgress1
        val textView = binding.pyrProgressTxtValue1

        binding.pyrPyrProgress1Btn.setOnClickListener {
            ProgressManager.startProgress { progress ->
                progressBar.progress = progress
                textView.text = "$progress%"
            }
        }

        // Если есть сохраненное состояние прогресса, продолжаем с него
        val savedProgress = ProgressManager.getCurrentProgress()
        if (savedProgress > 0) {
            progressBar.progress = savedProgress
            textView.text = "$savedProgress%"
        }

        return root
    }


    override fun onResume() {
        super.onResume()

        // Обновляем прогресс при возвращении во фрагмент
        val savedProgress = ProgressManager.getCurrentProgress()
        if (savedProgress > 0) {
            binding.pyrPyrProgress1Btn.performClick()
            //binding.pyrProgress1.progress = savedProgress
            //binding.pyrProgressTxtValue1.text = "$savedProgress%"
        }

    }
//
//    override fun onPause() {
//        super.onPause()
//        //ProgressManager.pauseProgress()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}