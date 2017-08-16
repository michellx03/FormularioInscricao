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
	$scope.Status = {};
	$scope.ListaPremio = {};
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/statusPremio/list',
		cache : false
	}).success(function(data) {
		$scope.Status = data;
	});

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/premio/list',
		cache : false
	}).success(function(data) {
		$scope.ListaPremio = data;
	});
		
	$scope.Cadastrar = function(id) {

		var Premio = {
			premPremio : $scope.premPremio,
			premStatus : $("#status option:selected").val(),
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
	$scope.Status = {};	
	$scope.ListaPremio = {};
	$scope.Premio;
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/statusPremio/list',
		cache : false
	}).success(function(data) {
		$scope.Status = data;
	});
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/premio/list',
		cache : false
	}).success(function(data) {
		$scope.ListaPremio = data;
	});
	
			$http({
				method : "GET",
				url : '/AcessoRestrito/rest/premio/obterPremios?id='+$routeParams.id+'',
				cache : false
			}).success(function(data) {
				$scope.Premio = data;
				$scope.premPremio = data.premPremio;
				
				var status = data.stprStatus == null?"Selecione uma opcao":data.stprStatus;
				$('#status').append(
						'<option selected="true" value="' + data.premStatus
								+ '">' + status + ' </option> ');
				
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