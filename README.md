# scala-jdbc-example

how to:
- `sudo docker pull mysql/mysql-server:latest`
- ```
  docker run \
  --name=mysql-test \
  --publish 3306:3306 \
  --detach \
  --env MYSQL_ROOT_PASSWORD=root  \
  mysql/mysql-server
  ```
- `docker exec -it mysql-test bash`
- `mysql -u root -p`
- `update mysql.user set host = "%" where user="root";`
- `docker restart mysql-test`
- change `.../mysql...` in jdbc url to your db name 
- go ahead...
