# SPRING BOOT 基础开发平台

# 项目介绍：

######   基于spring boot 2.x 搭建基础版 api服务框架
  
# 技术架构：

######   开发环境：
    
    ·语言 Java8
    ·IDE(JAVA)： IDEA / Eclipse安装lombok插件
    ·依赖管理：Maven
    ·数据库：SQLite & MySQL5.7+ / Oracle 11g / SqlServer2017 
    ·缓存：Redis
    ·消息队列：RabbitMQ
    
######   API服务端：
    
    ·基础框架：Spring Boot 2.1.8.RELEASE
    ·持久层框架：Mybatis-plus 3.2.0
    ·安全框架：Apache Shiro 1.4.0，Jwt 0.7.0
    ·数据库连接池：Dynamic 2.4.2
    ·Redis连接池：Jedis 2.9.0
    ·日志打印：logback
    ·其他：fastjson，poi，swagger-bootstrap-ui，quartz, lombok（简化代码）等。

# 功能支持：

######   1、消息队列
  
    ·RabbitMQ监听工厂配置MqListenerFactoryConfig
    ·MqTemplateReceiver 提供 consumer 监听消费实例
    ·framework下框架针对MQ提供记录机制，成功失败均可记入SQLite库
              
######   2、Redis缓存
  
    ·通过Jedis连接池操作Redis缓存数据
    ·JedisUtils工具类提供基础操作方法
    ·DemoController列举调用实例
             
######   3、SQLite数据库
    
    自给自足的、无服务器的、零配置的、事务性的 SQL 数据库引擎
    本系统中SQLite用于对MQ等服务提供留痕记录操作
  
######   4、Swagger
    
    ·swagger-bootstrap-ui:提供swagger的可视化界面
    ·swagger2:收集管理所有API接口信息 URL+入参+返回值
    
######   5、MessageSource/i18n 消息国际化
  
    ·基于MessageSource支持消息国际化翻译
    ·MessageUtils：提供了翻译方法
    ·框架基于ExceptionHandler + RestControllerAdvice实现消息自动翻译机制

# 项目下载和运行:

######   ·拉取项目代码：http://192.168.1.250:6060/RT/base-api-server.git
    
    
