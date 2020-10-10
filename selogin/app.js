var bodyParser = require("body-parser"),
    methodOverride = require("method-override"),
    express = require("express"),
    app = express(),
    mongoose = require("mongoose"),
    passport = require("passport"),
    LocalStrategy = require("passport-local"),
    User = require("./models/user"),
    flash = require("connect-flash");

mongoose.connect("mongodb://localhost:27017/se", {
    useNewUrlParser: true,
    'useUnifiedTopology': true
});
mongoose.set('useFindAndModify', false);

var connection = mongoose.connection;

// APP CONFIG

app.set("view engine", "ejs");
app.use(express.static("public"));
app.use(bodyParser.json({
    limit: '50mb'
}));
app.use(bodyParser.urlencoded({
    limit: '50mb',
    extended: true
}));
app.use(methodOverride("_method"));
app.use(flash());

// RESTFUL ROUTES

app.use(require("express-session")({
    secret: "jaikumxr",
    resave: false,
    saveUninitialized: false
}));


app.use(passport.initialize());
app.use(passport.session());
passport.use(new LocalStrategy(User.authenticate()));
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());
app.use(function (req, res, next) {
    res.locals.currentUser = req.user;
    res.locals.error = req.flash("error");
    res.locals.success = req.flash("success");
    next();
});

app.get("/", function (req, res) {
    res.redirect("/home");
});


//index route
app.get("/home", isLoggedIn, function (req, res) {
    res.render("home", {
        currentUser: req.user,

    });

});

//admin Dashboard
app.get("/dashboard", isLoggedIn, function (req, res) {
    User.find({}, function (err, users) {
        if (err) {
            console.log(err);
        } else {
            res.render("dashboard", {
                users: users
            });
        }
    });
});

//AUTH ROUTES

//show signup form
app.get("/dashboard/createuser", function (req, res) {
    res.render("register", {
    });
});

//handle signup logic
app.post("/dashboard/createuser", function (req, res) {

    const {
        username,
        password,
        email,
        firstName,
        lastName,
        confirmpassword
    } = req.body;
    let errors = [];

    if (!username || !password || !email) {
        errors.push({
            msg: 'Please enter all required fields'
        });
    }

    var paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;

    if (!password.match(paswd)) {
        errors.push({
            msg: "Enter a password between 7 to 15 characters which contains at least one numeric digit and a special character."
        });
    }

    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
        errors.push({
            msg: "Please enter a valid email"
        })
    }

    if (password != confirmpassword) {
        errors.push({
            msg: "Both passwords are different. Kindly enter same password in the Confirm Password field."
        })
    }

    if (errors.length > 0) {
        req.flash("error", errors[0].msg);
        res.redirect("/dashboard/createuser");
    } else {
        var newUser = new User({
            username: req.body.username
        });
        if (req.body.role === "admin") {
            newUser.isAdmin = "true"
        }
        newUser.lastName = lastName;
        newUser.firstName = firstName;
        newUser.email = email;
        User.register(newUser, req.body.password, function (err) {
            if (err) {
                console.log(err);
                return res.redirect("/dashboard/createuser");
            }
            req.flash("success", "Successfully created user: " + req.body.username);
            res.redirect("/dashboard");
        });
    }
});

app.get("/dashboard/:id/edituser", isLoggedIn, function (req, res) {
    User.findById(req.params.id, function (err, foundUser) {
        if (err) {
            console.log(err);
        } else {
            res.render("edit", {
                user: foundUser,
            });
        }
    });
});

app.put("/dashboard/:id", isLoggedIn, function (req, res) {

    const {
        username,
        password,
        email,
        firstName,
        lastName,
        confirmpassword
    } = req.body;
    let errors = [];

    if (!username || !password || !email) {
        errors.push({
            msg: 'Please enter all required fields'
        });
    }

    var paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;

    if (!password.match(paswd)) {
        errors.push({
            msg: "Enter a password between 7 to 15 characters which contains at least one numeric digit and a special character."
        });
    }

    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
        errors.push({
            msg: "Please enter a valid email"
        })
    }

    if (password != confirmpassword) {
        errors.push({
            msg: "Both passwords are different. Kindly enter same password in the Confirm Password field."
        })
    }

    if (errors.length > 0) {
        req.flash("error", errors[0].msg);
        res.redirect("/dashboard/" + req.params.id + "/edituser");
    } else {
        User.findByIdAndRemove(req.params.id, function (err) {
            if (err) {
                console.log(err);
            } else {
                console.log("Updating user...");
            }
        });
        var newUser = new User({
            username: req.body.username,
            lastName: req.body.lastName,
            firstName: req.body.firstName,
            email: req.body.email,
        });
        if (req.body.role === "admin") {
            newUser.isAdmin = "true"
        }
        User.register(newUser, req.body.password, function (err) {
            if (err) {
                console.log(err);
                return res.redirect("/dashboard")
            }
            req.flash("success", "Successfully updated user: " + req.body.username);
            res.redirect("/dashboard");
        });
    }
});



//delete USER
app.delete("/dashboard/:id", isLoggedIn, function (req, res) {
    User.findByIdAndRemove(req.params.id, function (err) {
        if (err) {
            req.flash("error", "Could not remove user");
            res.redirect("/dashboard");
        } else {
            req.flash("success", "Removed user");
            res.redirect("/dashboard");
        }
    });
});

app.get("/profile/:id", function (req, res) {
    res.render("profile", {
        currentUser: req.user
    });
});

//show login form
app.get("/login", function (req, res) {
    res.render("login");
});

//handling login logic
app.post("/login", passport.authenticate("local", {
    successRedirect: "/home",
    failureRedirect: "/login",
    failureFlash: true
}), function () {});

//logout ROUTE
app.get("/logout", function (req, res) {
    req.logout();
    req.flash("success", "Successfully logged out");
    res.redirect("/login");
});



//isloggedin
function isLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }
    req.flash("error", "Please log in first")
    res.redirect("/login");
}


let port = process.env.PORT;
if (port == null || port == "") {
    port = 3000;
  }
  app.listen(port , function(){
    console.log("IMS is up");
  });