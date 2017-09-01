angular.module('siteForm', ['ngRoute', 'ngCookies', 'exampleApp.services', 'oc.lazyLoad'])
	.config([
			
		'$routeProvider',
		'$locationProvider',
		'$httpProvider',
		
		function($routeProvider, $locationProvider,
				$httpProvider){
			$routeProvider.when('/Inscricao', {
					templateUrl : 'partials/Inscrito/Site.html',
					controller : InscritoSiteCadastroController,
					});
			$routeProvider.when('/Confirmada', {
				templateUrl : 'partials/Inscrito/Confirmada.html',
				controller : InscritoSiteCadastroController,
				});
			
			$routeProvider.when('/', {
				template : '<div></div>',
				controller : InscritoIndexController
			});
				}	
			])	

function InscritoIndexController($scope, $cookieStore) {
	window.location.href = '/AcessoRestrito/Principal.html#/Inscricao'
}

			