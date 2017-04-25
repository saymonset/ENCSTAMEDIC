'use strict';

app.service('mailService',
    function mailService($resource) {
        return $resource('services/mandarMailResource/:to/:cedula/:cedulaFamiliar/:smtpUser/:smtpPassword/:message/:title', {
        	to: '@to',cedula: '@cedula',cedulaFamiliar:'@cedulaFamiliar',smtpUser:'@smtpUser',smtpPassword:'@smtpPassword',message:'@message',title:'@title'
        }, {
            sendMail: {
            	url:'services/mandarMailResource/sendMail/:to/:cedula/:cedulaFamiliar/:smtpUser/:smtpPassword/:message/:title',
                method: 'POST',
                isArray: false
            }  
        });
    });


 