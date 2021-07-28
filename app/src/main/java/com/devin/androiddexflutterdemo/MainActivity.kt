package com.devin.androiddexflutterdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.nextBtn).setOnClickListener {
            startFlutterActivity()
        }
        findViewById<Button>(R.id.cacheBtn).setOnClickListener {
            startCacheFlutterActivity()
        }
        findViewById<Button>(R.id.engineGroupBtn).setOnClickListener {
            var intent = Intent(this, SingleFlutterActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.loginBtn).setOnClickListener {
            startCacheFlutterLoginActivity()
        }
    }

    fun startDefaultFlutterActivity() {
        var intent = FlutterActivity.createDefaultIntent(this)
        startActivity(intent)
    }

    fun startFlutterActivity() {
        var intent = FlutterActivity
            .withNewEngine()
            .initialRoute("/main")
            .build(this)
        startActivity(intent)
    }

    fun startCacheFlutterActivity() {
        var intent = FlutterActivity
            .withCachedEngine(Const.EngineId.mainEngineId)
            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)  // background transparent
            .build(this)
        startActivity(intent)
    }

    fun startCacheFlutterLoginActivity() {
        var intent = FlutterActivity
            .withCachedEngine(Const.EngineId.loginEngineId)
            .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)  // background transparent
            .build(this)
        startActivity(intent)
    }
}