[TOC]

---

# 虚拟机（JVM）

<div align="center"> <img src="https://upload-images.jianshu.io/upload_images/54256-cea8bcf9e1be3413.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700" width="600"/> </div><br>

- JDK（Java Development Kit）是面向开发人员使用的软件开发包，它提供了Java的开发环境和运行环境；
- JRE（Java Runtime Environment）是指Java的运行环境，是面向使用者的；
- JVM（Java Virtual Machine)是整个java实现跨平台的最核心的部分，所有的java程序会首先被编译为.class的类文件，这种类文件可以在虚拟机上执行，也就是说class并不直接与机器的操作系统相对应，而是经过虚拟机间接与操作系统交互，由虚拟机将程序解释给本地系统执行。

## JVM内存模型

### JVM运行时数据区

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904144714.png" width="400"/> </div><br>


- **程序计数器**

程序计数器是一块较小的内存空间，当前线程所执行的字节码的行号指示器。分支、跳转、循环、异常处理、线程恢复等基础功能都需要依赖程序计数器完成。如果线程正在执行一个Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行Native方法，这个计数器值为空。（没有OutOfMemoryError情况）

- **虚拟机栈**

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904090833.png" width="400"/> </div><br>

虚拟机栈线程私有，生命周期与线程相同。每个方法在执行的同时都会创建一个栈帧用于存储局部变量表，操作数栈，动态链接，方法出口等。从方法调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。
局部变量表存放了编译期可知的各种基本类型、对象引用、returnAddress类型（指向一条字节码指令的地址）。
该区域可能抛出以下异常：
    * 当线程请求的栈深度超过最大值，会抛出 StackOverflowError 异常；
    * 栈进行动态扩展时如果无法申请到足够内存，会抛出 OutOfMemoryError 异常。

- **本地方法栈**

本地方法栈为虚拟机使用到的Native方法服务。

- **Java堆**

Java堆是被所有线程共享的一块内存区域，唯一目的是存放对象实例。Java堆是垃圾收集器管理的主要区域（GC堆）。
堆不需要连续内存，并且可以动态增加其内存，增加失败会抛出 OutOfMemoryError 异常。
现代的垃圾收集器基本都是采用分代收集算法，其主要的思想是针对不同类型的对象采取不同的垃圾回收算法，可以将堆分成两块：
  * 新生代（Young Generation）
  * 老年代（Old Generation）

- **方法区**

用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
和堆一样不需要连续的内存，并且可以动态扩展，动态扩展失败一样会抛出 OutOfMemoryError 异常。
对这块区域进行垃圾回收的主要目标是对常量池的回收和对类的卸载，但是一般比较难实现。

- **运行时常量池**

运行时常量池是方法区的一部分。用于存放编译期生成的各种字面量和符号引用。除了保存Class文件中描述的符号引用外，还会把翻译出来的直接引用存储在运行时常量池中。除了在编译期生成的常量，还允许动态生成，例如 String 类的 intern()。

### 对象的访问定位

目前主流的访问方式有使用句柄和直接指针两种。

**句柄访问：**Java堆中将会划分一块内存来作为句柄池，reference中存储的就是对象的句柄地址，而句柄中包含了对象实例数据与类型数据各自的具体地址信息。
<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904100852.png" width="400"/> </div><br>

**直接指针访问：**java堆对象分布中就必须考虑如何放置访问类型数据的相关信息，reference存储的直接就时对象地址。
<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904100911.png" width="400"/> </div><br>

这两种对象的访问方式各有优势，使用句柄访问最大的好处就是reference中存储的是稳定的句柄地址，在对象被移动时，只用修改句柄中的实例数据指针，而reference本身不需要修改！
直接访问对象方式的好处就是，减少一次指针定位的时间开销，由于对象的访问是非常频繁的，因此这类开销积少成多也是一项非常的执行成本。

### 引用类型

无论是通过引用计算算法判断对象的引用数量，还是通过可达性分析算法判断对象是否可达，判定对象是否可被回收都与引用有关。

