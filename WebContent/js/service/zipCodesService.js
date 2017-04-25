'use strict';
app.service("ZipCodes", function($rootScope) {
    return {
        setZipCode: function(type, zip) {
            this[type] = zip;
            $rootScope.$broadcast("zipCodeUpdated", {
                type: type, zipCode: zip 
            });
        }
    }
});