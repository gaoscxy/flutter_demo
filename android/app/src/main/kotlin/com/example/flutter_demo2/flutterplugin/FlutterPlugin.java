package com.example.flutter_demo2.flutterplugin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.flutter_demo2.SecondActivity;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class FlutterPlugin implements MethodChannel.MethodCallHandler {

    public static final String CHANNEL = "com.example.flutter_demo2/jump_plugin";
    static MethodChannel channel;

    private Activity activity;
    private FlutterPlugin(Activity activity) {
        this.activity = activity;
    }

    public static void registerWith(FlutterEngine flutterEngine, FlutterActivity activity) {
        channel = new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL);
        FlutterPlugin instance = new FlutterPlugin(activity);
        //setMethodCallHandler在此通道上接收方法调用的回调
        channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        //通过MethodCall可以获取参数和方法名，然后再寻找对应的平台业务，本案例做了2个跳转的业务
        //接收来自flutter的指令oneAct
        if (call.method.equals("trun2Second")) {
            //跳转到指定Activity
            Intent intent = new Intent(activity, SecondActivity.class);
            activity.startActivity(intent);

            //返回给flutter的参数
            result.success("success");
        }
        //接收来自flutter的指令twoAct
        else if (call.method.equals("mapData")) {
            //解析参数
            String value = call.argument("flutter");

            Log.v("debug", value);

            Toast.makeText(activity, value, Toast.LENGTH_SHORT).show();
            //返回给flutter的参数
            result.success("success");
        }
        else if (call.method.equals("getNativeResult")) {
            //解析参数
            String value1 = call.argument("key1");
            String value2 = call.argument("key2");
            Log.v("debug", value1 + value2);

            //返回给flutter的参数
            result.success("success");

//            // 创建 HashMap 对象 Sites
//            HashMap<String, String> Sites = new HashMap<String, String>();
//            // 添加键值对
//            Sites.put("key", "flutter");

            //android调用flutter方法 并获取结果
            channel.invokeMethod("flutterMedia", "from android", new MethodChannel.Result() {
                @Override
                public void success(Object o) {
                    // 这里就会输出 "Hello from Flutter"
                    Log.i(">>>>>>>>>>debug", o.toString());
                }
                @Override
                public void error(String s, String s1, Object o) {
                }

                @Override
                public void notImplemented() {

                }
            });
        }else if(call.method.equals("goBack")){
            activity.finish();
        }
        else {
            result.notImplemented();
        }
    }
}