Java 提供了四种强度不同的引用类型。

#### 强引用

被强引用关联的对象不会被回收。

使用 new 一个新对象的方式来创建强引用。

```java
Object obj = new Object();
```

#### 软引用

被软引用关联的对象只有在内存不够的情况下才会被回收。

使用 SoftReference 类来创建软引用。

```source-java
Object obj = new Object();
SoftReference<Object> sf = new SoftReference<Object>(obj);
obj = null;  // 使对象只被软引用关联
```

#### 弱引用

被弱引用关联的对象一定会被回收，也就是说它只能存活到下一次垃圾回收发生之前。

使用 WeakReference 类来实现弱引用。

```source-java
Object obj = new Object();
WeakReference<Object> wf = new WeakReference<Object>(obj);
obj = null;
```

#### 虚引用

又称为幽灵引用或者幻影引用。一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用取得一个对象。

为一个对象设置虚引用关联的唯一目的就是能在这个对象被回收时收到一个系统通知。

使用 PhantomReference 来实现虚引用。

```source-java
Object obj = new Object();
PhantomReference<Object> pf = new PhantomReference<Object>(obj);
obj = null;
```



## 垃圾收集

### 对象可回收判断

#### 引用计数算法
给对象添加一个引用计数器，当对象增加一个引用时计数器加 1，引用失效时计数器减 1。引用计数为 0 的对象可被回收。

两个对象出现循环引用的情况下，此时引用计数器永远不为 0，导致无法对它们进行回收。

#### 可达性分析算法
通过“GC Roots”的对象作为起始点，从这些节点开始向下搜索，搜索走过的路径称为引用链，当一个对象到GC Roots没有任何引用链相连时，此对象不可用。

- 虚拟机栈中局部变量表中引用的对象
- 本地方法栈中 JNI 中引用的对象
- 方法区中类静态属性引用的对象
- 方法区中的常量引用的对象

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904102146.png" width="400"/> </div><br>

#### 方法区的回收

因为方法区主要存放永久代对象，而永久代对象的回收率比新生代低很多，因此在方法区上进行回收性价比不高。

主要是对常量池的回收和对类的卸载。

在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载功能，以保证不会出现内存溢出。

类的卸载条件很多，需要满足以下三个条件，并且满足了也不一定会被卸载：

- 该类所有的实例都已经被回收，也就是堆中不存在该类的任何实例。
- 加载该类的 ClassLoader 已经被回收。
- 该类对应的 Class 对象没有在任何地方被引用，也就无法在任何地方通过反射访问该类方法。

#### finalize()

当一个对象可被回收时，如果需要执行该对象的 finalize() 方法，那么就有可能在该方法中让对象重新被引用，从而实现自救。自救只能进行一次，如果回收的对象之前调用了 finalize() 方法自救，后面回收时不会调用 finalize() 方法。

### 垃圾收集算法

#### 标记 - 清除

首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象
不足：
- 标记和清除过程效率都不高；
- 会产生大量不连续的内存碎片，导致无法给大对象分配内存

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904103535.png" width="400"/> </div><br>

#### 复制算法

将内存划分为大小相等的两块，每次只使用其中一块，当这一块内存用完了就将还存活的对象复制到另一块上面，然后再把使用过的内存空间进行一次清理。

主要不足是只使用了内存的一半。


<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904103813.png" width="400"/> </div><br>

#### 标记 - 整理

标记过程仍然和“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活对象都向一端移动，然后直接清理掉端边界以外的内存。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904104143.png" width="400"/> </div><br>

### 垃圾收集器

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904104314.png" width="400"/> </div><br>

**安全点**，即程序执行时并非在所有地方能停顿下来开始GC，只有到达安全点时才能暂停。
**安全区域**，是指一段代码片段之中，引用关系不会发生变化。在这个区域中的任意地方开始GC都是安全的。

#### Serial 收集器

