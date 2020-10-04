var express = require('express');
var router = express.Router();
var Blog = require('../models/blog');
var middleware = require('../middleware');
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

//INDEX - show all blogs
router.get('/', function(req, res) {
	var noMatch = null;
	if (req.query.search) {
		const regex = new RegExp(escapeRegex(req.query.search), 'gi');
		Blog.find({ name: regex }, function(err, allBlogs) {
			if (err) {
				console.log(err);
			} else {
				if (allBlogs.length < 1) {
					noMatch = 'No Blogs matched that query, please try again.';
				}
				res.render('blogs/index', { blogs: allBlogs, page: 'blogs', noMatch: noMatch });
			}
		});
	} else {
		Blog.find({}, function(err, allBlogs) {
			if (err) {
				console.log(err);
			} else {
				res.render('blogs/index', { blogs: allBlogs, page: 'blogs', noMatch: noMatch });
			}
		});
	}
});

//CREATE - add new blog to DB
router.post('/', middleware.isLoggedIn, upload.single('image'), function(req, res) {
	cloudinary.v2.uploader.upload(req.file.path, function(err, result) {
		if (err) {
			req.flash('error', err.message);
			return res.redirect('back');
		}
		// add cloudinary url for the image to the blog object under image property
		req.body.blog.image = result.secure_url;
		// add image's public_id to blog object
		req.body.blog.imageId = result.public_id;
		// add author to blog
		req.body.blog.author = {
			id: req.user._id,
			username: req.user.username
		};
		req.body.blog.description = req.sanitize(req.body.blog.description);
		Blog.create(req.body.blog, function(err, blog) {
			if (err) {
				req.flash('error', err.message);
				return res.redirect('back');
			}
			res.redirect('/blogs/' + blog.id);
		});
	});
});

//NEW - show form to create new blog
router.get('/new', middleware.isLoggedIn, function(req, res) {
	res.render('blogs/new');
});

//SHOW - shows more info about one blog
router.get('/:id', function(req, res) {
	//find the blog with provide id
	Blog.findById(req.params.id).populate('comments likes').exec(function(err, foundBlog) {
		if (err) {
			console.log(err);
		} else {
			//render show template with that blog
			res.render('blogs/show', { blog: foundBlog });
		}
	});
});

// EDIT BLOG ROUTE
router.get('/:id/edit', middleware.checkBlogOwnership, function(req, res) {
	Blog.findById(req.params.id, function(err, foundBlog) {
		res.render('blogs/edit', { blog: foundBlog });
	});
});

router.put('/:id', middleware.checkBlogOwnership, upload.single('image'), function(req, res) {
	Blog.findById(req.params.id, async function(err, blog) {
		if (err) {
			req.flash('error', err.message);
			res.redirect('back');
		} else {
			if (req.file) {
				try {
					await cloudinary.v2.uploader.destroy(blog.imageId);
					var result = await cloudinary.v2.uploader.upload(req.file.path);
					blog.imageId = result.public_id;
					blog.image = result.secure_url;
				} catch (err) {
					req.flash('error', err.message);
					res.redirect('back');
				}
			}
			blog.name = req.body.blog.name;
			blog.description = req.sanitize(req.body.blog.description);
			blog.save();
			req.flash('success', 'Successfully Updated!');
			res.redirect('/blogs/' + req.params.id);
		}
	});
});

//DESTROY BLOG
router.delete('/:id', middleware.checkBlogOwnership, function(req, res) {
	Blog.findById(req.params.id, async function(err, blog) {
		if (err) {
			req.flash('error', err.message);
			return res.redirect('back');
		}
		try {
			await cloudinary.v2.uploader.destroy(blog.imageId);
			blog.remove();
			req.flash('success', 'Blog deleted successfully!');
			res.redirect('/blogs');
		} catch (error) {
			req.flash('error', err.message);
			return res.redirect('back');
		}
	});
});

// Blog Like Route
router.post('/:id/like', middleware.isLoggedIn, function(req, res) {
	Blog.findById(req.params.id, function(err, foundBlog) {
		if (err) {
			console.log(err);
			return res.redirect('/blogs');
		}

		// check if req.user._id exists in foundBlog.likes
		var foundUserLike = foundBlog.likes.some(function(like) {
			return like.equals(req.user._id);
		});

		if (foundUserLike) {
			// user already liked, removing like
			foundBlog.likes.pull(req.user._id);
		} else {
			// adding the new user like
			foundBlog.likes.push(req.user);
		}

		foundBlog.save(function(err) {
			if (err) {
				console.log(err);
				return res.redirect('/blogs');
			}
			return res.redirect('/blogs/' + foundBlog._id);
		});
	});
});

function escapeRegex(text) {
	return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, '\\$&');
}

module.exports = router;
