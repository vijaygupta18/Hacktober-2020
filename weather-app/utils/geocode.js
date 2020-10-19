const request = require('postman-request')

const geocode = (address, callback) => { 
    const url = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' 
    + encodeURIComponent(address) +'.json?'+
    'access_token=pk.eyJ1IjoiamFpbmF5dSIsImEiOiJja2NyejhrbTExam1vMnJsZmpjZ2UyY3g1In0.HtDMPM1KxpQj5hn1tNuc0g'

    request({url, json: true}, (error, { body } = {}) => {
        if (error) {
            callback('Unable to connect to location service!!', undefined)
        } else if (body.features.length === 0){
            callback('Unable to find location, Try another search', undefined)
        } else {
            callback(undefined,  data = {
                latitude:   body.features[0].center[1],
                longitude:  body.features[0].center[0],
                location:   body.features[0].place_name,
            })
        }  
    })
}

module.exports = geocode