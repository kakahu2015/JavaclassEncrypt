一般而言java開發中字節碼加密有以下幾種方式： 
====

0. 混淆器，將jar包混淆後反編譯出來的東西看起來就很眼花，但如果耐心一點也是可以看出來的。 
1. 對jar包進行加密，然後在Java層重寫類加載器對其進行解密，以達到對jar包的加密保護。包括用對稱加密算法和非對稱加密算法。不管用什麽算法，在Java層面的類加載器實現的話，其實也作用不大，因為類加載器本身被反編譯出來後就基本暴露無遺了。 
2. 可以修改java編譯後的class文件的某些屬性，以讓反編譯軟件分析不了，但它也不可靠，只要按照class格式深入分析下也能反編譯出來。 
3. 修改JDK源碼，定製JDK就涉及到JVM的整體改動，而且還要求外部使用，不太可行。 
4. 利用JDK中JVM的某些類似鉤子機製和事件監聽機製，監聽加載class事件，使用本地方式完成class的解密。C/C++被編譯後想要反編譯就很麻煩了，另外還能加殼。這裏就看看這種方式。

這裏采用第5種方式，目前實現了普通java項目、SpringBoot項目的加密保護。由於目前Tomcat的war包部署方式已經很少用，所以傳統的Tomcat加密方式尚未實現,
歡迎有興趣的開發者一起探討。


運行環境Linux x86-64、JDK 1.8
====


0、動態鏈接庫只加加密、解密指定（org.kakahu.safe）包下面的class文件，所以您要加密的類務必放在org.kakahu.safe包中，否則無法被加密到。

1、SpringBoot由於自帶類加載器會加載BOOT-INFO目錄下的全部class文件，所以要對打出的jar包進行處理，可參照項目中的maven配置或者手工移動相關文件確保：
lib、config(裏面為application.yaml)與jar包在同一目錄(另外一種思路是修改SpringBoot的類加載器，而不進行這個操作）。

2、進入encrypt目錄，編輯jarConfig.properties，然後運行java -jar encrypt.jar,完成jar包加密。

3、如果你直接java -jar  xxx.jar(加密的jar) 一定會報錯，因為有個class文件加密了，正確的運行方式為：
java -agentpath:/xxx/libus.so(動態鏈接庫路徑) -cp xxx.jar（加密後的jar） 啟動類全名（如org.kakahu.test.DemoApplication)。

4、如果您想快速體驗，可以進入testDemo目錄下的工程為編譯打包好的工程，您可以用這個作為加密jar來實踐

Docker支持
====
詳見docker目錄的Dockerfile,務必把config、lib兩個目錄一並添加到鏡像內,然後運行docker build -t kakahu2015/jarencrypt:v0.1.0 .(這是個例子，具體版本號請自行替換)構建鏡像


這種加密可用於保護核心java字節碼不被反編譯、以及商業license技術實現方案等，商業合作郵箱:kakahu@kakahu.org
-------
