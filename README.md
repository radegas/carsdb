## CarsDB
**Build:**
```
mvn clean package
```

**Run tests:**
```
mvn test
```

**Run:**
```
java -jar target/CarsDB-0.1.0.jar
```

**Add a car:**
```
curl -X POST -H "Content-Type: application/json" -d '{"manufacturer": "Citroen", "type": "Berlingo", "registration": "4UD 9999"}' http://localhost:8080/cars
```

**List cars:**
```
curl http://localhost:8080/cars
```

**Set list of cars:**
```
curl -X PUT -H "Content-Type: application/json" -d '[{"manufacturer": "Citroen", "type": "Berlingo", "registration": "4UD 9999"}, {"manufacturer": "Ford", "type": "Mondeo", "registration": "2AH 0201"}]' http://localhost:8080/cars
```

