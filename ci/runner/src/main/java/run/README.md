# README

优先实现 DockerExecutor 

## ProcessExecutor
负责shell的执行

主要作用是将每个 stage 转换为一个 shell 文件，然后去执行

## DockerExecutor

负责使用容器的方式去执行，每个 stage 转换为 Dockerfile 里的 stage 