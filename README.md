# scala-api-hack-day

Start the server with hot-reload
`sbt ~reStart`

The server will start in localhost port 8080 (http://127.0.0.1:8080/)

Test the server is up by doing
`curl http://127.0.0.1:8080/diagnose`

Add a new record to the database with:

```bash
curl -H "Content-Type: application/json" -d '{"data":"test"}' http://127.0.0.1:8080/record
```
