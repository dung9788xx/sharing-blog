# Blog Platform Design
## About
  Where to share you knowledge, build with Spring Boot + MySql
## Requirements
 - Docker
## RUN
1. env-example to .env file
```
 cp .env-example .env
```
2. Build docker 
```agsl
docker-compose up -d --build
```
After docker build success application will run at:
```agsl
http://localhost:6868
```
## Configuration auto build with Intellij IDEA
```agsl
File => Settings => Build, Execution, Deployment => Complier
 => check Build project automaticly => Apply => OK
```
abc