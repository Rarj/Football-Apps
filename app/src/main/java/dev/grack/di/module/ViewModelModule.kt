package dev.grack.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.grack.di.factory.ViewModelFactory

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//  @Binds
//  @IntoMap
//  @ViewModelKey(DetailViewModel::class)
//  protected abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

}