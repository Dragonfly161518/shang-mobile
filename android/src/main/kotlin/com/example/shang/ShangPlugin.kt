package com.example.shang

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class ShangPlugin(private val registrar: Registrar): MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "shang")
      channel.setMethodCallHandler(ShangPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "shareInstagramStory") {
//      val stickerImage: String? = call.argument("stickerImage")
//      val backgroundImage: String? = call.argument("backgroundImage")

      val backgroundTopColor: String? = call.argument("backgroundTopColor")
      val backgroundBottomColor: String? = call.argument("backgroundBottomColor")
      val attributionURL: String? = call.argument("attributionURL")
//      val file =  File(registrar.activeContext().cacheDir,stickerImage)
//      val stickerImageFile = FileProvider.getUriForFile(registrar.activeContext(), registrar.activeContext().applicationContext.packageName + ".com.shekarmudaliyar.social_share", file)

      val intent = Intent("com.instagram.share.ADD_TO_STORY")
      intent.setType("image/*")
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//      if(backgroundImage!=null){
//        //check if background image is also provided
//        val backfile =  File(registrar.activeContext().cacheDir,backgroundImage)
//        val backgroundImageFile = FileProvider.getUriForFile(registrar.activeContext(), registrar.activeContext().applicationContext.packageName + ".com.shekarmudaliyar.social_share", backfile)
//        intent.setDataAndType(backgroundImageFile,"image/*")
//      }

      intent.putExtra("content_url", attributionURL)
      intent.putExtra("top_background_color", backgroundTopColor)
      intent.putExtra("bottom_background_color", backgroundBottomColor)
      Log.d("", registrar.activity().toString())
      // Instantiate activity and verify it will resolve implicit intent
      val activity: Activity = registrar.activity()
//      activity.grantUriPermission(
//              "com.instagram.android", stickerImageFile, Intent.FLAG_GRANT_READ_URI_PERMISSION)
      if (activity.packageManager.resolveActivity(intent, 0) != null) {
        registrar.activeContext().startActivity(intent)
        result.success("success")
      } else {
        result.success("error")
      }
    } else {
      result.notImplemented()
    }
  }
}
