package com.example.flutter_demo2

import com.example.flutter_demo2.flutterplugin.FlutterPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        //给当前页面注册组件
        FlutterPlugin.registerWith(flutterEngine, this)
    }
}
