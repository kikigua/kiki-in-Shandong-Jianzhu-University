移动设备软件开发A

## 配置文件

## XML文件

### xml声明

```xml
<?xml version="1.0" encoding="utf-8"?>
```

### xml处理指令

（简称PI,processing instruction），可以用来指定解析器如何解析xml文档内容

### XML元素

只有在

```xml
<activity android:name=".MainActivity">
/**
*这里的name为java文件夹中com.xinguan.myapplication中创建的java类
*/
</activity>
```

添加了

```xml
<intent-filter>
    <action android:name:"android.intent.action.MAIN"/>

    <category android:name="android.intent.category.LAUCHER"/>
</intent-filter>
```

最后的样子↓

![](C:\Users\27682\AppData\Roaming\marktext\images\2022-09-05-08-09-31-image.png)

那么对应的java文件才能够运行

在java文件中绑定res-layout当中的.xml才可以运行响应的界面。

#### XML元素间的关系

XML标签允许嵌套，但不允许交叉。子元素的开始标记必须在父元素之前，子元素的结束标记必须在父元素之前。

### XML元素的属性

![](C:\Users\27682\AppData\Roaming\marktext\images\2022-09-05-08-23-01-image.png) 

# 事件监听

![](C:\Users\27682\AppData\Roaming\marktext\images\2022-09-05-08-29-58-image.png)

### 控件

图形用户界面的最基本元素

### 事件源

事件源对象

### 监听器

按钮去执行一段正确的功能代码，就需要监听器去监听哪个控件发出了什么事件。

事件源先和监听器绑定，监听器是监听事件发生的一个对象。

### 事件

单击的动作

# 代码

```xml
@+id/but_01
```

```java
but=(Button)findViewById(R.id.but_01);
```

控件id，＋代表新增的意思。如果不加+号，则是引用其他的控件的id
这个控件对象可以在java文件findviewbyId中通过id给拿到，在java文件里面变为一个java对象！

```java
//第一种
but.setOnClickListenter(new View.OnClickListener){
    @Override
    //重写onclick方法
    public void onClick(View view){
    Toast.markText(TextViewDemoActivity.this,et.getText().toString.trim(),Toast
}
}
//第二种
testListenter tt=new testListenter();
but.setOnClickListener(tt);

class testListenter implements View.OnClickListener{
    public void onClick(View view){
        Toast.markText(TextViewDemoActivity.this,et.getText().toString

}
}
```

事件监听机制的写法，监听器的对象，
控件对象和监听器对象绑定的方式：
1用匿名对象的写法，2单独写一个类，实现监听器接口的
方法。在绑定之前创建监听器的对象，然后通过set方法把对象和方法绑定在一起。

# 第二章Activity

用户和应用程序交互的接口，是安卓组件中最基本也是最为常见的组件

### 创建activity

file-activity-new empty

在配置文件.xml里面也会自动添加activity标签元素

### 注册activity

oncreate方法，相当于java中的main方法，是入口，刚打开activity的时候就要执行的方法。

```java
super.onCreate(savedInstanceState);
setContentView(R.layout.控件名);
```

strings.xml文件里面定义的，可以在别的文件里@string调用

## view类的常用属性及方法

只有需要与用户进行交互的时候才需要有id。通过java中的事件对象来实现交互功能的时候才需要有id

#### android:onClick

另外一种绑定监听的机制

绑定监听：创建一个类 （匿名对象，匿名内部类），重写监听器里的方法，把类加载到。

android:padding与android:margin的区别

## TextView控件

文本类控件

## 事件监听机制

<img src="file:///C:/Users/27682/AppData/Roaming/marktext/images/2022-09-21-13-21-08-image.png" title="" alt="" width="477">

```java
//        添加事件处理，单击单选按钮“男”时，让篮球和足球可选
rbtMale.addAcitionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
    cbxBasketball.setEnabled(true);
    cbxFootball.setEnable(true);
    cbxDance.setEnabled(false);
    cbxDance.setSelected(false);
    cbxDance.setVisible(false);
}
}
```

```java
public class JTextAreaListenerDemo extends JFrame implements ActionListener{
    //按钮事件
    public void actionPerformed(ActionEvent e){
        if(e.getSource()=bclear){
            text1.setText(null);
            text2.setText(null);
        }
    }
//绑定监听
bclear.addActionListener(this);
}
```

```java
class ProcessEvent implements FocusListener,ActionListener{
    public void focusGained(FoucusEvent e){//当鼠标进入组件时执行该方法
        if(e.getSource()==txtNumber2)
            lblErrorMessage1.setText("焦点到我这了");
}

    public void focusLost(FocusEvent e){
        if(e.getSource()==txtNumber1){
            if(!txtNumber1.getText().trim().equals(("")))
                return;
            if(!isNumber(txtNumber1.getText())){
                lblErrorMessage1.setText("数据格式不对");
                txtNumber1.requestFocus();//让输入焦点获得
            }else
    }
}
```

```java
//下面开始处理事件
ProcessEvent pe=new ProcessEvent();
txtNumber1.addFocusListener(pe);
txtNumber2.addFocusListener(pe);
btnEqual.addActionListener(pe);
```

# 第一次课堂检测

Android的四大组件：

活动（activity），用于表现功能；

服务（service），后台运行服务，不提供界面呈现；

广播接受者（Broadcast Receive），用于接收广播；

内容提供者（Content Provider），支持多个应用中存储和读取数据，相当于数据库。

---

在Android工程中新建了一个Activity 需要在哪个xml文件中声明一下？（AndroidMainifest.xml）

---

在Android应用程序中，布局文件应放在res下哪个目录？

[(34条消息) 7.Android学习之资源访问（二）_ttycr的博客-CSDN博客_android state_checked](https://blog.csdn.net/ttycr/article/details/124064032)

---

如果我们需要捕捉某个控件的事件，我们需要为该控件创建(A.监听器)

---

Android应用程序启动时最先加载的是AndroidManifest.xml文件，如果有多个Activity， 哪个个Activity最先被加载？（android.intent.action.MAIN）

---

一个应用程序默认会包含（1）个Activity。

---

# 第二次课堂检测

ImageView控件用来显示图片

---

Toast创建完毕后，需要显示出来，此时需要调用show方法

---

ProgressBar控件可以用来显示进度

---

用来限制EditText输入类型的是属性：inputType

---

RadioButton为单选按钮，需要配合RadioGroup使用，提供两个或多个互斥的选项集。
CheckBox为多选按钮，不能单独使用。
Button是按钮，用于响应鼠标事件。

---

Android项目中的布局文件放在目录下：res/layout

---

9.关于AlertDialog说法不正确的是

A. setNegativeButton方法的功能是添加取消按钮；
B.setPositiveButton方法的功能是添加确定按钮；
C.对话框的显示需要调用show方法
D.要想使用对话框首先要使用new关键字创建AlertDialog的实例对象

---

10、设置TextView中邮箱内容包含链接方法是（ D ）。
A.android:link="all"
B.android:link="email"
C.android:autolink="web"
D.android:autolink="email"

---

# 遇到的Bug&解决

[(40条消息) AS：Error:(27, 13) Failed to resolve: com.android.support:appcompat-v7:30.+解决办法_Vivian小姐的博客-CSDN博客](https://blog.csdn.net/qq_43819274/article/details/108862348)
