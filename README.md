#**红外转发器SDK**

- [简介](#Introduction) 
		- [APP SDK简介](#sdk_Introduction)
		- [集成流程](#Integration_Processes)
- [二、轻松集成](#integrated) 
    - [Setp1 下载SDK](#setp1)
    - [Setp2 开发环境配置](#setp2)
    - [Setp3 初始化SDK](#setp3) 
- [三、API文档](#api)  
- [四、更新历史](#history)
## 一、<a name="Introduction">简介</a>
 
- [APP SDK简介](#sdk_Introduction)
- [集成流程](#Integration_Processes) 

### 1.1、<a name="sdk_Introduction">APP SDK 简介</a>

**为简化开发者的APP开发，云智易提供了APP开发套件和相关的支持服务：**

- 提供APP的SDK和开发文档，兼容iOS、Android平台
- 提供APP端通用业务功能块的实例源代码，极大简化开发人员开发工作
- 为企业用户提供专业的技术支持服务
- 本文档只包含透传通讯协议以及红外码库获取协议

**APP开发套件包含以下主要功能：**

- 智能配网功能
- 码库获取
- 透传通讯功能 
- 使开发者简化APP开发流程，快速完成APP开发，开发者仅需关注APP业务功能即可，而相对复杂的协议与错误处理等事项可忽略。

### 1.3、<a name="Integration_Processes" >集成流程</a> 
 
1. 下载、配置APP SDK
2. 通过SDK中HmRemotebleHttpManage接口获取相应的码库（获取码库必须通过我司提供的mac才能有效获取码库）；
3. 透传数据传输过程中需要客户自己提供用户昵称以及用户id； 
 

##二、<a name="integrated">轻松集成</a>

 
- [Setp1 下载SDK](#setp1)
- [Setp2 开发环境配置](#setp2)
- [Setp3 初始化SDK](#setp3)
- [Setp4 注册、登录用户账号](#setp4)
- [Setp5 添加设备](#setp5)
- [Setp6 连接设备](#setp6)
- [Setp7 设备控制 & 接收设备数据](#setp7)
- [Setp8 推送集成](#setp8) 
  
###2.2  <a name="setp1" >Setp 1 下载SDK工具包</a>
1. SDK下载地址 
2. 解压下载的SDK。
 

###2.3 <a name="setp2" >Setp 2 开发环境配置</a>

**Android**

1. 打开Android Studio ，点击菜单 File->New->New Project... 创建一个新的工程；
2. 把解压的 HmapiSdkv1.0.0.jar（具体jar名称可能有区别） 添加到工程 libs目录；
3. 在APP的build.gradle文件中添加sdk、json、http库的引用

```
 dependencies {
    compile 'com.google.code.gson:gson:2.8.2'
    compile 'com.lzy.net:okgo:+'
    compile 'com.lzy.net:okrx:+'
    
}
```

**IOS**



###2.4 <a name="setp3" >Setp 3 初始化SDK</a>

**Android**
 

1. 添加sdk所需要权限

	**Android 代码范例**

	```
	<!-- 访问网络，网络定位需要上网 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 读取日记权限 -->
    <uses-permission android:name="android.permission.READ_LOGS" />
    <!-- 读取手机信息权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- 这个权限用于进行网络定位 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <!-- 这个权限用于访问GPS定位 -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位 -->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <!-- 获取运营商信息，用于支持提供运营商信息相关的接口 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />	```

2. 创建自定义Application 实现onHmDeviceBack接口，并在AndroidManifest.xml中修改android:name为新建的Application

	**Android 代码范例**

	```
	public class MyApp extends Application implements onHmDeviceBack
	```

	```
	 <application
	        android:name=".MyApp"
	        ...
	 </application>
	```

3. 在自定义Application 下的onCreate()函数调用XlinkAgent.init进行SDK初始化

	**Android 代码范例**

	```
	//可以在登录之后，在这里初始化SDK，传入相应的用户昵称以及用户ID
   HmAgent.getInstance().init(this, "用户昵称", "用户ID");
	```

4. 注册SDK回调监听器。至少需要注册一个监听器，可注册多个。


	**Android 代码范例**

	```
	//这里放到数据接收后解码即可。
   HmAgent.getInstance().hmDecoer("数据","秘钥",this);
	//可以在该监听器中直接更新UI
	``` 

####注意：
  调用初始化方法HmAgent.getInstance().init(this, "用户昵称", "用户ID")和添加监听的回调onHmDeviceBack方法不能在子线程中进行操作，否则可能会出错。
 
 

##三、<a name="api">API文档</a>

####Android：

具体参考[SDK接口说明](.../接口文档) 以及demo中的使用

 

##四、<a name="history">更新历史</a>

####Android:

2018-02-05：发布初步版本协议以及SDK
 

 