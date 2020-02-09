package dev.grack.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.grack.features.nextmatch.NextMatchFragment
import dev.grack.features.pastmatch.PastMatchFragment

@Module
abstract class FragmentModule {

  @ContributesAndroidInjector
  abstract fun contributePastMatch(): PastMatchFragment

  @ContributesAndroidInjector
  abstract fun contributeNextMatch(): NextMatchFragment
}