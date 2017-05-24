#动态日志
### 关闭安全校验
### management.security.enabled=false
访问http://localhost:8080/test  
日志打印info error（日志默认是info）
### log信息访问地址 
http://localhost:8080/loggers  
由于默认的日志级别为INFO，所以并没有输出DEBUG级别的内容。
下面我们可以尝试通过/logger端点来将日志级别调整为DEBUG，
比如，发送POST请求到/loggers/com.github.tone端点，其中请求体Body内容为：  
{
    "configuredLevel": "DEBUG"
}  
查看/loggers  级别变成了debug  
再次访问查看日志 打印出了debug info error

 