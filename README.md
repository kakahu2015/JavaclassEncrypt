大致想到以下几种方式： 
1. 混淆器，将jar包混淆后反编译出来的东西看起来就很眼花，但如果耐心一点也是可以看出来的。 
2. 对jar包进行加密，然后在Java层重写类加载器对其进行解密，以达到对jar包的加密保护。包括用对称加密算法和非对称加密算法。不管用什么算法，在Java层面的类加载器实现的话，其实也作用不大，因为类加载器本身被反编译出来后就基本暴露无遗了。 
3. 可以修改java编译后的class文件的某些属性，以让反编译软件分析不了，但它也不可靠，只要按照class格式深入分析下也能反编译出来。 
4. 修改JDK源码，定制JDK就涉及到JVM的整体改动，而且还要求外部使用，不太可行。 
5. 利用JDK中JVM的某些类似钩子机制和事件监听机制，监听加载class事件，使用本地方式完成class的解密。C/C++被编译后想要反编译就很麻烦了，另外还能加壳。这里就看看这种方式。

这里就是以第种5方式，目前实现了普通java项目、SpringBoot项目的加密保护，传统的Tomcat欢迎大家提供方法来讨论下如何实现。

加密步骤:
这块涉及Java类加载器的知识，这里不详细说了，大家可以去百度、谷歌搜索java类加载器原理学习。
0、动态链接库写死了（org.kakahu.safe），所以您要加密的类务必放在org.kakahu.safe包中，否则无法被加密到，如需定制化加密其他包下的class请发送邮件到kakahu2021@gmail.com联系作者

1、SpringBoot由于自带类加载器会加载BOOT-INFO目录下的全部class文件，所以要对打出的jar包进行处理，可参照项目中的maven配置或者手工移动相关文件确保：
lib、config(里面为application.yaml)与jar包在同一目录(当然你也可以选择修改SpringBoot的类加载器，而不进行这个处理）

2、进入encrypt目录，编辑jarConfig.properties（该文件有注释，参照注释替换你实际的内容即可），然后运行java -jar encrypt.jar,完成jar包加密

3、如果你直接java -jar  加密后的.jar 一定会报错，因为有个class文件加密了，正确的运行方式为：
java -agentpath:/xxx/libus.so(动态链接库路径) -cp xxx.jar（加密后的jar） 启动类全名（如rg.kakahu.test.DemoApplication)

4、testDemo目录下的工程为编译打包好的工程，您可以用这个作为加密jar来实践


谢谢阅读，欢迎指正！