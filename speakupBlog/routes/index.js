var express = require('express');
var router = express.Router();
var passport = require('passport');
var User = require('../models/user');
var Blog = require('../models/blog');
var multer = require('multer');
var storage = multer.diskStorage({
	filename: function(req, file, callback) {
		callback(null, Date.now() + file.originalname);
	}
});
var imageFilter = function(req, file, cb) {
	// accept image files only
	if (!file.originalname.match(/\.(jpg|jpeg|png|gif)$/i)) {
		return cb(new Error('Only image files are allowed!'), false);
	}
	cb(null, true);
};
var upload = multer({ storage: storage, fileFilter: imageFilter });

var cloudinary = require('cloudinary');
cloudinary.config({
	cloud_name: 'speak-up',
	api_key: process.env.CLOUDINARY_API_KEY,
	api_secret: process.env.CLOUDINARY_API_SECRET
});

//root route
router.get('/', function(req, res) {
	res.render('landing');
});

//show register form
router.get('/register', function(req, res) {
	res.render('register', { page: 'register' });
});

//handle sign up logic
router.post('/register', upload.single('image'), function(req, res) {
	cloudinary.v2.uploader.upload(req.file.path, function(err, result) {
		if (err) {
			req.flash('error', err.message);
			return res.redirect('back');
		}
		// add cloudinary url for the image to the blog object under image property
		req.body.avatar = result.secure_url;
		// add image's public_id to blog object
		req.body.avatarId = result.public_id;

		var newUser = new User({
			username: req.body.username,
			firstName: req.body.firstName,
			lastName: req.body.lastName,
			email: req.body.email,
			avatar: req.body.avatar,
			avatarId: req.body.avatarId,
			bio: req.body.bio
		});

		if (req.body.adminCode === process.env.ADMIN_CODE) {
			newUser.isAdmin = true;
		}

		User.register(newUser, req.body.password, function(err, user) {
			if (err) {
				req.flash('error', err.message);
				return res.redirect('/register');
			}
			passport.authenticate('local')(req, res, function() {
				if (newUser.isAdmin) {
					console.log(user.username);
					req.flash('success', 'Successfully Signed Up to SpeakUP! ' + user.username + ", You're an Admin!");
				} else {
					console.log(user.username);
					req.flash('success', 'Successfully Signed Up to SpeakUP! ' + user.username);
				}
				res.redirect('/blogs');
			});
		});
	});
});

//show login from
router.get('/login', function(req, res) {
	res.render('login', { page: 'login' });
});

//handle login form logic

router.post('/login', function(req, res, next) {
	passport.authenticate('local', {
		successRedirect: '/blogs',
		failureRedirect: '/login',
		failureFlash: true,
		successFlash: 'Welcome to SpeakUP, ' + req.body.username + '!'
	})(req, res);
});

//LOGOUT ROUTE
router.get('/logout', function(req, res) {
	req.logout();
	req.flash('success', 'Logged you out!');
	res.redirect('/blogs');
});

//USER Profile Route
router.get('/users/:id', function(req, res) {
	User.findById(req.params.id, function(err, foundUser) {
		if (err) {
			req.flash('error', 'Something went wrong');
			return res.redirect('/');
		}
		Blog.find().where('author.id').equals(foundUser._id).exec(function(err, blogs) {
			if (err) {
				req.flash('error', 'Something went wrong');
				return res.redirect('/');
			}
			res.render('users/show', { user: foundUser, blogs: blogs });
		});
	});
});

//EDIT ROUTE
router.get('/users/:id/edit', function(req, res) {
	User.findById(req.params.id, function(err, foundUser) {
		if (err) {
			res.redirect('back');
		} else {
			res.render('users/edit', { user: foundUser });
		}
	});
});

//Update ROUTE
router.put('/users/:id', upload.single('image'), function(req, res) {
	User.findById(req.params.id, async function(err, user) {
		if (err) {
			req.flash('error', err.message);
			res.redirect('back');
		} else {
			if (req.file) {
				try {
					await cloudinary.v2.uploader.destroy(user.avatarId);
					var result = await cloudinary.v2.uploader.upload(req.file.path);
					user.avatarId = result.public_id;
					user.avatar = result.secure_url;
				} catch (err) {
					req.flash('error', err.message);
					res.redirect('back');
				}
			}
			user.firstName = req.body.firstName;
			user.lastName = req.body.lastName;
			user.email = req.body.email;
			user.bio = req.body.bio;
			user.save();
			req.flash('success', 'Profile Updated Successfully!');
			res.redirect('/users/' + user._id);
		}
	});
});

module.exports = router;
