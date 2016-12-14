var db_string = 'mongodb://localhost:27017/minhagasosa';

if (process.env.PORT) {
	db_string = 'mongodb://heroku_mhcrtkhx:lihoc3618usahfd81au68rqtjn@ds119728.mlab.com:19728/heroku_mhcrtkhx';
}
var mongoose = require('mongoose').connect(db_string);
var Schema = mongoose.Schema;

var db = mongoose.connection;

db.on('error', console.error.bind(console, 'Erro ao conectar no banco'));

db.once('open', function() {

	var routeSchema = mongoose.Schema({
	  name: String,
	  roundTrip: Boolean,
	  extra: Boolean,
	  goingDistance: Number,
	  backDistance: Number,
	  extraPoints: [{ lat: Number, lng: Number}],
	  startPoint: { lat: Number, lng: Number},
	  endPoint: { lat: Number, lng: Number }
	});

	exports.Route = mongoose.model('Route', routeSchema);


	var userSchema = mongoose.Schema({
	  firstName: String,
	  lastName: String,
	  login: String,
	  email: String,
	  gender: String,
	  address: String,
	  city: String,
	  state: String,
	  country: String,
	  phoneNumber: String,
	  lastLogin: Date,
	  birthDate: Date,
	  routes: [{ type: Schema.Types.ObjectId, ref: 'Route' }],
	  created_at: Date
	});

	exports.User = mongoose.model('User', userSchema);

	var commentSchema = mongoose.Schema({
	  text: String,
	  author: { type: Schema.Types.ObjectId, ref: 'User' },
	  creationDate: Date,
	  thumbsUp: Number
	});

	exports.Comment = mongoose.model('Comment', commentSchema);

	var gasSchema = mongoose.Schema({
	  name: String,
	  city: String,
	  state: String,
	  comments: [{ type: Schema.Types.ObjectId, ref: 'Comment' }],
	  rating: Number,
	  description: String,
	  location: { lat: Number, lng: Number },
	});

	exports.GasStation = mongoose.model('GasStation', gasSchema);

});