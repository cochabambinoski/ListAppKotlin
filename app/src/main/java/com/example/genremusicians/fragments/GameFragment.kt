package com.example.genremusicians.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.genremusicians.R
import com.example.genremusicians.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(
        val text:String,
        val answers:List<String>
    )

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What's the name of the creator of Apple?",
                answers =   listOf("Steve Jobs","Bill Gates","Steve Wozniak","Tim Cook")),
        Question(text = "What's the name of the last Beatle alive?",
            answers =   listOf("Paul McCartney","Ringo Star","John Lennon","George Harrison")),
        Question(text = "What's the name of the Pandemy that hit the world in 2020?",
            answers =   listOf("Covid 19","Covid 20","Covid 18","Covid 21"))
    )

    lateinit var currentQuestion: Question

    lateinit var answers: MutableList<String>

    private var questionIndex = 0

    private val numQuestions = Math.min((questions.size + 1) / 1, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,R.layout.fragment_game,container,false)

        randomizeQuestions()

        binding.game = this

        binding.submitButtom.setOnClickListener {
            view:View->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            if(checkedId != -1){
                var answerIndex = 0
                when (checkedId){
                    R.id.secondAnswerRadioButton->answerIndex=1
                    R.id.thirdAnswerRadioButton->answerIndex=2
                    R.id.fourthAnswerRadioButton->answerIndex=3
                }

                if(answers[answerIndex] == currentQuestion.answers[0])
                {
                    questionIndex++
                    if(questionIndex < numQuestions){
                        setQuestion()
                        binding.invalidateAll()
                    } else{
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                } else{
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }

        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
    }

}