package com.example.genremusicians.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.genremusicians.R
import com.example.genremusicians.databinding.FragmentGameOverBinding
import com.example.genremusicians.databinding.FragmentGameWonBinding

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        var binding = DataBindingUtil.inflate<FragmentGameOverBinding>(inflater,R.layout.fragment_game_over,container,false)
        binding.tryAgainButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_gameOverFragment_to_gameFragment)
        }
        return binding.root
    }
}