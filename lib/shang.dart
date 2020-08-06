import 'dart:async';

import 'package:flutter/services.dart';

class Shang {
  static const MethodChannel _channel = const MethodChannel('shang');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('shareInstagramStory');
    return version;
  }

  static Future<String> shareInstagramStory(
      String imagePath,
      String backgroundTopColor,
      String backgroundBottomColor,
      String attributionURL) async {
    Map<String, dynamic> args;

    // final tempDir = await getTemporaryDirectory();

    // File file = File(imagePath);
    // Uint8List bytes = file.readAsBytesSync();
    // var stickerdata = bytes.buffer.asUint8List();
    // String stickerAssetName = 'stickerAsset.png';
    // final Uint8List stickerAssetAsList = stickerdata;
    // final stickerAssetPath = '${tempDir.path}/$stickerAssetName';
    // file = await File(stickerAssetPath).create();
    // file.writeAsBytesSync(stickerAssetAsList);
    args = <String, dynamic>{
      // "stickerImage": stickerAssetName,
      "backgroundTopColor": backgroundTopColor,
      "backgroundBottomColor": backgroundBottomColor,
      "attributionURL": attributionURL
    };
    final String response =
        await _channel.invokeMethod('shareInstagramStory', args);
    return response;
  }
}
