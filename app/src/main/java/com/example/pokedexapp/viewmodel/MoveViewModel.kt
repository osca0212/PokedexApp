package com.example.pokedexapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.remote.MoveDetailResponse
import com.example.pokedexapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoveViewModel : ViewModel() {

    private val _moveDetail = MutableStateFlow<MoveDetailResponse?>(null)
    val moveDetail: StateFlow<MoveDetailResponse?> = _moveDetail

    fun fetchMoveDetail(moveName: String) {
        viewModelScope.launch {
            try {
                val move = RetrofitInstance.api.getMoveDetail(moveName)
                _moveDetail.value = move
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
