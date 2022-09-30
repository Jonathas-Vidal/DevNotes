package com.jonathasdev.devnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.jonathasdev.devnotes.databinding.FragmentNewNotesBinding

class NewNotesFragment : Fragment() {

    private val viewModel by activityViewModels<NotesViewModel>()
    private lateinit var binding : FragmentNewNotesBinding
    val fButton : ExtendedFloatingActionButton
        get() = (activity as? MainActivity)?.fButton!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fButton.apply {
            icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_save_24)
            text = context.getString(R.string.text_save_notes)
            extend()
            setOnClickListener{
                val title = binding.editTitle.text.toString()
                val body = binding.editBody.text.toString()
                viewModel.add(
                    Note(title,body)
                )
                activity?.onBackPressed()
            }
        }
        binding.toolbar2.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}