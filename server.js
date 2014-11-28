var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

io.on('connection', function(socket){
    console.log("Connection is established with new client!");

    socket.on('client message', function(msg) {
        console.log('Message: ' + msg);
    });
});

http.listen(3000, function(){
    console.log('listening on port: 3000');
});
