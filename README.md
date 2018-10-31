# sig
Trabajo para la materia Sistemas de Informacion general

### Instructions to setup MYSQl DB engine

##### 1) Login to your db engine:
```
mysql -u root -p
```
Then digit your root password

You will enter to mysql terminal environment

##### 2) Create a sig database inside the mysql shell:
```
CREATE DATABASE sig;
```


##### 3) Create an user sig and grant db access to it
```
CREATE USER 'sig'@'localhost' IDENTIFIED BY 'sig';
GRANT ALL ON sig.* TO 'sig'@'localhost';
```

##### 4) Flush privilage.

```
FLUSH PRIVILEGES;
```

##### 5) Exit the mysql environment 
```
exit
```

##### 6) Verify you can login to the db engine with the new credentials:

```
mysql -u sig -p sig
SELECT DATABASE();
```

The system should show the following output:

```
+------------+
| DATABASE() |
+------------+
| sig        |
+------------+
```

## Check if API is working
```
sbt run
```


Go to a browser and access `http://localhost:9000`