package dev.grack

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.grack.di.component.DaggerApiComponents
import dev.grack.di.module.ApiModule
import javax.inject.Inject

class AppController : Application(), HasAndroidInjector {
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  override fun androidInjector(): AndroidInjector<Any> {
    return dispatchingAndroidInjector
  }

  override fun onCreate() {
    super.onCreate()

    Logger.addLogAdapter(AndroidLogAdapter())
    val formatStrategy = PrettyFormatStrategy.newBuilder()
      .showThreadInfo(false)
      .tag("MY_LOGGER")
      .build()

    Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

    DaggerApiComponents.builder()
      .application(this)
      .apiModule(ApiModule())
      .build().inject(this)
  }
}