var mongoose = require('mongoose'), Schema = mongoose.Schema;

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

	exports.route = module.exports = mongoose.model('Route', routeSchema);


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

	exports.user = module.exports = mongoose.model('User', userSchema);

	var comment = mongoose.Schema({
	  text: String,
	  author: { type: Schema.Types.ObjectId, ref: 'User' },
	  creationDate: Date,
	  thumbsUp: Number
	});

	exports.comment = module.exports = mongoose.model('Comment', comment);

	var gasSchema = mongoose.Schema({
	  name: String,
	  city: String,
	  state: String,
	  comments: [{ type: Schema.Types.ObjectId, ref: 'Comment' }],
	  rating: Number,
	  description: String,
	  location: { lat: Number, lng: Number },
	});

	exports.gasStation = module.exports = mongoose.model('GasStation', gasSchema);

});