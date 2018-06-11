package com.kvest.adapters.ext

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> AppCompatActivity.withViewModel(
        factory: ViewModelProvider.Factory,
        body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}