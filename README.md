# expert-ci

使用 Java 语言实现一套 Lite CI 

## 执行

在目录 `ci/runner/src/main/java/dev/magaofei/ci/runner/Main.java` 执行

`Main.main()`


### TODO

- [x] 容器创建
- [ ] shell执行
- [x] 解析 yaml
- [ ] multiple stage
- [ ] 日志


### 问题

#### 解决 mac 电脑挂载问题
```
brew install socat
socat TCP-LISTEN:2376,reuseaddr,fork,bind=127.0.0.1 UNIX-CLIENT:/var/run/docker.sock
```
接着设置 host 为 `tcp://localhost:2376`