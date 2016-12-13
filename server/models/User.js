/**
 * Created by Alessandro on 04/12/2016.
 */
var db = require('./db_config.js');

exports.save = function(firstName, lastName, login, email, gender, address, city, state, country, phoneNumber, lastLogin, birthDate, routes, callback){
  new db.User({
    'firstName': firstName,
    'lastName': lastName,
    'login': login,
    'email' : email,
    'gender' : gender,
    'address' : address,
    'city' : city,
    'state' : state,
    'country' : country,
    'phoneNumber' : phoneNumber,
    'lastLogin' : lastLogin,
    'birthDate' : birthDate,
    'routes' : routes,
    'created_at': new Date()
  }).save(function(error, user) {
    if(error) {

      callback('Não foi possível salvar o usuário');
    } else {

      callback(user);
    }
  });
};

exports.list = function(callback){
  //db.User.collection.drop();
  db.User.find({}, function(error, users) {
    if(error) {
      callback('Não foi possível retornar os usuários!');
    } else {
      callback(users);
    }
  });
};




