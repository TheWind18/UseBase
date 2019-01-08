# UseBase
use base lib 基础类再收集，都搞好几个了。    

- [AnimUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/anim/AnimUtils.java);       
> 用于收集一些常见动画    
```
void jitter(View pView);//左右抖动动画    
```    

- [BaseApp](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/app/BaseApp.java);   
- [ExceptionHandler](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/app/ExceptionHandler.java);   
> 用于收集报错，日志存放于：/Android/data/.../files/log/，分日期存放   
```
ExceptionHandler.getInstance().init(context);//必须先调用初始化   
```     

- [DoAsync](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/async/DoAsync.java);    
> 对AsyncTask的封装，没有使用原生的AsyncTask，使用了：https://github.com/litesuits/android-lite-async      
```
new DoAsync<Void,Void,Void>(context).execute();//     
```    

- [WeakHandler](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/async/WeakHandler.java);    
> 使用方法同原生Handler    

- [DensityUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/density/DensityUtils.java);//密度转换   
```
int dp2px(Context context, float dpVal);///
int sp2px(Context context, float spVal);//
float px2dp(Context context, float pxVal);//
float px2sp(Context context, float pxVal);//
void fontScale(Context context);//设置字体不缩放，里面可设置值
```     

- [DeviceUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/device/DeviceUtils.java);//   
> 获取设备信息    
```
String getImei(Context context);//需要权限：Manifest.permission.READ_PHONE_STATE    
String getMac(Context pTarget);//首次需要连接WIFI，DeviceUtils.DEFAULT_WAC是错值，返回这个的那需要连WIFI   
String getAndroidID(Context context);
String getAndroidType();   
```    

- [Md5Utils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/encrypt/Md5Utils.java);   
```
String md5(String plainText);//md5
```    

- [FileCompare](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/file/FileCompare.java);   
> 文件对比    
```
boolean MD5Compare(File src, File des)
boolean MD5Compare(InputStream src, InputStream des)
boolean MD5Compare(InputStream src, File des)
String getFileMD5(String filePath)
String getFileMD5(File file)
String getFileMD5(InputStream in) 
String getPartFileMD5(InputStream in, int length)
String getPartFileMD5(InputStream in, long start, int length)
boolean ShaCompare(File src, File des)
boolean ShaCompare(InputStream src, InputStream des)
boolean ShaCompare(InputStream src, File des)
String getFileSha1(File file)
String getFileSha1(InputStream in)
```    

- [FileIOandOperation](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/file/FileIOandOperation.java);   
> 文件操作    
```
 * File Utils
 * <ul>
 * Read or write file
 * <li>{@link #readFile(String, String)} read file</li>
 * <li>{@link #readFileToList(String, String)} read file to string list</li>
 * <li>{@link #writeFile(String, String, boolean)} write file from String</li>
 * <li>{@link #writeFile(String, String)} write file from String</li>
 * <li>{@link #writeFile(String, List, boolean)} write file from String List
 * </li>
 * <li>{@link #writeFile(String, List)} write file from String List</li>
 * <li>{@link #writeFile(String, InputStream)} write file</li>
 * <li>{@link #writeFile(String, InputStream, boolean)} write file</li>
 * <li>{@link #writeFile(File, InputStream)} write file</li>
 * <li>{@link #writeFile(File, InputStream, boolean)} write file</li>
 * </ul>
 * <ul>
 * Operate file
 * <li>{@link #moveFile(File, File)} or {@link #moveFile(String, String)}</li>
 * <li>{@link #copyFile(String, String)}</li>
 * <li>{@link #getFileExtension(String)}</li>
 * <li>{@link #getFileName(String)}</li>
 * <li>{@link #getFileNameWithoutExtension(String)}</li>
 * <li>{@link #deleteFile(String)}</li>
 * <li>{@link #isFileExist(String)}</li>
 * <li>{@link #isFolderExist(String)}</li>
 * <li>{@link #makeFolders(String)}</li>
 * <li>{@link #makeDirs(String)}</li>
 * </ul>
 * 文件读写，复制移动操作工具类
```    

- [L](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/log/L.java);   
> 日志    
```
public static boolean isDebug = false;//在app模块里可用 L.isDebug=BuildConfig.DEBUG;
其它函数同Log
```    

