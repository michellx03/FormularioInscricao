function StatusConsultaController($scope, $http, $routeParams, $location) {
	$scope.ListaStatus = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/status/list',
		cache : false
	}).success(function(data) {
		$scope.ListaStatus = data;
	});

}