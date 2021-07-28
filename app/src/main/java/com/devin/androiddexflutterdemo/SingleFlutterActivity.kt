package com.devin.androiddexflutterdemo

import android.content.Context
import android.util.Log
import dev.flutter.multipleflutters.EngineBindings
import dev.flutter.multipleflutters.EngineBindingsDelegate
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class SingleFlutterActivity : FlutterActivity() , EngineBindingsDelegate {
    private val engineBindings: EngineBindings by lazy {
        EngineBindings(activity = this, delegate = this, entrypoint = "otherMain", engineId = Const.EngineId.homeEngineId)
    }

//    override fun provideFlutterEngine(context: Context): FlutterEngine? {
//        return engineBindings.engine
//    }

    override fun getCachedEngineId(): String? {
        Log.i("flutter", engineBindings.engineId)
        return engineBindings.engineId
    }

    override fun onNext() {
        TODO("Not yet implemented")
    }
}