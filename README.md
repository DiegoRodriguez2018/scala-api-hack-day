# scala-api-hack-day

Start the server with
`sbt run`

The server will start in localhost port 3000 (http://127.0.0.1:3000/)

Test the server is up by doing
`curl http://127.0.0.1:3000/diagnose`

Add a new record to the database with:

```bash
curl -H "Content-Type: application/json" -d '{"data":"test"}' http://127.0.0.1:3000/record
```
