var express = require('express'),
	app = express(),
	bodyParser = require('body-parser'),
	mongoose = require('mongoose'),
	flash = require('connect-flash'),
	expressSanitizer = require('express-sanitizer'),
	methodOverride = require('method-override'),
	passport = require('passport'),
	LocalStrategy = require('passport-local'),
	User = require('./models/user');

//requiring routes
var commentRoutes = require('./routes/comments'),
	blogRoutes = require('./routes/blogs'),
	indexRoutes = require('./routes/index');

var url = process.env.DATABASEURL || 'mongodb://localhost:27017/speakup';
mongoose.connect(url, { useNewUrlParser: true, useUnifiedTopology: true });

app.use(bodyParser.urlencoded({ extended: true }));
app.set('view engine', 'ejs');
app.use(express.static(__dirname + '/public'));
app.use(methodOverride('_method'));
app.use(flash());
app.use(expressSanitizer());

app.locals.moment = require('moment');

//PASSPORT CONFIG
app.use(
	require('express-session')({
		secret: process.env.PASSPORT_SECRET,
		resave: false,
		saveUninitialized: false
	})
);
app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());
app.use(function(req, res, next) {
	res.locals.currentUser = req.user;
	res.locals.error = req.flash('error');
	res.locals.success = req.flash('success');
	next();
});

app.use('/', indexRoutes);
app.use('/blogs', blogRoutes);
app.use('/blogs/:id/comments', commentRoutes);

var port = process.env.PORT || 3000;
app.listen(port, function() {
	console.log('SpeakUp Server Has Started!');
});
