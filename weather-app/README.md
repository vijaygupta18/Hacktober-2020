# weather-app
A node.js application to check weather by simply providing the address using *cli*. 

Here I am using different npm library i.e postman-request to fetch data from two different APIs, Mapbox (to fetch the coordinates of the provided address) and Weather stack (to fetch the weather on the provided coordinates)

By this section, I explored the asynchronous nature of Node.js. I learnt how to use asynchronous programming to make HTTP API requests to third-party HTTP APIs. This allowed me to pull in data, like real-time weather data, into my app.

## Steps to install:

**1. Clone the application using**
```gitattributes
git clone https://github.com/jainayu/weather-app
```

**2. Change the directory using**
```bash
cd notes-app
```

**3. Install the application using**
```gitattributes
npm install
```
------

## How to Use:

```
node app.js "your address"
```
Example
```
node app.js India
```
and for address with spaces you can enter
```
node app.js "New York"
```



