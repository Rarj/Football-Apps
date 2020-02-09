package dev.grack.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, T : BaseViewModel> : AppCompatActivity() {

  protected var binding: B? = null
  protected var viewModel: T? = null

  @Inject
  internal lateinit var viewModelFactory: ViewModelProvider.Factory

  protected fun bindView(@LayoutRes layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
  }

  abstract fun setViewModel(): T?

  abstract fun initInjector()

}