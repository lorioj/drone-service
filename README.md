# Read Me First
RUNNING AND TESTING

Build: mvn clean install

Run: mvn spring-boot:run



CURL COMMAND TO TEST ENDPOINT

sample end point: http://localhost:8080/drone-service/api/test

//REGISTER DRONE
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/drone-service/api/register -d'{"serialNumber":"DRONE1", "droneModel": "LIGHTWEIGHT", "droneState": "IDLE", "batteryCapacity": 100, "weightLimit": 50}'


//CHECK DRONE AVAILABILITY FOR LOADING
curl -X GET \
     -H "Accept: application/json" \
     http://localhost:8080/drone-service/api/available/DRONE1


//LOAD
curl -X POST \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     http://localhost:8080/drone-service/api/load/DRONE1 \
     -d '{
           "name": "Paracetamol",
           "code": "MED123",
           "weight": 20,
           "image": "http://example.com/paracetamol.jpg"
         }'
//

//CHECKD BATTERY INFORMATION
curl -X GET \
     -H "Accept: application/json" \
     http://localhost:8080/drone-service/api/battery-information/DRONE1


//CHECKING LOADED MEDICATION
curl -X GET \
     -H "Accept: application/json" \
     http://localhost:8080/drone-service/api/loaded/DRONE1

