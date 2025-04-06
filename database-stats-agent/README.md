# Database Stats Agent

## Docker Setup

### Prerequisites
- Docker installed on your machine
- Docker Compose installed on your machine

### Building Docker Image

您可以使用以下两种方式之一来构建 Docker 镜像：

1. 使用 Docker 命令直接构建：
```bash
docker build -t images-name:latest .
```

2. 使用 Docker Compose 构建（推荐）：
```bash
docker-compose build
```

### Running with Docker Compose

1. 启动容器：
```bash
docker-compose up -d
```
此命令会：
- 如果镜像不存在，自动构建镜像
- 在后台运行容器（-d 参数）
- 映射 8081 端口到主机
- 配置 MongoDB 连接到 host.docker.internal

2. 停止容器：
```bash
docker-compose down
```

### 手动构建和运行

#### 1. 构建 Docker 镜像
```bash
# 在 database-stats-agent 目录下执行
docker build -t image-name:latest .
```

构建命令说明：
- `-t image-name:latest`: 指定镜像名称和标签
- `.`: 使用当前目录的 Dockerfile

#### 2. 运行 Docker 容器
```bash
docker run -d \
  --name 20250404_05-container \
  -p 8081:8081 \
  -v $(pwd)/logs:/app/logs \
  -e SPRING_DATA_MONGODB_HOST=host.docker.internal \
  20250404_04_image:latest
```

运行命令说明：
- `-d`: 在后台运行容器
- `--name database-stats-agent`: 指定容器名称
- `-p 8081:8081`: 将容器的 8081 端口映射到主机的 8081 端口
- `-e SPRING_DATA_MONGODB_HOST=host.docker.internal`: 设置 MongoDB 主机环境变量

#### 3. 查看容器状态和日志
```bash
# 查看容器状态
docker ps

# 查看容器日志
docker logs container-name/container-id

# 实时查看日志
docker logs -f container-name/container-id
```

#### 4. 停止和删除容器
```bash
# 停止容器
docker stop container-name/container-id

# 删除容器
docker rm container-name/container-id
```

### 环境变量配置

容器运行时可以通过环境变量配置以下参数：
- `SPRING_DATA_MONGODB_HOST`: MongoDB 服务器地址（默认：host.docker.internal）
- `TAG`: 构建的 Docker 镜像标签（默认：latest）

### 访问应用

应用成功启动后，可以通过以下地址访问：
- API 地址：`http://localhost:8081`

## API Usage

### Get All Databases
```bash
curl -X GET http://localhost:8081/api/v1/stats/databases
```

**Example Response:**
```json
["database1", "database2", "database3"]
```

### Get Collections
```bash
curl -X GET http://localhost:8081/api/v1/stats/collections?database=local
```

**Optional Parameter:**
- `database`: Database name to filter collections

**Example Response:**
```json
["collection1", "collection2", "collection3"]
```

非FAT JAR启动方式:
# 将依赖复制到 target/lib 目录
mvn dependency:copy-dependencies -DoutputDirectory=target/lib
# 在项目根目录下运行
java -cp target/database-stats-agent-1.0-SNAPSHOT.jar:target/lib/* com.example.dbstats.DatabaseStatsApplication