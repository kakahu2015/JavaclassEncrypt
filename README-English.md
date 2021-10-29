Generally speaking, there are several ways to encrypt bytecode in java development:

0. Obfuscator, what is decompiled after obfuscating the jar package looks very dazzling, but you can see it if you have a little patience.
1. Encrypt the jar package, and then rewrite the class loader in the Java layer to decrypt it to achieve the encryption protection of the jar package. Including the use of symmetric encryption algorithms and asymmetric encryption algorithms. No matter what algorithm is used, the implementation of the class loader at the Java level is actually not very useful, because the class loader itself is basically exposed after being decompiled.
2. You can modify some attributes of the class file compiled by java so that the decompiler cannot analyze it, but it is not reliable. It can be decompiled as long as it is analyzed in-depth according to the class format.
3. Modify the JDK source code. Customizing the JDK involves the overall change of the JVM, and it also requires external use, which is not feasible.
4. Use some similar hook mechanism and event monitoring mechanism of JVM in JDK to monitor loading class events, and use local methods to complete class decryption. After C/C++ is compiled, it is very troublesome to decompile, and it can also pack. Here is a look at this way.

The fifth method is used here, which currently implements encryption protection for common java projects and SpringBoot projects. Since Tomcat's war package deployment method is rarely used at present, the traditional Tomcat encryption method has not been implemented yet.
Interested developers are welcome to discuss together.

Encryption steps:

Operating environment Linux x86-64, JDK 1.8.

0. The dynamic link library only encrypts and decrypts the class files under the specified (org.kakahu.safe) package, so the class you want to encrypt must be placed in the org.kakahu.safe package, otherwise it cannot be encrypted.

1. Since SpringBoot comes with a class loader that will load all the class files in the BOOT-INFO directory, it is necessary to process the typed jar package. You can refer to the maven configuration in the project or manually move the relevant files to ensure:
The lib, config (application.yaml inside) and the jar package are in the same directory (another idea is to modify the SpringBoot class loader without this operation).

2. Enter the encrypt directory, edit jarConfig.properties, and then run java -jar encrypt.jar to complete the jar package encryption.

3. If you directly java -jar xxx.jar (encrypted jar), you will definitely get an error, because a class file is encrypted. The correct operation method is:
java -agentpath:/xxx/libus.so (dynamic link library path) -cp xxx.jar (encrypted jar) The full name of the startup class (such as org.kakahu.test.DemoApplication).

4. If you want a quick experience, you can enter the project under the testDemo directory as a compiled and packaged project, you can use this as an encrypted jar to practice

This kind of encryption can be used to protect the core java bytecode from being decompiled, as well as commercial license technology implementation solutions, etc. Commercial cooperation email: kakahu@kakahu.org