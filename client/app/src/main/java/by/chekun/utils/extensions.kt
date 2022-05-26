package by.chekun.utils

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.content.SharedPreferences
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import by.chekun.R


fun Activity.hideKeyboardEx() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.apply { imm.hideSoftInputFromWindow(windowToken, 0) }
}

fun Activity.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
fun Activity.showSnack(text: String) = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

