Generally think of the following ways:
0. Obfuscator, the decompiled after obfuscating the jar package looks very dazzling, but if you are patient, you can see it.
1. Encrypt the jar package, and then rewrite the class loader in the Java layer to decrypt it to achieve the encryption protection of the jar package. Including the use of symmetric encryption algorithms and asymmetric encryption algorithms. No matter what algorithm is used, the implementation of the class loader at the Java level is actually not very useful, because the class loader itself is basically exposed after being decompiled.
2. You can modify some attributes of the class file compiled by java so that the decompilation software cannot analyze it, but it is not reliable. It can be decompiled as long as it is analyzed in-depth according to the class format.
3. Modify the JDK source code. Customizing the JDK involves the overall change of the JVM, and it also requires external use, which is not feasible.
4. Use some similar hook mechanisms and event monitoring mechanisms of the JVM in the JDK to monitor loading class events, and use local methods to complete class decryption. After C/C++ is compiled, it is very troublesome to decompile, and it can also pack. Here is a look at this way.

Here is the fifth method, which currently implements encryption protection for ordinary java projects and SpringBoot projects. Traditional Tomcat welcomes everyone to provide methods to discuss how to implement it.

Encryption steps:
This piece involves the knowledge of Java class loader. I will not go into details here. You can go to Baidu and Google to search for the principle of java class loader to learn.
0. The dynamic link library is hard to write (org.kakahu.safe), so the class you want to encrypt must be placed in the org.kakahu.safe package, otherwise it cannot be encrypted. If you need to customize the encryption of classes in other packages, please Send an email to kakahu2021@gmail.com to contact the author

1. Since SpringBoot comes with a class loader that will load all the class files in the BOOT-INFO directory, it is necessary to process the typed jar package. You can refer to the maven configuration in the project or manually move the relevant files to ensure:
lib, config (application.yaml inside) and jar package are in the same directory (of course, you can also choose to modify the SpringBoot class loader without this processing)

2. Enter the encrypt directory, edit jarConfig.properties (the file has comments, just refer to the comments to replace your actual content), and then run java -jar encrypt.jar to complete the jar package encryption

3. If you directly encrypt the .jar with java -jar, an error will be reported, because a class file is encrypted. The correct operation method is:
java -agentpath:/xxx/libus.so (dynamic link library path) -cp xxx.jar (encrypted jar) Full name of the startup class (eg rg.kakahu.test.DemoApplication)

4. The project in the testDemo directory is a compiled and packaged project, you can use this as an encrypted jar to practice


Thank you for reading, and welcome to correct me!