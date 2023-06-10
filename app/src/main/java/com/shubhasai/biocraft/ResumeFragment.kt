package com.shubhasai.biocraft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.shubhasai.biocraft.databinding.FragmentResumeBinding

class ResumeFragment : Fragment() {
    private lateinit var binding:FragmentResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResumeBinding.inflate(layoutInflater)
        binding.btnCreativeninja.setOnClickListener{
            val directions = ResumeFragmentDirections.actionResumeFragmentToCreativeninjaFragment()
            findNavController().navigate(directions)
        }
        binding.btnDevgenius.setOnClickListener{
            val directions = ResumeFragmentDirections.actionResumeFragmentToDevgeniusFragment()
            findNavController().navigate(directions)
        }
        binding.btnDigitalpro.setOnClickListener {
            val directions = ResumeFragmentDirections.actionResumeFragmentToDigitalproFragment()
            findNavController().navigate(directions)
        }
        binding.btnMarketmaster.setOnClickListener {
            val directions = ResumeFragmentDirections.actionResumeFragmentToMarketmasterFragment()
            findNavController().navigate(directions)
        }
        return binding.root
    }
}