#### 服务断路器 Sentinel，[官方文档](https://github.com/alibaba/Sentinel/wiki)
#### 搭建 Sentinel 控制台
```bash
# 下载控制台安装包
$ wget -P /home/sentinel https://github.com/alibaba/Sentinel/releases/download/1.6.3/sentinel-dashboard-1.6.3.jar

# 启动控制台
$ java -jar /home/sentinel/sentinel-dashboard-1.6.3.jar \
       --server.port=8080                               \
       --auth.username=sentinel                         \
       --auth.password=sentinel                         \
       --logging.file=/home/sentinel/sentinel-dashboard.log
```
#### 流控模式说明
 - 直接：直接限制当前资源
 - 关联：关联资源到达限流阈值后限制当前资源
 - 链路：从某个链路进来的流量达到限流阈值后限制当前资源
 
#### 流控效果说明
 - Warm Up：阈值 / codeFactor（预热因子默认是3） == 最初阈值，经过预热时间后才真正到达阈值（适用于秒杀，就是瞬间流量暴增场景）
 - 排队等待：到达阈值后请求进入等待状态（注意：排队等待的流控效果一定要使用QPS阈值类型，否则无法生效）
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/rule-add.png)

#### 降级规则说明
 - RT：窗口时间内请求数超过5次且平均响应时间大于RT时间，触发降级，窗口时间结束后关闭降级（注意：RT时间最大4900毫秒，设置再大也是4900，强制设置最大值的方法（JVM参数）：-Dcsp.sentinel.statistic.max.tr=xxx）
 - 异常比例：窗口时间内错误率大于限制错误比例，触发降级，窗口时间结束后关闭降级
 - 异常数：一分钟内异常数大于限制异常数，触发降级，降级时间间隔结束后关闭降级（降级时间间隔建议设置>=60，因为异常数是每一分钟统计一次）（注意：这个到时详细测试看看，具体怎么配置合理）
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/downgrade-add.png)
 
#### 热点限流规则说明（这个其实就是对参数限流或对参数的值限流） （注意：参数的类型必须是基本类型的，否则无法生效）
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/hotspot-add.png)
 
#### 系统保护规则阀值类型说明
 -  LOAD：当前系统load1（1分钟的load）超过阀值且并发线程数超过系统容量触发（建议Load设置=CPU核心数 * 2.5；系统容量=秒级统计最大QPS*秒级统计最小响应时间）（注意：Load类型仅对Linux系统起作用，Load的值可使用命令：uptime查看），代码位置：com.alibaba.csp.sentinel.slots.system.SystemRuleManager#checkBbr
 - RT：所有入口流量的平均RT达到阀值后触发
 - 线程数：所有入口流量的并发线程数达到阀值后触发
 - 入口 QPS：所有入口流量的QPS达到阀值后触发
 - CPU 使用率：CPU使用率达到阀值后触发，代码位置：com.alibaba.csp.sentinel.slots.system.SystemRuleManager#checkSystem
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/load-add.png)

#### 新增授权规则说明
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/auth-add.png)