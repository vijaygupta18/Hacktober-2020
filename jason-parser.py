import json
import requests
response=requests.get("http://extreme-ip-lookup.com/json/119.160.198.197")
#ipaddress in google what is my ip address then replace
print('Response code:{}'.format(response.status_code))
todos=json.loads(response.text)
print('lat:{}'.format(response.json()["lat"]))
print('lon:{}'.format(response.json()["lon"]))
print('businessName:{}'.format(response.json()["businessName"]))
print('businessWebsite:{}'.format(response.json()["businessWebsite"]))
print('City:{}'.format(response.json()["city"]))
print('continent:{}'.format(response.json()["continent"]))
print('countryCode:{}'.format(response.json()["countryCode"]))
print('ipName:{}'.format(response.json()["ipName"]))
print('ipType:{}'.format(response.json()["ipType"]))
print('query:{}'.format(response.json()["query"]))
print('region:{}'.format(response.json()["region"]))
