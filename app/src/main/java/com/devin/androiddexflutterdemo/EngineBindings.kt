package dev.flutter.multipleflutters

import android.app.Activity
import com.devin.androiddexflutterdemo.Const
import com.devin.androiddexflutterdemo.MyApplication
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

/**
 * This interface represents the notifications an EngineBindings may be receiving from the Flutter
 * instance.
 *
 * What methods this interface has depends on the messages that are sent over the EngineBinding's
 * channel in `main.dart`.  Messages that interact with the DataModel are handled automatically
 * by the EngineBindings.
 *
 * @see main.dart for what messages are getting sent from Flutter.
 */
interface EngineBindingsDelegate {
    fun onNext()
}

/**
 * This binds a FlutterEngine instance with the DataModel and a channel for communicating with that
 * engine.
 *
 * Messages involving the DataModel are handled by the EngineBindings, other messages are forwarded
 * to the EngineBindingsDelegate.
 *
 * @see main.dart for what messages are getting sent from Flutter.
 */
class EngineBindings(activity: Activity, delegate: EngineBindingsDelegate, entrypoint: String, engineId: String) {
    val channel: MethodChannel
    val engine: FlutterEngine
    var engineId: String
    val delegate: EngineBindingsDelegate

    init {
        val app = activity.applicationContext as MyApplication
        // This has to be lazy to avoid creation before the FlutterEngineGroup.
        val dartEntrypoint =
            DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(), entrypoint
            )
        engine = app.engines.createAndRunEngine(activity, dartEntrypoint)
//        engine.navigationChannel.setInitialRoute("/home")
        // cache engine
        FlutterEngineCache.getInstance().put(engineId, engine)

        this.delegate = delegate
        this.engineId = engineId
        channel = MethodChannel(engine.dartExecutor.binaryMessenger, "multiple-flutters")
    }

    /**
     * This setups the messaging connections on the platform channel and the DataModel.
     */
    fun attach() {
        channel.setMethodCallHandler { call, result ->
            when (call.method) {
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    /**
     * This tears down the messaging connections on the platform channel and the DataModel.
     */
    fun detach() {
        // TODO: Uncomment after https://github.com/flutter/engine/pull/24644 is on stable.
        // engine.destroy();
        channel.setMethodCallHandler(null)
    }
}
