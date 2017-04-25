'use strict';
app.service('sharedProperties',["$cookies", function ($cookies) {
    
    var property = {};

    return {
        getProperty: function () {
        	if (null!=$cookies.getObject("property")){
        		property=JSON.parse($cookies.getObject("property"));
        		console.log($cookies.getObject('property'));
        	}
            return property;
        },
        setProperty: function(value) {
            property = value;
            $cookies.putObject("property", JSON.stringify(value));
        }
    };
}]);

// 
//var value = $cookies.get("key");


//ngApp.factory("userPersistenceService", ["$cookies", function($cookies) {
//	//your service code goes here
//	}
//]);

//app.service('service2',['service1', function(service1) {}]);