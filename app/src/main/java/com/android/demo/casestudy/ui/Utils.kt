package com.android.demo.casestudy.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.android.demo.casestudy.R
import com.android.demo.casestudy.network.Resource
import com.google.android.material.snackbar.Snackbar


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}


fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackBar(
            getString(R.string.network_error),
            retry
        )
        failure.errorCode == 401 -> {
            if (this is MainFragment) {
                requireView().snackBar(getString(R.string.wrong_error))
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackBar(error)
        }
    }
}