它以**串行**的方式执行。它是**单线程**的收集器，只会使用一个线程进行垃圾收集工作。它的优点是**简单高效**，对于单个 CPU 环境来说，由于没有线程交互的开销，因此拥有最高的单线程收集效率。

它是 Client 模式下的默认新生代收集器，因为在该应用场景下，分配给虚拟机管理的内存一般来说不会很大。Serial 收集器收集几十兆甚至一两百兆的新生代停顿时间可以控制在一百多毫秒以内，只要不是太频繁，这点停顿是可以接受的。
<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904105224.png" width="400"/> </div><br>

#### ParNew 收集器

它是 Serial 收集器的多线程版本。是 Server 模式下的虚拟机首选新生代收集器，除了性能原因外，主要是因为除了 Serial 收集器，只有它能与 CMS 收集器配合工作。
默认开启的线程数量与 CPU 数量相同，可以使用 -XX:ParallelGCThreads 参数来设置线程数。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904105552.png" width="400"/> </div><br>

#### Parallel Scavenge 收集器

与 ParNew 一样是多线程收集器。

其它收集器关注点是尽可能缩短垃圾收集时用户线程的停顿时间，而它的目标是达到一个可控制的吞吐量，它被称为“吞吐量优先”收集器。这里的吞吐量指 CPU 用于运行用户代码的时间占总时间的比值。

停顿时间越短就越适合需要与用户交互的程序，良好的响应速度能提升用户体验。而高吞吐量则可以高效率地利用 CPU 时间，尽快完成程序的运算任务，适合在后台运算而不需要太多交互的任务。

缩短停顿时间是以牺牲吞吐量和新生代空间来换取的：新生代空间变小，垃圾回收变得频繁，导致吞吐量下降。

可以通过一个开关参数打开 GC 自适应的调节策略（GC Ergonomics），就不需要手工指定新生代的大小（-Xmn）、Eden 和 Survivor 区的比例、晋升老年代对象年龄等细节参数了。虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或者最大的吞吐量。

#### Serial Old 收集器

是 Serial 收集器的老年代版本，也是给 Client 模式下的虚拟机使用。如果用在 Server 模式下，它有两大用途：

- 在 JDK 1.5 以及之前版本（Parallel Old 诞生以前）中与 Parallel Scavenge 收集器搭配使用。
- 作为 CMS 收集器的后备预案，在并发收集发生 Concurrent Mode Failure 时使用。

#### Parallel Old 收集器

是 Parallel Scavenge 收集器的老年代版本。

在注重吞吐量以及 CPU 资源敏感的场合，都可以优先考虑 Parallel Scavenge 加 Parallel Old 收集器。


<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904110354.png" width="400"/> </div><br>

#### CMS 收集器

CMS（Concurrent Mark Sweep），Mark Sweep 指的是标记 - 清除算法。

分为以下四个流程：

*   初始标记：仅仅只是标记一下 GC Roots 能直接关联到的对象，速度很快，需要停顿。
*   并发标记：进行 GC Roots Tracing 的过程，它在整个回收过程中耗时最长，不需要停顿。
*   重新标记：为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，需要停顿。
*   并发清除：不需要停顿。

在整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，不需要进行停顿。

具有以下缺点：

*   吞吐量低：低停顿时间是以牺牲吞吐量为代价的，导致 CPU 利用率不够高。
*   无法处理浮动垃圾，可能出现 Concurrent Mode Failure。浮动垃圾是指并发清除阶段由于用户线程继续运行而产生的垃圾，这部分垃圾只能到下一次 GC 时才能进行回收。由于浮动垃圾的存在，因此需要预留出一部分内存，意味着 CMS 收集不能像其它收集器那样等待老年代快满的时候再回收。如果预留的内存不够存放浮动垃圾，就会出现 Concurrent Mode Failure，这时虚拟机将临时启用 Serial Old 来替代 CMS。
*   标记 - 清除算法导致的空间碎片，往往出现老年代空间剩余，但无法找到足够大连续空间来分配当前对象，不得不提前触发一次 Full GC。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904110413.png" width="400"/> </div><br>

