var app = angular.module("app", [ "ngResource", "ngRoute", "daypilot",
		"ui.bootstrap", "ngCookies" ]);
// realizamos la configuraci�n del enrutado dependiendo de la url
// con el objeto $routeProvider haciendo uso de when
// en este caso, cuando estemos en la p�gina principal, le decimos que
// cargue el archivo templates/index.html y que haga uso del controlador
// indexController, as� en todos los casos
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "encuesta_ca.html",
		controller : "encuesta_caCtrl"
	}).when("/ver", {
		templateUrl : "encuesta_caver.html",
		controller : "encuesta_caVerCtrl"
	}).when("/verOld", {
		templateUrl : "encuestaver.html",
		controller : "encuestaVerCtrl"
	})

	// este es digamos, al igual que en un switch el default, en caso que
	// no hayamos concretado que nos redirija a la p�gina principal
	.otherwise({
		reditrectTo : "/"
	});
})
