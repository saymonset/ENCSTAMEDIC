 app.service('tipoCtrlService',["$cookies",  function ($cookies) {
                
                var property = {};

                return {
                    getProperty: function () {
                    	if (null!=$cookies.getObject("propertyCtrl")){
                    		property=JSON.parse($cookies.getObject("propertyCtrl"));
                    		 console.log($cookies.getObject('propertyCtrl'));
                    	}
                        return property;
                    },
                    setProperty: function(value) {
                    	  property = value;
                    	  $cookies.putObject("propertyCtrl", JSON.stringify(value));
                    }
                };
            }])  .value("tempStorage", {})
      	  .service("Navigator", function($location, tempStorage) {
      	    return {
      	      goTo: function(url, args) {
      	        tempStorage.args = args;
      	        $location.path(url);
      	      }
      	    };
      	  });