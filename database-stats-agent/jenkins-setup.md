# Jenkins 配置指南

## 前置要求
1. 安装 Jenkins
2. 配置 Maven 和 Docker 环境
3. 安装必要的 Jenkins 插件：
   - Pipeline
   - Docker Pipeline
   - Git

## Jenkins 任务配置
1. 创建新的 Pipeline 任务
2. 配置 Git 仓库地址
3. 选择 Jenkinsfile 作为 Pipeline 定义
4. 配置构建触发器（如代码提交自动触发）

## 环境变量配置
在 Jenkins 中配置以下环境变量：
- MONGODB_HOST
- MONGODB_DATABASE
- MONGODB_USERNAME
- MONGODB_PASSWORD