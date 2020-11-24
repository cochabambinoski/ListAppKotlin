package com.example.genremusicians.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.genremusicians.R
import com.example.genremusicians.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        var binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater,R.layout.fragment_game_won,container,false)
        binding.nextMatchButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_gameWonFragment_to_gameFragment)
        }
        return binding.root
    }

}