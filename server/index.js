var app = require('./models/app_config');
var users = require('./models/User');
//var dataBase = require('./models/db_config');
//var gasStation = require('./routes/gas');
var express = require('express');


//var RouteModel = mongoose.model('Route');
//var CommentModel = mongoose.model('Comment');
//var GasModel = mongoose.model('GasStation');




app.use(express.static(__dirname + '/public'));

//app.use('/gas', gasStation);

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.get('/', function(request, response) {
  response.send('ok');
});

app.get('/users', function(req, res) {
  users.list(function(resp) {
    res.json(resp);
  });
});

app.post('/signUpUser', function(req, res, next){
  users.save(req.body.firstName, req.body.lastName, req.body.login, req.body.email, req.body.gender, req.body.address, req.body.city, req.body.state, req.body.country, req.body.phoneNumber, req.body.lastLogin, req.body.birthDate, req.body.routes, function(resp){
      res.json(resp);
  });
});

/*
app.listen(port, function() {
  console.log('Node app is running on port', port);

  RouteModel.findOneAndUpdate({
    name: 'AAA'
  }, {
    name: 'AAA',
    goingDistance: 123.76,
    startPoint: {lat: -5, lng: 4},
    extraPoints: [{lat: 6, lng: 9}, {lat: 67, lng: 98}]
  }, {
    upsert: true,
    'new': true
  });

  UserModel.findOneAndUpdate({ // se nao existir, insere
    firstName: "Zé"
  }, {
    firstName: "Zé",
    lastName: "Zezo",
    login: "Zezox"
  }, {
    upsert: true,
    'new': true
  }, function(err, document) {
    CommentModel.findOneAndUpdate({
      text: "AAAA"
    }, {
      text: "AAAA",
      author: document._id,
      thumbsUp: 45
    }, {
      upsert: true,
      'new': true
    }, function(err2, document2){
      GasModel.findOneAndUpdate({
        name: "Posto Ipiranga"
      }, {
        name: "Posto Ipiranga",
        comments: [document2._id],
        rating: 5,
        location: {lat: -7.2294637, lng: -35.9092364}
      }, {
        upsert: true,
        'new': true
      });
    });
  });
  */