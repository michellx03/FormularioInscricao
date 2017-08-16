function StatusPremioConsultaController($scope, $http, $routeParams, $location) {
	$scope.ListaStatus = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/statusPremio/list',
		cache : false
	}).success(function(data) {
		$scope.ListaStatus = data;
	});

}