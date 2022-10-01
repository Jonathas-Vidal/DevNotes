package com.jonathasdev.devnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.jonathasdev.devnotes.databinding.FragmentNewNotesBinding

class NewNotesFragment : Fragment() {

    private val viewModel by activityViewModels<NotesViewModel>()
    private lateinit var binding: FragmentNewNotesBinding
    val fbutton: ExtendedFloatingActionButton
        get() = (activity as? MainActivity)?.fbutton!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbutton.apply {
            icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_save_24)
            text = context.getString(R.string.text_save_notes)
            isEnabled = false
            setOnClickListener {
                val title = binding.editTitle.text.toString()
                val body = binding.editBody.text.toString()
                viewModel.add(
                    Note(title, body)
                )
                activity?.onBackPressed()
            }
        }
        binding.toolbar2.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.editTitle.doOnTextChanged { _, _, _, count ->
            val bodyText = binding.editBody.text
            val hasBody = bodyText.isNotEmpty() && bodyText.isNotBlank()
            if (count > 0 && hasBody) {
                fbutton.extend()
                fbutton.isEnabled = true
            } else {
                fbutton.shrink()
                fbutton.isEnabled = false
            }
        }

        binding.editBody.doOnTextChanged { _, _, _, count ->
            val bodyTile = binding.editTitle.text
            val hasTitle = bodyTile.isNotEmpty() && bodyTile.isNotBlank()
            if (count > 0 && hasTitle) {
                fbutton.extend()
                fbutton.isEnabled = true
            } else {
                fbutton.shrink()
                fbutton.isEnabled = false
            }
        }
    }
}