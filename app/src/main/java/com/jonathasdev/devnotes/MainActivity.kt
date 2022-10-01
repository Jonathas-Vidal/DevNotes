package com.jonathasdev.devnotes

import com.jonathasdev.devnotes.databinding.ActivityMainBinding

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NotesViewModel>()
    private lateinit var binding: ActivityMainBinding
    val fbutton: ExtendedFloatingActionButton
        get() = binding.fButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val navhost = binding.fragmentNavContainer.id
        supportFragmentManager.commit {
            add(navhost, HomeFragment())
        }
    }
}

fun AppCompatActivity.addFragment(host: Int, fragment: Fragment) {
    supportFragmentManager.commit {
        add(host, fragment)
    }
}