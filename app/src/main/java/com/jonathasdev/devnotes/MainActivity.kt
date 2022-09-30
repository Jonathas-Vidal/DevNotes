package com.jonathasdev.devnotes

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.jonathasdev.devnotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NotesViewModel>()
    private lateinit var binding: ActivityMainBinding
    val fButton : ExtendedFloatingActionButton
        get() = binding.fButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = binding.fragmentNavContainer.id
        supportFragmentManager.commit {
            add(navHost, HomeFragment.newInstance("Dev Notes"))
        }
    }
}
