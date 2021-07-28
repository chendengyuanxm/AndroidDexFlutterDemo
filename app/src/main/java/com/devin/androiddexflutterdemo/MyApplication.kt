package com.devin.androiddexflutterdemo

import android.app.Application
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

class MyApplication : Application() {
    lateinit var engines: FlutterEngineGroup
    lateinit var flutterEngine : FlutterEngine
    lateinit var loginFlutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()
        initFlutterEngine()
    }

    private fun initFlutterEngine() {
        engines = FlutterEngineGroup(this)

        flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("/main")
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(Const.EngineId.mainEngineId, flutterEngine)

        loginFlutterEngine = FlutterEngine(this)
        loginFlutterEngine.navigationChannel.setInitialRoute("/login")
        loginFlutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(),
                "otherMain"
            )
        )
        FlutterEngineCache.getInstance().put(Const.EngineId.loginEngineId, loginFlutterEngine)
    }
}