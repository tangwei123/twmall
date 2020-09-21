## 商城项目

### 目前进度开发中...

### 开发目的：
    熟悉 spring boot + spring cloud+mybatis-plus的开发流程
 
### 开发所涉及的知识点：
    一、基于maven的多模块聚合
    二、基于maven的项目管理
    三、基础的java 8 语法
    四、spring cloud的各个组件（RPC开发模式）
        1、spring cloud alibaba nacos 做为注册中心和配置中心的使用
        2、spring cloud openFeign的调用远程服务
        3、spring cloud gateway做为路由调用nacos的远程服务
    五、高并发开发的3大法宝
        1、缓存技术，redis
            ①、远程redis的使用
                缓存穿透
                缓存雪崩
                缓存击穿
            ②、spring cache的使用
            ③、分布式锁redison的使用
        2、异步技术、线程
            ①、java提供的最基本的线程调用
                Runable、Thread、Callable+FutureTask
            ②、线程池的使用
                new ThreadPoolExecutor()
                Executors.newFixedThreadPool()
            ③、java8提供的CompletableFuthre
                创建：CompletableFuture.RunAsync()
                     CompletableFuture.SupplyAsync()  
                
                组合2个线程：
                    A.thenCombine(B, ()->{}, Executors)
                    
                承接下一个线程
                    A.thenApplyAsync(()->{}, Executors)
                    A.thenAcceptAsync(()->{}, Executors)
                
                等待多个线程执行完毕
                    CompletableFuture.AllOf(A, B, C, D)
                    CompletableFuture.AnyOf(A, B, C, D)
                
                等待线程执行完毕：
                    .get()
                    .join()
                    
                拿到线程执行结果并处理
                    .handler((res, exception)->{}, Executors)
        3、消息队列技术、RabbitMQ
            连接、信道、Virtual Host、Message的Routine Key、binding、Exchange、Queue
            其中 Exchange分为：fanout-Exchange、direct-Exchange、Topic-Exchange
            
            spring boot使用RabbitMQ
                
    六、spring session的使用(分布式session存储到redis上)
        其实是spring-session-data-redis、解决跨域问题！！
        
    七、mybatis-plus的使用，相较于mybatis的优点在哪
    
    八、基于elasticSearch的全文搜索服务
        开发步骤，一般为先设计每个doc的数据类型，然后是做存入数据，搜索数据（聚合数据）
    
    九、spring-cloud-starter-alibaba-seata分布式事务的解决方案
    
    十、spring-session-data-redis session存储到redis的解决方案
    
    十一、spring boot的计划任务的使用@EnableSchduling
    
               