#### G1 收集器

G1收集器是一款面向服务端的垃圾收集器。G1跟踪各个Region里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的Region。

使用G1收集器时，Java堆的内存布局与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的**独立区域（Region）**，虽然还保留新生代和老年代的概念。但新生代和老年代不再物理间隔。

在G1中，还有一种特殊的区域，叫Humongous区域。它专门用来存放巨型对象。如果一个H区装不下一个巨型对象，那么G1会寻找连续的H分区来存储。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180903190516.png" width="400"/> </div><br>


- **对象之间的引用**

G1中每个Region都有一个与之对应的Remembered Set,虚拟机发现程序在对Reference类型的数据进行写操作时，会产生一个Write Barrier暂时中断写操作，检查Reference引用的对象是否处于不同的Region之中，如果是，便通过CardTable把相关引用信息记录到被引用对象所属的Region的Remembered Set之中。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180903192613.png" width="400"/> </div><br>

- **运作步骤**
 1. 初始标记：标记GC Root能直接关联的对象，并修改TAMS
 2. 并发标记：可达性分析，找出存活对象，但可与用户程序并发执行。
 3. 最终标记：修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，并行执行。
 4. 筛选回收：首先对各个Region的回收价值和成本进行排序，根据用户所期望的GC停顿时间来制定回收计划。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904110706.png" width="400"/> </div><br>

- **G1收集器的特点**
 - 并行与并发：G1能充分利用多CPU,多核的硬件优势来缩短Stop—The—World停顿的时间，部分其它收集器原本需要停顿用户线程执行的GC动作， G1依然可以通过并发的方式让用户线程继续执行。
 - 分代收集：同其它收集器一样，分代概念依然在G1中保留。G1可以不需要其它收集器配合就能独立管理整个GC堆，而且采用了不同的方式处理新创建的对象，已经存活一段时间的对象，熬过多次GC的旧对象，来获得更好的收集结果。
 - 空间整合：使用算法从整体上来看是基于标记——整理实现的。局部来看，是基于“复制”算法实现的。所以G1在运作期间不会产生内存空间碎片， 收集后能提供规整的可用内存。这有利于程序长时间运行。
 - 可预测的停顿：G1同CMS都追求低停顿时间。但是G1还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，消耗在垃圾收集的时间上不超过N毫秒，这几乎是实时Java的垃圾收集器的特征。

## 类加载机制
>类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：**加载（Loading）**、**验证（Verification）**、**准备(Preparation)**、**解析(Resolution)**、**初始化(Initialization)**、**使用(Using)**和**卸载(Unloading)**7个阶段。其中准备、验证、解析3个部分统称为连接（Linking）。
加载、验证、准备、初始化和卸载这5个阶段的顺序是确定的，类的加载过程必须按照这种顺序按部就班地开始，而解析阶段则不一定：它在某些情况下可以在初始化阶段之后再开始，这是为了支持Java语言的运行时绑定（也称为**动态绑定或晚期绑定**）。

<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904145923.png" width="400"/> </div><br>

### 类的加载过程

#### 加载
在加载阶段（可以参考java.lang.ClassLoader的loadClass()方法），虚拟机需要完成以下3件事情：

- 通过一个类的全限定名来获取定义此类的二进制字节流；
- 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构；
- 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口；

其中二进制字节流可以从以下方式中获取：

*   从 ZIP 包读取，成为 JAR、EAR、WAR 格式的基础。
*   从网络中获取，最典型的应用是 Applet。
*   运行时计算生成，例如动态代理技术，在 java.lang.reflect.Proxy 使用 ProxyGenerator.generateProxyClass 的代理类的二进制字节流。
*   由其他文件生成，例如由 JSP 文件生成对应的 Class 类。
*   从数据库中获取。

加载阶段和连接阶段（Linking）的部分内容（如一部分字节码文件格式验证动作）是交叉进行的，加载阶段尚未完成，连接阶段可能已经开始

