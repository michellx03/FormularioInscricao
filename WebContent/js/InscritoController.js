function InscritoConsultaController($scope, $http, $routeParams, $location) {

	$scope.ListaInscrito = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/inscrito/list',
		cache : false
	}).success(function(data) {

		$scope.ListaInscrito = data;
	});
	
	$scope.deleteInscrito = function(id) {
		//

		$http({
			method : "DELETE",
			url : '/AcessoRestrito/rest/inscrito/delete?id='+id+'',
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

function InscritoCadastroController($scope, $http,  $location) {

	$scope.Cadastrar = function(id) {

		var Inscrito = {
			inscNomeCompleto : $scope.inscNomeCompleto,
			inscRg : $scope.inscRg,
			inscCpf : $scope.inscCpf,
			inscTelefone : $scope.inscTelefone,
			inscCelular : $scope.inscCelular,
			inscCidade : $scope.inscCidade,
			inscEstado : $scope.inscEstado,
			inscPremId : $scope.inscPremio,
			inscEmail : $scope.inscEmail,
			inscStinId : 1,
			inscNumeroInscricao : $scope.inscNumeroInscricao,
			
		}

			$http({
				method : "POST",
				url : '/AcessoRestrito/rest/inscrito/Cadastrar',
				data : Inscrito,
				cache : false
			}).success(function(data) {
				$location.path("/InscritoConsulta");
				$('#showToastSucesso').click();
			});

	};

}

function InscritoAlteracaoController($scope, $http, $routeParams, $location) {

			$http({
				method : "GET",
				url : '/AcessoRestrito/rest/inscrito/alteracao?id='+$routeParams.id+'',
				cache : false
			}).success(function(data) {
				$scope.notiNoticia = data.notiNoticia;
				$scope.notiFoto = data.notiFoto;
				
				$scope.inscNomeCompleto = data.inscNomeCompleto;
				$scope.inscRg = data.inscRg;
				$scope.inscCpf = data.inscCpf;
				$scope.inscTelefone = data.inscTelefone ;
				$scope.inscCelular = data.inscCelular;
				$scope.inscCidade = data.inscCidade;
				$scope.inscEstado = data.inscEstado;
				$scope.inscPremio = data.inscPremio;
				$scope.inscEmail = data.inscEmail;
				$scope.inscStatus = data.inscStatus;
				$scope.inscNumeroInscricao = data.inscNumeroInscricao;
			});
			
			$scope.Alterar = function() {

				var Inscrito = {
					inscId : $routeParams.id,
					inscNomeCompleto : $scope.inscNomeCompleto,
					inscRg : $scope.inscRg,
					inscCpf : $scope.inscCpf,
					inscTelefone : $scope.inscTelefone,
					inscCelular : $scope.inscCelular,
					inscCidade : $scope.inscCidade,
					inscEstado : $scope.inscEstado,
					inscPremId : $scope.inscPremio,
					inscEmail : $scope.inscEmail,
					inscStinId : $scope.inscStatus,
					inscNumeroInscricao : $scope.inscNumeroInscricao,
				}

					$http({
						method : "POST",
						url : '/AcessoRestrito/rest/inscrito/alterar',
						data : Inscrito,
						cache : false
					}).success(function(data) {
						$location.path("/InscritoConsulta");
						$('#showToastSucesso').click();
					});

				}
}