package dev.grack.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.grack.features.parent.ParentActivity

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract fun contributeParentActivity(): ParentActivity

}