#### 验证

验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。

- 文件格式验证：
验证字节流是否符合Class文件格式的规范；例如：是否以魔术0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、常量池中的常量是否有不被支持的类型。
- 元数据验证：
对字节码描述的信息进行语义分析（注意：对比javac编译阶段的语义分析），以保证其描述的信息符合Java语言规范的要求
- 字节码验证：
通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
- 符号引用验证：
符号引用验证可以看做是对类自身以外的信息进行匹配性校验。

验证阶段是非常重要的，但不是必须的，它对程序运行期没有影响，如果所引用的类经过反复验证，那么可以考虑采用-Xverifynone参数来关闭大部分的类验证措施，以缩短虚拟机类加载的时间。

#### 准备

准备阶段是正式为**类变量分配内存**并设置**类变量初始值**的阶段，这些变量所使用的内存都将在**方法区**中进行分配。这时候进行内存分配的仅包括类变量（被static修饰的变量），而不包括实例变量，实例变量将会在对象实例化时随着对象一起分配在堆中。其次，这里所说的初始值“通常情况”下是数据类型的零值，假设一个类变量的定义为：
```java
public static int value=123;
```

那变量value在准备阶段过后的初始值为0而不是123.因为这时候尚未开始执行任何java方法，而把value赋值为123的putstatic指令是程序被编译后，存放于类构造器()方法之中，所以把value赋值为123的动作将在初始化阶段才会执行。

至于“特殊情况”是指：public static final int value=123，即当类字段的字段属性是ConstantValue时，会在准备阶段初始化为指定的值，所以标注为final之后，value的值在准备阶段初始化为123而非0.

#### 解析
解析阶段是虚拟机将常量池内的符号引用替换为**直接引用**的过程。解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符7类符号引用进行。


#### 初始化
类初始化阶段是类加载过程的最后一步，到了初始化阶段，才真正开始执行类中定义的java程序代码。

### 类初始化时机

#### 主动引用

虚拟机规范中并没有强制约束何时进行加载，但是规范严格规定了有且只有下列五种情况必须对类进行初始化（加载、验证、准备都会随之发生）：

*   遇到 new、getstatic、putstatic、invokestatic 这四条字节码指令时，如果类没有进行过初始化，则必须先触发其初始化。最常见的生成这 4 条指令的场景是：使用 new 关键字实例化对象的时候；读取或设置一个类的静态字段（被 final 修饰、已在编译期把结果放入常量池的静态字段除外）的时候；以及调用一个类的静态方法的时候。
*   使用 java.lang.reflect 包的方法对类进行反射调用的时候，如果类没有进行初始化，则需要先触发其初始化。
*   当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
*   当虚拟机启动时，用户需要指定一个要执行的主类（包含 main() 方法的那个类），虚拟机会先初始化这个主类；
*   当使用 JDK 1.7 的动态语言支持时，如果一个 java.lang.invoke.MethodHandle 实例最后的解析结果为 REF_getStatic, REF_putStatic, REF_invokeStatic 的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化；

#### 被动引用

以上 5 种场景中的行为称为对一个类进行主动引用。除此之外，所有引用类的方式都不会触发初始化，称为被动引用。被动引用的常见例子包括：

*   通过子类引用父类的静态字段，不会导致子类初始化。

```source-java
System.out.println(SubClass.value);  // value 字段在 SuperClass 中定义
```

*   通过数组定义来引用类，不会触发此类的初始化。该过程会对数组类进行初始化，数组类是一个由虚拟机自动生成的、直接继承自 Object 的子类，其中包含了数组的属性和方法。

```source-java
SuperClass[] sca = new SuperClass[10];
```

*   常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。

```source-java
System.out.println(ConstClass.HELLOWORLD);
```

### 类加载器
>两个类相等需要类本身相等，并且使用同一个类加载器进行加载。这是因为每一个类加载器都拥有一个独立的类名称空间。
这里的相等，包括类的 Class 对象的 equals() 方法、isAssignableFrom() 方法、isInstance() 方法的返回结果为 true，也包括使用 instanceof 关键字做对象所属关系判定结果为 true。

