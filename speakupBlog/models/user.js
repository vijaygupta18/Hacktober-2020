var mongoose = require('mongoose'),
	passportLocalMongoose = require('passport-local-mongoose');

var UserSchema = new mongoose.Schema({
	username: String,
	password: String,
	avatar: String,
	avatarId: String,
	firstName: String,
	lastName: String,
	email: String,
	bio: String,
	isAdmin: { type: Boolean, default: false }
});

UserSchema.plugin(passportLocalMongoose);

module.exports = mongoose.model('User', UserSchema);
