package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentQuizBinding
import com.example.myapplication.databinding.FragmentWelcomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var _binding : FragmentQuizBinding? = null
    val binding get() = _binding!!
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (findNavController().currentDestination != null) {
                toWelcomePage()
            }
        }
        with(binding){
            firstRadio.setOnCheckedChangeListener { group, checkedId ->
                sendResults.isEnabled = checkForButton()
            }
            secondRadio.setOnCheckedChangeListener { group, checkedId ->
                sendResults.isEnabled = checkForButton()
            }
            thirdRadio.setOnCheckedChangeListener { group, checkedId ->
                sendResults.isEnabled = checkForButton()
            }
            sendResults.setOnClickListener {
                findNavController().navigate(R.id.action_quizFragment_to_resultsFragment, bundle)
            }
            backButton.setOnClickListener {
                toWelcomePage()
            }
        }
    }

    fun toWelcomePage(){
        findNavController().popBackStack(R.id.welcomeFragment, false)
    }

    fun checkForButton(): Boolean{
        with(binding){
            if(firstRadio.checkedRadioButtonId != -1 &&
                secondRadio.checkedRadioButtonId != -1 &&
                thirdRadio.checkedRadioButtonId != -1){
                checkAnswers()
                return true
            }
        }
        return false
    }

    fun checkAnswers(){
        var answ = 0
        with(binding){
            if(leftFirst.isChecked) answ ++
            if(leftSecond.isChecked) answ ++
            if(rightThird.isChecked) answ ++
        }
        bundle.putString("result", answ.toString())
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}