- [MediaUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/media/MediaUtils.java);   
```   
MediaUtils.getInstance().play(String path);//
MediaUtils.getInstance().playRaw(Context pContext, int id, OnPlayer pPlayer)
MediaUtils.getInstance().play(String nowPath, OnPlayer pOnPlayer)
MediaUtils.getInstance().prepare(String nowPath, OnPlayer pOnPlayer, final boolean prepare2start)
MediaUtils.getInstance().start()
MediaUtils.getInstance().pause();
MediaUtils.getInstance().resume()
MediaUtils.getInstance().stop()
//播放的时候有个原则
1. 可以直接调用play()播放 
2. 可以先调用prepare();过一会再调用start(),进行播放   
```       

- [MetaUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/meta/MetaUtils.java);    
```
 T getMetaData(Context context, String key, T defaultObject);//获取AndroidManifest.xml里meta-data的值
```     

- [NetUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/network/NetUtils.java);//    
```
boolean isConnected(Context context);// 一定有网
boolean isConnected2(Context context);// 连接上了，不一定有网 
boolean isWifi(Context context)
boolean hasWifi(Context context)
boolean isMobile(Context context)
openSetting(Activity activity)
void openWifiSetting(Context pContext)
```    

- [PermissionUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/permission/PermissionUtils.java);   
> 权限相关   
```
boolean hasPermissions(Context pTarget, String... permissions);
boolean verifyPermissions(int[] grantResults)   
```     

- [ScreenUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/screen/ScreenUtils.java);        
```
boolean isPad(Context context)
isLand(Context context)
```     

- [SpUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/sp/SpUtils.java);    
```
void put(Context context, String key, Object object)
 T get(Context context, String key, T defaultObject)
 void remove(Context context, String key)
 void clear(Context context) 
 boolean contains(Context context, String key)
 Map<String, ?> getAll(Context context)
```    

- [UrlUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/url/UrlUtils.java);   
```
String encodeUrl(String input);//url编码  
String appendImgUrl(String url, String defaultAppend);//
```    

- [PackageUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/pack/PackageUtils.java);
> 包相关   
```
boolean isPackageExist(Context context, String packageName)  //应用是否安装
boolean installNormal(Context context, String filePath)  
boolean uninstallNormal(Context context, String packageName)
boolean isSystemApplication(Context context, String packageName)
long getAppVersionCode(Context context, String packageName)

```    

- [AppUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/pack/AppUtils.java);      
```
List<PackageInfo> getUserInstallAppInfo(Context context)
List<PackageInfo> getSystemInstallAppInfo(Context context)
List<PackageInfo> getAllInstalledAppInfo(Context context)
```       

- [SystemUtils](https://github.com/xuanu/UseBase/blob/master/usebaselib/src/main/java/zzx/zeffect/cn/usebaselib/pack/SystemUtils.java);      
> 系统相关操作    
```
void addCalendarEvent(Context pContext, String title, String location, Calendar begin, Calendar end)  添加日历
void startTimer(Context pContext, String message, int seconds)  添加倒计时
void createAlarm(Context pContext, String message, int hour, int minutes, ArrayList<Integer> days) 添加闹钟
int getCurBattery(Context pContext) 获取当前电量
void maxVolume(Context pContext) 设置最大音量
void maxVolume(Context pContext, int volumeType) 设置最大音量 
void minVolume(Context pContext) 设置最小音量
void minVolume(Context pContext, int volumeType) 设置最小音量
void raiseVolume(Context pContext) 调高音量
void raiseVolume(Context pContext, int volumeType) 调高音量
void lowerVolume(Context pContext)  调低音量
void lowerVolume(Context pContext, int volumeType) 调低音量
int getScreenBrightness(Context pContext) 获取屏幕亮度
void lowerBrightness(Context context) 调低屏幕亮度
void raiseBrightness(Context context) 调高屏幕亮度
void setScreenBrightness(Context pContext, int lighit) 设置屏幕亮度
void setScrennManualMode(Context pContext) 设置手动调节亮度模式
void setWindowBrightness(Activity activity, int brightness)  设置当前应用的亮度
```     


