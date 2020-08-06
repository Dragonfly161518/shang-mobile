package com.example.shang_example

import android.content.Intent
import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
  private var sharedText: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)

    handleShareIntent()

    MethodChannel(flutterView, "app.channel.shared.data")
    .setMethodCallHandler { call, result ->
      if (call.method.contentEquals("getSharedText")) {
        result.success(sharedText)
        sharedText = null
      }
    }
  }

  fun handleShareIntent() {
    val intent: Intent = getIntent();
    val action = intent.action
    val type = intent.type

    if (Intent.ACTION_SEND.equals(action) && type != null) {
      if ("text/plain".equals(type)) {
        handleSendText(intent)
      }
    }
  }

  fun handleSendText(intent: Intent) {
    sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
  }
    
}