#### 类加载器分类

从 Java 虚拟机的角度来讲，只存在以下两种不同的类加载器：
*   启动类加载器（Bootstrap ClassLoader），这个类加载器用 C++ 实现，是虚拟机自身的一部分；
*   所有其他类的加载器，这些类由 Java 实现，独立于虚拟机外部，并且全都继承自抽象类 java.lang.ClassLoader。

从 Java 开发人员的角度看，类加载器可以划分得更细致一些：

- **启动类加载器（Bootstrap ClassLoader）**此类加载器负责将存放在 <JRE_HOME>\lib 目录中的，或者被 -Xbootclasspath 参数所指定的路径中的，并且是虚拟机识别的（仅按照文件名识别，如 rt.jar，名字不符合的类库即使放在 lib 目录中也不会被加载）类库加载到虚拟机内存中。启动类加载器无法被 Java 程序直接引用，用户在编写自定义类加载器时，如果需要把加载请求委派给启动类加载器，直接使用 null 代替即可。
- **扩展类加载器（Extension ClassLoader）**这个类加载器是由 ExtClassLoader （sun.misc.Launcher$ExtClassLoader）实现的。它负责将 <JAVA_HOME>/lib/ext 或者被 java.ext.dir 系统变量所指定路径中的所有类库加载到内存中，开发者可以直接使用扩展类加载器。
- **应用程序类加载器（Application ClassLoader）**这个类加载器是由 AppClassLoader（sun.misc.Launcher$AppClassLoader）实现的。由于这个类加载器是 ClassLoader 中的 getSystemClassLoader() 方法的返回值，因此一般称为系统类加载器。它负责加载用户类路径（ClassPath）上所指定的类库，开发者可以直接使用这个类加载器，如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。

#### 双亲委派模型
该模型要求除了顶层的启动类加载器外，其余的类加载器都应有自己的父类加载器。这里类加载器之间的父子关系一般通过组合（Composition）关系来实现，而不是通过继承（Inheritance）的关系实现。


<div align="center"> <img src="https://github.com/LyricYang/Internet-Recruiting-Algorithm-Problems/tree/master/JAVA/IMG/20180904150027.png" width="400"/> </div><br>

若一个类加载器收到类加载请求，他首先不会自己尝试去加载这个类，而是把这个请求委派给父类加载器去完成，每一层类加载器都是如此，所以所有请求都会传送到顶层的启动类加载器中，只有当父类加载器反馈自己无法完成这个加载请求(它的搜索范围没找到)，子加载器才会自己尝试去加载。

**使用双亲委派模型好处：**

java类以及它的类加载器一起具备了一种带有优先级的层次关系。从而使得基础类得到统一。

如果java.lang.Object类被不同的类加载器加载很多次，jvm中存在了多个不同的Object类，那么java类型体系中最基础的行为也无从保证，应用程序会一片混乱。

相反，使用了双亲委派模型，无论哪个类加载器去加载，都会委派到启动类加载器进行加载，保证了Object在各个类加载器环境中，都是同一个类。


## JVM调优

