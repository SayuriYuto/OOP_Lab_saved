# Java JDBC Experiment
## Download
1. MySQL</br>
2. MySQLConnector/J </br>
## MySQL
Step 1: Create Schema</br>
```sql
CREATE SCHEMA `student` ;
```
Step 2: Create table with column names </br>"name","rollno","country","marks"</br>
```sql
CREATE TABLE `student`.`student` (
  `name` VARCHAR(45) NULL,
  `rollno` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `marks` VARCHAR(45) NULL,
  PRIMARY KEY (`rollno`, `country`));
```

## VSCode
Step 1: Add Java extension  pack to VSCode</br>
Step 2: Add connector file in Reference libraries section</br>