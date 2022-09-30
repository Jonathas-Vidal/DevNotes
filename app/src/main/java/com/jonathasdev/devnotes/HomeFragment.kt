package com.jonathasdev.devnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.jonathasdev.devnotes.databinding.FragmentHomeBinding

//Classe do fragment. Um fragment é uma Parte/Seção da Activity. Todo fragment precisa de uma Activity
//Um App pode ser totalmente feito com uma Activity e diversos fragments.
//O Fragment tem seu próprio Ciclo de Vida, diferente do Ciclo das activities

class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<NotesViewModel>()
    private lateinit var binding : FragmentHomeBinding
    val fButton : ExtendedFloatingActionButton
        get() = (activity as? MainActivity)?.fButton!!


//Primeira etapa do Lifecycle do Fragment. Aqui é quando está criando o Fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
//Segunda etapa do lifecycle do Fragment: local para trabalhar com o Fragment já criado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fButton.shrink() //Encolher o Botão
        fButton.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.fragment_navContainer,NewNotesFragment())
            }
        }
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        fun newInstance(text: String) : HomeFragment {
            val fragment = HomeFragment()
            fragment.arguments = Bundle().apply {
                putString(TITLE_KEY,text)
            }
            return fragment
        }
    }

}