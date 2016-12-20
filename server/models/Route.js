/**
 * Created by Alessandro on 04/12/2016.
 */
var db = require('./db_config.js');

exports.save = function(name, roundTrip, extra, goingDistance, backDistance, extraPoints, startPoint, endPoint, callback){
  new db.Route({
    'name': name,
    'roundTrip': roundTrip,
    'extra': extra,
    'goingDistance' : goingDistance,
    'backDistance' : backDistance,
    'extraPoints' : extraPoints,
    'startPoint:' : startPoint,
    'endPoint' : endPoint,
    'created_at': new Date()
  }).save(function(error, route) {
    if(error) {

      callback('Your route could not be saved!');
    } else {

      callback(route);
    }
  });
};

exports.list = function(callback){
  db.Route.find({}, function(error, routes) {
    if(error) {
      callback('Could not recover the routes!');
    } else {
      callback(routes);
    }
  });
};