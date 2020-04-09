# scala-api-hack-day **WIP**

Start the server with hot-reload
`sbt ~reStart`

The server will start in localhost port 8080 (http://127.0.0.1:8080/)

Test the server is up by doing
`curl http://127.0.0.1:8080/diagnose`

Add a new record to the database with: (Not yet implemented)

```bash
curl -H "Content-Type: application/json" -d '{"data":"test"}' http://127.0.0.1:8080/record
```

### Seeding the database
You will need postgres installed with `postgres` user without password. 

To seed the database run the following commands. 

$ curl -O https://raw.githubusercontent.com/tpolecat/doobie/series/0.7.x/world.sql
$ psql -c 'create user postgres createdb' postgres
$ psql -c 'create database world;' -U postgres
$ psql -c '\i world.sql' -d world -U postgres


Then you can try running this query:

`select name, continent, population from country where name like 'U%';`