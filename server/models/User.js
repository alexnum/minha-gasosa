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

      callback('Unable to save this user!');
    } else {

      callback(user);
    }
  });
};

exports.list = function(callback){
  db.User.find({}, function(error, users) {
    if(error) {
      callback('Could not recover the users!');
    } else {
      callback(users);
    }
  });
};



exports.update = function(id, firstName, lastName, login, email, gender, address, city, state, country, phoneNumber, lastLogin, birthDate, routes, callback) {
  db.User.findById(id, function(error, user) {
    if(firstName) {
      user.firstName = firstName;
    }
    if(lastName) {
      user.lastName = lastName;
    }
    if(login) {
      user.login = login;
    }
    if(email) {
      user.email = email;
    }
    if(gender) {
      user.gender = gender;
    }
    if(address) {
      user.address = address;
    }
    if(city) {
      user.city = city;
    }
    if(state) {
      user.state = state;
    }
    if(country) {
      user.country = country;
    }
    if(phoneNumber) {
      user.phoneNumber = phoneNumber;
    }
    if(lastLogin) {
      user.lastLogin = lastLogin;
    }
    if(birthDate) {
      user.birthDate = birthDate;
    }
    if(routes) {
      user.routes = routes;
    }
    user.save(function(error, user) {
      if(error) {
        callback('Could not update the user!');
      } else {
        callback(user);
      }
    });
  });
};


exports.delete = function(id, callback) {
  db.User.findById(id, function(error, user) {
    if(error) {
      callback('Could not recover the user!');
    } else {
      user.remove(function(error) {
        if(!error) {
          callback('Deleted user successfully');
        }
      });
    }
  });
};




