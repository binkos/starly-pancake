package com.binkos.starlypancacke.app.common.extensions

import android.content.Context
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.binkos.starlypancacke.data.common.coroutine.CoroutineContextProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

inline fun <T> LiveData<T>.subscribe(
    owner: LifecycleOwner,
    crossinline onDataReceived: (T) -> Unit
) =
    observe(owner, Observer { onDataReceived(it) })

fun showSnackBar(@StringRes message: Int, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun showSnackBar(message: String, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
    setOnClickListener { onClick() }
}

fun FragmentActivity.showFragment(
    fragment: Fragment,
    @IdRes container: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
        .replace(container, fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}

inline fun ViewModel.launch(
    coroutineContext: CoroutineContext = CoroutineContextProvider().main,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) { block() }
}

fun Fragment.tryToGetString(
    key: String,
    exceptionMessage: String = composeErrorMessage(key)
): String {
    return this.arguments?.getString(key) ?: throwArgumentException(exceptionMessage)
}

fun Fragment.tryToGetStringOrNull(key: String): String? = this.arguments?.getString(key)

fun Fragment.tryToGetInt(
    key: String,
    exceptionMessage: String = composeErrorMessage(key)
): Int {
    return this.arguments?.getInt(key) ?: throwArgumentException(exceptionMessage)
}

fun Fragment.tryToGetBoolean(
    key: String,
    exceptionMessage: String = composeErrorMessage(key)
): Boolean {
    return this.arguments?.getBoolean(key) ?: throwArgumentException(exceptionMessage)
}

fun <T : Parcelable> Fragment.tryToGetParcelable(
    key: String,
    exceptionMessage: String = composeErrorMessage(key)
): T {
    return this.arguments?.getParcelable(key) ?: throwArgumentException(exceptionMessage)
}

fun <T : Parcelable> Fragment.tryToGetArrayList(
    key: String,
    exceptionMessage: String = composeErrorMessage(key)
): ArrayList<T> {
    return this.arguments?.getParcelableArrayList(key) ?: throwArgumentException(exceptionMessage)
}

fun Fragment.getColor(@ColorRes id: Int) = ContextCompat.getColor(this.requireContext(), id)

private fun <T> throwArgumentException(message: String): T = throw IllegalArgumentException(message)

private fun composeErrorMessage(key: String) = "Argument $key hasn't been provided"