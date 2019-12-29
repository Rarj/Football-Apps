package dev.grack.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.grack.AppController
import dev.grack.di.module.ActivityModule
import dev.grack.di.module.ApiModule
import dev.grack.di.module.FragmentModule
import dev.grack.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ApiModule::class,
    ViewModelModule::class, AndroidSupportInjectionModule::class,
    ActivityModule::class, FragmentModule::class]
)
interface ApiComponents {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    @BindsInstance
    fun apiModule(apiModule: ApiModule): Builder

    fun build(): ApiComponents
  }

  fun inject(appController: AppController)

}