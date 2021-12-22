import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  //获取插件与原生Native的交互通道
  static const platForm = const MethodChannel('com.example.flutter_demo2/jump_plugin');
  //具体要做的功能
  void _goToNativeActivity() async {
    String result = await platForm.invokeMethod('trun2Second');
    print(result);
  }

  // 具体要做的功能
  void _sendDataToNative() async {
    Map<String, String> map = {"flutter": "我是flutter 传递过来的"};
    String result = await platForm.invokeMethod('mapData', map);
    print(result);
  }

  String nativeBackString = 'Not return';

  void _invokeNativeGetResult() async {
    String backString;
    try {
      // 调用原生方法并传参，以及等待原生返回结果数据
      var result = await platForm.invokeMethod('getNativeResult', {'key1':'flutter参数1','key2':'flutter参数2'});
      backString = 'Native return $result';
    } on PlatformException catch (e) {
      backString = "Failed to get native return: '${e.message}'.";
    }
    setState(() {
      nativeBackString = backString;
    });
  }

  void _goBackToNative(){
    if (Navigator.canPop(context)) {  // 返回上一页
      Navigator.of(context).pop();
    } else {
      platForm.invokeMethod('goBack');
    }
  }

  @override
  void initState() {
    super.initState();
    }


  @override
  Widget build(BuildContext context) {
    // flutter 注册原生监听方法
    return new Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

            RaisedButton(
              child: Text('跳转到第二个原生页面'),
              onPressed: () {
                _goToNativeActivity();
              },
            ),
            RaisedButton(
              child: Text('flutter向原生传递数据'),
              onPressed: () {
                _sendDataToNative();
              },
            ),
            RaisedButton(
              child: Text('调用原生方法并传参'+'\n原生传递过来的值 $nativeBackString'),
              onPressed: () {
                _invokeNativeGetResult();
              },
            ),

            RaisedButton(
              child: Text('返回第一个原生界面'),
              onPressed: () {
                _goBackToNative();
              },
            ),
          ],
        ),
      ),
    );
  }

}
