package com.android.demo.casestudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.demo.casestudy.R
import com.android.demo.casestudy.data.EspressoIdlingResource
import com.android.demo.casestudy.data.FakeCaseStudy.FAKE_NETWORK_DELAY
import com.android.demo.casestudy.data.FakeCaseStudy.caseStudy
import com.android.demo.casestudy.databinding.FragmentMainBinding
import com.android.demo.casestudy.network.Resource
import com.android.demo.casestudy.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: BaseViewModel by viewModels()
    private val caseAdapter = CaseAdapter()
    private lateinit var binding: FragmentMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        EspressoIdlingResource.increment()

        binding.progressbarCasestudy.visible(false)
        binding.recyclerviewCasestudy.adapter = caseAdapter

        viewModel.caseStudyResponse.observe(viewLifecycleOwner) {
            binding.progressbarCasestudy.visible(it is Resource.Loading)
            when (it) {

                is Resource.Success -> {
                    lifecycleScope.launch {
                        caseAdapter.setCaseStudyList(it.value.caseStudies)
                        caseAdapter.notifyDataSetChanged()
                        EspressoIdlingResource.decrement()

                    }
                }
                is Resource.Failure -> handleApiError(it) {
                    requireView().snackBar(getString(R.string.wrong_error))
                    EspressoIdlingResource.decrement()

                }
            }

        }
        viewModel.loadCaseStudy()
    }

    //check with fake data
    //Test with local entries
    fun insertFakeData() {
        val job = GlobalScope.launch(IO) {
            EspressoIdlingResource.increment()
            binding.progressbarCasestudy.visible(true)
            delay(FAKE_NETWORK_DELAY)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Main) {
                binding.progressbarCasestudy.visible(false)
                caseAdapter.setCaseStudyList(caseStudy.toList())
                caseAdapter.notifyDataSetChanged()
                EspressoIdlingResource.decrement()
            }
        }
    }
}