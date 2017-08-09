function PremioConsultaController($scope, $http, $routeParams, $location) {
	$scope.ListaPremio = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/premio/list',
		cache : false
	}).success(function(data) {
		$scope.ListaPremio = data;
	});
	
	$scope.deletePremio = function(id) {
		//

		$http({
			method : "DELETE",
			url : '/AcessoRestrito/rest/premio/delete?id='+id+'',
			cache : false
		}).success(function(data) {
			$('#showToastSucesso').click();
			var setFunctionInTime = setTimeout(
					FuncaoExecutaDepoisDoTempo, 1000);
			function FuncaoExecutaDepoisDoTempo() {
				location.reload();
			}
		})
	};

}

function PremioCadastroController($scope, $http,  $location) {

	$scope.Cadastrar = function(id) {

		var Premio = {
			premPremio : $scope.premPremio,
		}

			$http({
				method : "POST",
				url : '/AcessoRestrito/rest/premio/Cadastrar',
				data : Premio,
				cache : false
			}).success(function(data) {
				$location.path("/PremioConsulta");
				$('#showToastSucesso').click();
			});

	};

}

function PremioAlteracaoController($scope, $http, $routeParams, $location) {
	//alert($routeParams.id);

			$http({
				method : "GET",
				url : '/AcessoRestrito/rest/premio/alteracao?id='+$routeParams.id+'',
				cache : false
			}).success(function(data) {
				$scope.premPremio = data.premPremio;
				
			});
			
			$scope.Alterar = function() {

				var Premio = {
					premId : $routeParams.id,
					premPremio : $scope.premPremio
				}

					$http({
						method : "POST",
						url : '/AcessoRestrito/rest/premio/alterar',
						data : Premio,
						cache : false
					}).success(function(data) {
						$location.path("/PremioConsulta");
						$('#showToastSucesso').click();
					});

				}
}