[VisualVM](http://www.cr173.com/soft/186392.html)

**Tomcat调优**
```
set JAVA_OPTS=
 -Xms4g
 -Xmx4g
 -Xss512k
 -XX:+AggressiveOpts //所有调优都用
 -XX:+UseBiasedLocking
 -XX:PermSize=64M //取消了
 -XX:MaxPermSize=300M
 -XX:+DisableExplicitG C //无法显示调用GC
```

**JVM参数**

- 堆设置
 - **-Xms**:starting 堆初始大小
 - **-Xmx**:max 堆最大容量
 - **-Xmn**:new 新生代大小
 - **-Xss**:线程栈大小
 - **-XX:SurvivorRation=8**：Eden区与两个Survivor区的比值
 - **-XX:MaxPermSize=n**:设置持久代大小
- 收集器设置
 - **-XX:+UseSerialGC**:设置串行收集器
 - **-XX:+UseParallelGC**:设置并行收集器
 - **-XX:+UseConcMarkSweepGC**:设置并发收集器
- 前缀说明
 - **-**：标准参数，所有jvm都支持
 - **-X**:非标，每个jvm实现都不同
 - **-XX**：不稳定参数，下一个版本可能会取消
 - **-XX：-DoEscapeAnalysis**：不做逃逸分析
 - **-XX：-EliminateAllocation**：不做标量替换
 - **-XX：-UseTLAB**:不是用本地分配
- GC信息
 - **-XX：-PrintGC**：打印GC
 - **-XX：-PrintGCDetail**：打印GC详细信息
 - **-Xloggc:filename**:将信息输出到指定文件

## 内存泄漏

**什么是内存泄漏？**
- 当一个对象已经不需要使用，本该被回收时，另一个正在使用的对象持有对它的引用从而导致它不能被回收，这导致本该被回收的对象不能被回收而停留在堆内存中，这就产生了内存泄漏。

**内存泄漏的影响？**
- 内存泄漏是造成应用程序OOM（Out Of Memory）的主要原因之一。比如说Android系统为每个应用程序分配的内存是有限的，而当一个应用内存泄漏比较多时，这就难免会导致应用所需要的内存超过系统分配的内存限额，这就造成内存溢出从而导致应用崩溃。

**内存泄漏和内存溢出的区别？**
- 内存溢出是由于应用所消耗的内存或者应用申请的内存超出了虚拟机分配的内存，也就是内存不够用了。内存泄漏是某个不再使用对象由于被其他实例引用，导致不能被GC回收，而导致的内存不能释放。内存泄漏可能会引起内存溢出。

**如何检查和分析内存泄漏？**
- 因为内存泄漏是在堆内存中，所以对我们来说是不可见的，通常我们可以借助MAT（Memory Analysis Tool）、leakCanary等工具来检测应用程序是否存在内存泄漏。

**常见的内存泄漏及解决方法**

1. 单例造成的内存泄漏
由于单例的静态特性使得其生命周期和应用的生命周期一样长，如果一个对象已经不再需要使用了，而单例对象还持有该对象的应用，就会使得该对象不能被正常回收，从而导致了内存泄漏。
2. 非静态内部类创建静态实例造成的内存泄漏
非静态内部类默认会持有外部类的引用，而该非静态内部类又创建了一个静态的实例，该实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，从而导致Activity的内存资源不能被正常回收。
**解决方法：**将该内部类设为静态内部类或将该内部类抽取出来封装成一个单例，如果需要使用Context，就使用Application的Context。
3. 匿名内部类造成内存泄漏
4. 线程造成内存泄漏
将AsyncTask和Runnable类独立出来或者使用静态内部类，这样便可以避免内存泄漏。
5. 资源未关闭造成内存泄漏
6. 容器造成的内存泄漏
把一些对象的引用加入到了集合容器（比如ArrayList）中，当我们不需要该对象时，并没有把它的引用从集合中清理掉，这样这个集合就会越来越大。如果这个集合是static的话，那情况就更严重了。
**解决方法**：在退出程序之前，将集合里的东西clear，然后置为null，再退出程序。

**如何避免内存泄漏？**
- 对于需要在静态内部类中使用非静态外部成员变量（如：Context、View )，可以在静态内部类中使用弱引用来引用外部类的变量来避免内存泄漏。
- 对于不再需要使用的对象，显示的将其赋值为null，比如使用完Bitmap后先调用recycle()，再赋为null。
- 保持对对象生命周期的敏感，特别注意单例、静态对象、全局性集合等的生命周期。
- 对于生命周期比Activity长的内部类对象，并且内部类中使用了外部类的成员变量，可以这样做避免内存泄漏：
 - 将内部类改为静态内部类
 - 静态内部类中使用弱引用来引用外部类的成员变量



