import requests, os,json,random,datetime

SERV_URL=os.getenv("SERV_URL","http://localhost:8080/api/v1/carrides")

carRides = requests.get(SERV_URL).json()
print(json.dumps(carRides,indent=3))

now = datetime.datetime.now()
carIds=[2022000105,2022000100,2022000101,2022000102,2022000103,2022000104]


a_new_ride = {
      "isAStart": True,
      "tripStartTime": now.strftime("%m/%d/%Y, %H:%M:%S"),
      "tripCompletionDate": now.strftime("%m/%d/%Y"),
      "customerID": "CUST_" + str(random.randint(1,10)),
      "startLon": -122.38175,
      "startLat": 37.816807,
      "endLon": -122.42031,
      "endLat": 37.775986,
      "passengerCnt": random.randint(1,4),
      "carId": random.choice(carIds),
      "eventTimeMillis": 1709184423417
   }
rep=requests.post(SERV_URL, json=a_new_ride)
print(json.dumps(rep.json(),indent=3))