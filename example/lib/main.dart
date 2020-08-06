import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:social_share/social_share.dart';
import 'package:image_picker/image_picker.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: RaisedButton(
            onPressed: () async {
              File file =
                  await ImagePicker.pickImage(source: ImageSource.gallery);
              SocialShare.shareInstagramStory(
                      file.path, "#ffffff", "#000000", "https://deep-link-url")
                  .then((data) {
                print(data);
              });
            },
            child: Text("Share On Instagram Story"),
          ),
        ),
      ),
    );
  }
}
