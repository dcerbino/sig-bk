# sig
Trabajo para la materia Sistemas de Informacion general

Istruction to setup MYSQl db engine

1) login to your db engine:

mysql -u root -p

Then digit your root password

You will enter to mysql terminal environment

2) crete dig db:

CREATE DATABASE sig;

3) create user sig and grant db access

GRANT ALL ON sig.* TO 'sig'@'localhost' IDENTIFIED BY 'sig';

4) flush privilage.

FLUSH PRIVILEGES;

5) exit mysql enviroment 

exit

6) verify you can login to the db engine with new credential:

mysql -u sig -p sig
SELECT DATABASE();

the system shoud output this:


```
+------------+
| DATABASE() |
+------------+
| sig        |
+------------+
```

