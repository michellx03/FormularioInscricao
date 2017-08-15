function InformacaoConsultaController($scope, $http, $routeParams, $location) {

	$scope.Informacao = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/informacao/list',
		cache : false
	}).success(function(data) {

		$scope.Informacao = data;
	});
}

function InformacaoAlteracaoController($scope, $http, $routeParams, $location) {
	
	$scope.Informacao;	

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/informacao/alteracao?id='+$routeParams.id+'',
		cache : false
	}).success(function(data) {
		$scope.infoTitulo = data.infoTitulo;
		$scope.infoValor = data.infoValor;
		$scope.infoItem = data.infoItem;
		$scope.infoDescricao = data.infoDescricao;
	});
	
	$scope.Alterar = function() {

		var Informacoes = {
			infoId : $routeParams.id,
			infoTitulo : $scope.infoTitulo,
			infoValor : $scope.infoValor,
			infoItem : $scope.infoItem,
			infoDescricao : $scope.infoDescricao
		}

			$http({
				method : "POST",
				url : '/AcessoRestrito/rest/informacao/alterar',
				data : Informacoes,
				cache : false
			}).success(function(data) {
				$location.path("/InformacaoConsulta");
				$('#showToastSucesso').click();
			});

		}
}