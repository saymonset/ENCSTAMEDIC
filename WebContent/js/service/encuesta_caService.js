'use strict';

app.service('encuesta_caService',
    function encuesta_caService($resource) {
        return $resource('services/encuesta_caService/:co_proceso_nom', {
        	co_proceso_nom: '@co_proceso_nom'
        }, {
            query: {
                method: 'GET',
                isArray: false
            },
            buscarMedicamento: {
            	url:'services/encuesta_caService/buscarMedicamento/:co_proceso_nom',
                method: 'GET',
                isArray: false
            },
            
            buscarCedulaMedicamento: {
            	url:'services/encuesta_caService/buscarCedulaMedicamento/:cedula',
                method: 'GET',
                isArray: false
            },
            buscarCedulaMedicamentos: {
            	url:'services/encuesta_caService/buscarCedulaMedicamentos/:feDesde/:feHasta',
                method: 'GET',
                isArray: true
            },
            buscarCedula: {
            	url:'services/encuesta_caService/buscarCedula/:co_proceso_nom',
                method: 'GET',
                isArray: false
            },
            queryNo: {
            	url:'services/encuesta_caService/listNo/:co_proceso_nom/:tx_parametro_nom',
                method: 'GET',
                isArray: false
            },
            findUser: {
            	url:'services/encuesta_caService/findUser/:cedula',
                method: 'GET',
                isArray: false
            },
            parametroNominaDtos : {
            	url:'services/encuesta_caService/parametroNominaDtos/:cedula/:nuCedulaFamiliar',
                method: 'GET',
                isArray: true
            }, 
            familiares : {
            	url:'services/encuesta_caService/familiares/:cedula',
                method: 'GET',
                isArray: true
            },
            
            
            graficar: {
            	url:'services/encuesta_caService/graficar/:interesdos/:feDesde/:feHasta',
                method: 'GET',
                isArray: false
            }  
        });
    });


 