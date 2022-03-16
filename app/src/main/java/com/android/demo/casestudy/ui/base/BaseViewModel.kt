package com.android.demo.casestudy.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.android.demo.casestudy.network.Resource
import com.android.demo.casestudy.repository.BaseRepository
import com.android.demo.casestudy.response.CaseStudyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val repository: BaseRepository) : ViewModel() {

    private val _caseStudyResponse : MutableLiveData<Resource<CaseStudyResponse>> = MutableLiveData()
    val caseStudyResponse : LiveData<Resource<CaseStudyResponse>>
        get() =
           _caseStudyResponse


    fun loadCaseStudy()= viewModelScope.launch {
        _caseStudyResponse.value = Resource.Loading
        _caseStudyResponse.value = repository.loadCaseStudy()
    }

}