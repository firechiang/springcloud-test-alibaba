#### 一、下载以及解压安装包
```bash
# 下载安装包
$ wget -P /home/nacos-package https://github.com/alibaba/nacos/releases/download/1.1.3/nacos-server-1.1.3.tar.gz

# 解压安装包
$ tar -zxvf /home/nacos-package/nacos-server-1.1.3.tar.gz -C /home
```

#### 二、启动服务（访问地址：http://IP:8848/nacos，用户名：nacos，密码：nacos）
```bash
$ cd /home/nacos/bin

# 单机启动（注意：单机不需要依赖MySQL）
$ sh startup.sh -m standalone
```