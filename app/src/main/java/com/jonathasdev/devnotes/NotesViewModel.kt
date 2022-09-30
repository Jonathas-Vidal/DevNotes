package com.jonathasdev.devnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {

//Live Data: Componente OBSERVÁVEL que reconhece o Ciclo de Vida da Activity.
    //Ele observa enquanto a Activity/Fragment for visível, para consumir menos recursos
    //(PADRÃO) Criar um observável Mutable para receber dados, e expor um Imutável contendo
    //Os dados do Mutable, sem risco de serem manipulados por outras classes
    private val _notes : MutableLiveData<List<Note>> = MutableLiveData(emptyList())
    val notes : LiveData<List<Note>> = _notes

    //Copia a lista e deixa ela mutável
    fun add(note: Note) {
        val newList = _notes.value?.toMutableList()
        //Add nova nota
        newList?.add(note)
        //Se a lista estiver nula, solta uma lista vazia
        _notes.value = newList ?: emptyList()

    }
    fun remove(note: Note) {
        val newList = _notes.value?.toMutableList()
        //Remove a nota
        newList?.remove(note)
        _notes.value = newList ?: emptyList()
    }
}