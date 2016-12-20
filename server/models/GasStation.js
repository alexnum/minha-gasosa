/**
 * Created by GAEL on 04/12/2016.
 */
/**
 * Created by Alessandro on 04/12/2016.
 */
var db = require('./db_config.js');

exports.saveComment = function(text, author, thumbsUp, callback){
  new db.Comment({
    'text': text,
    'author': author,
    'thumbsUp': thumbsUp,
    'created_at': new Date()
  }).save(function(error, comment) {
    if(error) {

      callback('Could not register your comment!');
    } else {

      callback(comment);
    }
  });
};


exports.saveGas = function(name, city, state, comments, rating, description, location, callback){
  new db.GasStation({
    'name': name,
    'city': city,
    'state': state,
    'comments' : comments,
    'rating:' : rating,
    'description' : description,
    'location:' : location,
    'created_at': new Date()
  }).save(function(error, gas) {
    if(error) {

      callback('Could not register your gas station!');
    } else {

      callback(gas);
    }
  });
};


exports.listGasStation = function(callback){
  db.GasStation.find({}, function(error, gas) {
    if(error) {
      callback('Could not recover the gas stations!');
    } else {
      callback(gas);
    }
  });
};

exports.listComment = function(callback){
  db.Comment.find({}, function(error, comment) {
    if(error) {
      callback('Could not recover the comments!');
    } else {
      callback(comment);
    }
  });
};