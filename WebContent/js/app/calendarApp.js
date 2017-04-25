	var app = angular.module("app", [ "ngResource","daypilot" ]);
	app
	  .value("tempStorage", {})
	  .service("Navigator", function($location, tempStorage) {
	    return {
	      goTo: function(url, args) {
	        tempStorage.args = args;
	        $location.path(url);
	      }
	    };
	  });