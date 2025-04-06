# 使用 OpenJDK 23 精简版作为基础镜像，减小镜像体积
FROM openjdk:23-slim

# 安装基础工具包，包含 ps、top 等进程管理工具
# apt-get update 更新包索引
# apt-get install -y procps 安装 procps 包，-y 表示自动确认
# RUN apt-get update && apt-get install -y procps

# 设置容器的工作目录为 /app
WORKDIR /app

# 将本地构建的 jar 包复制到容器的 /app/app.jar
COPY target/database-stats-agent-1.0-SNAPSHOT.jar app.jar

# 声明容器将使用 8081 端口（仅声明，不会自动映射）
EXPOSE 8081

# 容器启动命令
# java: 启动 Java 程序
# -Dspring.profiles.active=docker: 激活 docker 配置文件
# -jar: 运行 jar 包
# app.jar: jar 包名称
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]