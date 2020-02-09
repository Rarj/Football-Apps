package dev.grack.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.grack.di.factory.ViewModelFactory
import dev.grack.features.nextmatch.NextMatchViewModel
import dev.grack.features.parent.ParentViewModel
import dev.grack.features.pastmatch.PastMatchViewModel

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(ParentViewModel::class)
  protected abstract fun parentViewModel(parentViewModel: ParentViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PastMatchViewModel::class)
  protected abstract fun pastMatchViewModel(pastMatchViewModel: PastMatchViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(NextMatchViewModel::class)
  protected abstract fun nextMatchViewModel(nextMatchViewModel: NextMatchViewModel): ViewModel

}