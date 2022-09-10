---
spring-boot-react-mariadb: Spring Boot + JPA + Docker + Mariadb  + Openjdk + Maven+ MacOS in  Visual Studio Code 
---

## 1. Prerequisites
1.  Install Java Development Kit as following steps:
    - Java Development Kit (JDK):  
      [https://www.microsoft.com/openjdk](https://www.microsoft.com/openjdk)
2.  Install Jave VSCode Extensions as following steps:
    - Extension Pack for Java:  
      [https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
    - Lombok Annotations Support:  
      [https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-lombok](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-lombok)
3.  Install Spring Boot VSCode Extensions as following steps:
    - Spring Boot Tools:  
      [https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-spring-boot](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-spring-boot)
    - Spring Initializr:  
      [https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr)
    - Spring Boot Dashboard:  
      [https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-boot-dashboard](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-boot-dashboard)
  
    We recommend installing [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=pivotal.vscode-boot-dev-pack) that includes all of the spring boot VSCode extensions above.  


## 2. Download & Install Docker For MacOS/Windows

### Download & Install Docker
1.  Download Docker Desktop For Mac:
    - Mac with Intel chip:  
      [https://desktop.docker.com/mac/main/amd64/Docker.dmg?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-mac-amd64](https://desktop.docker.com/mac/main/amd64/Docker.dmg?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-mac-amd64)
    - Mac with Apple chip:  
      [https://desktop.docker.com/mac/main/arm64/Docker.dmg?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-mac-arm64](https://desktop.docker.com/mac/main/arm64/Docker.dmg?utm_source=docker&utm_medium=webreferral&utm_campaign=docs-driven-download-mac-arm64)

2.  Download Docker Desktop For Windows:
    - Download Docker Desktop for Windows:  
      [https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe))

3.  Install Docker Desktop Documentation For Mac/Windows:
    - For information about installing Docker, see Get Docker in Docker documentation:  
      [https://docs.docker.com/get-docker/)](https://docs.docker.com/get-docker/))  


## 3. Install Mariadb Database by Docker Image

### Install Mariadb
1. create network , mariadb container :
```bash
  # create network
  $ docker network create demo
  # create mariadb
  $ docker run --detach --network demo --name mariadb -p 3306:3306 --env MARIADB_ROOT_PASSWORD=root  mariadb:10.4 
  # approach mariadb container
  $ docker exec -it mariadb mariadb --user root -proot
``` 

2. create new database, new user,privileges by root user :
```mysql
  # create DATABASE
  > CREATE DATABASE demo;
  # create  USER
  > CREATE USER `demo`@`%` IDENTIFIED BY 'demo';
  # grant privileges
  > GRANT Alter, Alter Routine, Create, Create Routine, Create Temporary Tables, Create View, Delete, Drop, Event, Execute, Grant Option, Index, Insert, Lock Tables, References, Select, Show View, Trigger, Update ON `demo`.* TO `demo`@`%`;
  > flush privileges;
  > exit;
``` 


### Create Biz Table &  inital Data

1. Create User Table :
```mysql
  # Create User Table

  DROP TABLE IF EXISTS `user`;

  CREATE TABLE `user` (
      `id` varchar(10) NOT NULL,
      `fin` varchar(8) NOT NULL,             
      `name` varchar(255) NOT NULL,         
      `email` varchar(255) NOT NULL,
      `password` varchar(255) DEFAULT NULL,
      `status` tinyint(4) NOT NULL DEFAULT 0,
      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `created_by` varchar(255) NOT NULL,
      `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `updated_by` varchar(255) NOT NULL,
      PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

``` 
2. create User balance Table :

```mysql
  # Create User Balance Table

  DROP TABLE IF EXISTS `user_balance`;

  CREATE TABLE `user_balance` (
      `id` varchar(10) NOT NULL,
      `user_id` varchar(10) NOT NULL,              
      `balance` varchar(255) NOT NULL,
      `status` tinyint(4) NOT NULL DEFAULT 0,
      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `created_by` varchar(255) NOT NULL,
      `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `updated_by` varchar(255) NOT NULL,
      PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

``` 
2. create User debt Table :

```mysql
  # Create User Transaction Table

  DROP TABLE IF EXISTS `user_transaction`;

  CREATE TABLE `user_transaction` (
      `id` varchar(10) NOT NULL,
      `user_id` varchar(10) NOT NULL,  
         
      `debt` varchar(255) DEFAULT NULL,
      `creditor` varchar(10) NOT NULL,        
      `status` tinyint(4) NOT NULL DEFAULT 0,
      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `created_by` varchar(255) NOT NULL,
      `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `updated_by` varchar(255) NOT NULL,
      PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

``` 

3. create User transaction Table :

```mysql
  # Create User transaction Table

  DROP TABLE IF EXISTS `user_transaction`;

  CREATE TABLE `user_transaction` (
      `id` varchar(10) NOT NULL,
      `user_id` varchar(10) NOT NULL, 
      `type` tinyint(4) NOT NULL DEFAULT 0,                
      `amount` varchar(255) DEFAULT NULL,    
      `status` tinyint(4) NOT NULL DEFAULT 0,
      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `created_by` varchar(255) NOT NULL,
      `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `updated_by` varchar(255) NOT NULL,
      PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

``` 

3. create User audit Table :

```mysql
  # Create User audit Table

  DROP TABLE IF EXISTS `user_audit`;

  CREATE TABLE `user_audit` (
      `id` varchar(10) NOT NULL,
      `user_id` varchar(10) NOT NULL, 
      `type` tinyint(4) NOT NULL DEFAULT 0,                
      `status` tinyint(4) NOT NULL DEFAULT 0,
      `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `created_by` varchar(255) NOT NULL,
      `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
      `updated_by` varchar(255) NOT NULL,
      PRIMARY KEY (`id`) USING BTREE
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

``` 


## 4. Getting Started React SpringBoot Demo Project 
### Running Backend Service as following steps:
* Spring Boot version 2.6.11 
* OpenJDK 11
* 数据对象显示


### Running Frontend Application as following steps (Command + Shift + P):

* Login Application Spring Boot 
```TypeScript
export type EditorFactoryArgs<T> = {
  field: string;
  valueObject: T;
  props: EditorProps;
  trans: TFunction;
  transLabelPrefix: string;
  formikProps?: FormikProps<T>;
  base: {
    inputId: string;
    name: string;
    value: any;
    onChange: any;
  };
};
```
* 表单显示
* 数据对象显示


## 4. Getting Started React SpringBoot Demo Project 
### Running Backend Service as following steps:
* 表单提交，带前端和后端API数据校验
* 表单显示
* 数据对象显示

## 5. Running API UnitTest   
### Running API UnitTest  
* 表单提交，带前端和后端API数据校验
* 表单显示
* 数据对象显示