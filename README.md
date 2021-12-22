# flutter与Android原生交互
Flutter 定义了三种不同的 Channel

MethodChanel：用于传递方法调用（method invocation）
EventChannel：用于事件流的发送（event streams）
MessageChannel：用于传递字符串和半结构化的消息

本案例只写了MethodChanel
Android端
首先，创建MethodChannel，在FlutterPlugin定义final类型的CHANNEL，通常为“包名/标识”，这里一定要和跟flutter中使用相同的注册字符串，否则无法完成互调。registerWith方法用于注册渠道。然后，设置MethodCallHander，methodCall中传递来自flutter的参数，通过result返回给flutter结果。本案例做了4个操作，详见注释
MainActivity，继承FlutterActivity ,覆写configureFlutterEngine方法，给此类注册组件
注意：
application需要增加主题，否则报错
Android的启动页换成OneActivity，否则启动会直接跳转到flutter页

Flutter端
创建MethodChannel，并注册channel名，这里再提醒一下，要与native里“包名/标识”的一致
通过invokeMethod发起异步调用，invokeMethod接受两个参数：
method：调用的native方法名
arguments：nativie方法参数，有多个参数时需要以map形式指定
## Demo详细描述
https://blog.csdn.net/u011124212/article/details/122086550

作者QQ：798462287


