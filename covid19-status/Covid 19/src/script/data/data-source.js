// penerapan callback function menjadi Promise
 class DataSource {
    static searchCountry(keyword) {
        return fetch(`https://covid19.mathdro.id/api/countries/${keyword}`)
            .then(response => {
                return response.json()
            })
            .then(responseJson => {
                if(responseJson.confirmed) {
                    return Promise.resolve(responseJson, );
                } else {
                    return Promise.reject(`${keyword} is not found`)
                }
            })
    }
}

export default DataSource;