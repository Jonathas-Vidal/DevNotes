package com.jonathasdev.devnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {

//Live Data: Componente OBSERVÁVEL que reconhece o Ciclo de Vida da Activity.
    //Ele observa enquanto a Activity/Fragment for visível, para consumir menos recursos
    //(PADRÃO) Criar um observável Mutable para receber dados, e expor um Imutável contendo
    //Os dados do Mutable, sem risco de serem manipulados por outras classes


        private var currentNotes = listOf<Note>()
        private val _notes: MutableLiveData<List<Note>> = MutableLiveData(emptyList())
        val notes: LiveData<List<Note>> = _notes

        fun remove(note: Note) {
            val newList = _notes.value.orEmpty().toMutableList()
            newList.remove(note)
            _notes.value = newList
            currentNotes = newList
        }

        fun add(vararg notes: Note) {
            val newList = _notes.value.orEmpty().toMutableList()
            newList.addAll(notes)
            _notes.value = newList
            currentNotes = newList
        }

        fun searchNote(key: String) {
            if (key.isNotEmpty() && key.isNotBlank()) {
                _notes.value = currentNotes.filter {
                    it.title.contains(
                        key,
                        ignoreCase = true
                    ) || it.body.contains(key, ignoreCase = true)
                }
            } else {
                _notes.value = currentNotes
            }
        }

    }
