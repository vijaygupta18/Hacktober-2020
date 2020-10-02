const path = require('path')
const express = require('express')
const hbs = require('hbs')
const geocode = require('./utils/geocode')
const forecast = require('./utils/forecast')

const app = express()
const port = process.env.PORT || 3000

// Define paths for Express config
const publicDirectoryPath = path.join(__dirname, '../public')
const viewPath = path.join(__dirname, '../templates/views')
const partialsPath = path.join(__dirname, '../templates/partials')

// Setup handlebars engine & views location
app.set('view engine', 'hbs')
app.set('views', viewPath)
hbs.registerPartials(partialsPath)

// Setup static directory to serve
app.use(express.static(publicDirectoryPath))

app.get('', (req, res) => {
    res.render('index', {
        title: 'Weather',
        name: 'Ashutosh Shaha'
    })
})

app.get('/about', (req, res) => {
    res.render('about', {
        title: 'About Me',
        name: 'Ashutosh Shaha'
    })
})

app.get('/help', (req, res) => {
    res.render('help', {
        helpText: '',
        title: 'Help',
        name: 'Ashutosh Shaha'
    })
})

app.get('/weather', (req, res) => {
    if (!req.query.address ) {
        return res.send({
            error: 'Must provide a search'
        })
    }

    geocode(req.query.address, (error, { latitude, longitude, location } = {}) => {
        if (error) {
            return res.send({error})
        }

        forecast(latitude, longitude, (error, forecastData) => {
            if (error) {
                return res.send({ error })
            }

            res.send({
                forecast: forecastData,
                location,
                address: req.query.address
            })
            
        })
    })

    
})

app.get('/products', (req, res) => {
    if (!req.query.search) {
        return res.send({
            error: 'Must provide a search'
        })
    }

    console.log(req.query)
    res.send({
        products: []
    })
})

app.get('/help/*', (req, res) => {
    res.render('error', {
        error: 'Help article not found',
        title: '404',
        name: 'Ashutosh Shaha'
    })
})

app.get('*', (req, res) => {
    res.render('error', {
        error: 'Page not found',
        title: '404',
        name: 'Ashutosh Shaha'
    })
})

app.listen(port, () => {
    console.log('Server is up on port '+ port)
})