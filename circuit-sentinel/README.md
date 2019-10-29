#### 服务断路器 Sentinel
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
#### 添加限流规则，流控模式说明
 - 直接：直接限制当前资源
 - 关联：关联资源到达限流阈值后限制当前资源
 - 链路：入口资源到达限流阈值后限制当前资源
![image](https://github.com/firechiang/springcloud-test-alibaba/blob/master/circuit-sentinel/image/rule-add.png)
