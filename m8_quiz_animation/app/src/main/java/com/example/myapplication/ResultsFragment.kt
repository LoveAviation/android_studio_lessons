package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentResultsBinding
import com.example.myapplication.databinding.FragmentWelcomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var _binding : FragmentResultsBinding? = null
    val binding get() = _binding!!

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
        _binding = FragmentResultsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (findNavController().currentDestination != null) {
                findNavController().popBackStack(R.id.welcomeFragment, false)
            }
        }
        val resText = getString(R.string.results_text)
        binding.resultsText.setText("$resText ${arguments?.getString("result")}/3")


        with(binding) {
            view.postDelayed({
                val centerX = view.width / 2f
                val centerY = view.height / 2f

                val textAnimatorX =
                   ObjectAnimator.ofFloat(resultsText, "translationX", centerX - resultsText.width / 2f)
                textAnimatorX.duration = 500

                val buttonAnimatorX =
                    ObjectAnimator.ofFloat(restartButton, "translationX", centerX - restartButton.width / 2f)
                buttonAnimatorX.duration = 500

                val TextfadeInAnimator = ObjectAnimator.ofFloat(resultsText, "alpha", 0f, 1f)
                TextfadeInAnimator.duration = 500
                val ButtonfadeInAnimator = ObjectAnimator.ofFloat(restartButton, "alpha", 0f, 1f)
                ButtonfadeInAnimator.duration = 500
                val ImagefadeinAnimator = ObjectAnimator.ofFloat(binding.lottieAnim, "alpha", 0f, 1f)
                ImagefadeinAnimator.duration = 800

                val animatorSet = AnimatorSet()
                animatorSet.playTogether(
                    textAnimatorX,
                    buttonAnimatorX,
                    TextfadeInAnimator,
                    ButtonfadeInAnimator,
                    ImagefadeinAnimator
                    )
                animatorSet.start()
            }, 1000)
        }




        binding.restartButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_quizFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}