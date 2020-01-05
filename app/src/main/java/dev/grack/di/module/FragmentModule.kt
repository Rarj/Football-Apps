package dev.grack.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.grack.features.pastmatch.PastMatchFragment

@Module
abstract class FragmentModule {

  @ContributesAndroidInjector
  abstract fun contributePastMatch(): PastMatchFragment

}