package com.github.bgrebennikov.devbuff.di.keys

import androidx.lifecycle.ViewModel
import dagger.MapKey
import dagger.multibindings.StringKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val viewModel: KClass<out ViewModel>)
