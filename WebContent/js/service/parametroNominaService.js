'use strict';

app.service('parametroNominaService',
    function parametroNominaService($resource) {
        return $resource('services/encuestaCtrl/:co_proceso_nom', {
        	co_proceso_nom: '@co_proceso_nom'
        }, {
            query: {
                method: 'GET',
                isArray: false
            },
            queryNo: {
            	url:'services/encuestaCtrl/listNo/:co_proceso_nom/:tx_parametro_nom',
                method: 'GET',
                isArray: false
            },
            findUser: {
            	url:'services/encuestaCtrl/findUser/:cedula',
                method: 'GET',
                isArray: false
            },
            personaYaEncuestada : {
            	url:'services/encuestaCtrl/personaYaEncuestada/:cedula/:nuCedulaFamiliar',
                method: 'GET',
                isArray: false
            }, 
            graficar: {
            	url:'services/encuestaCtrl/graficar/:interesdos',
                method: 'GET',
                isArray: false
            }  
        });
    });


 