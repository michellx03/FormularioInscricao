function InscritoSiteCadastroController($scope, $http,  $location) {
	$scope.ListaPremio = {};
	$scope.Status = {};
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/premio/list',
		cache : false
	}).success(function(data) {
		$scope.ListaPremio = data;
	});
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/status/list',
		cache : false
	}).success(function(data) {
		$scope.Status = data;
	});

	$scope.Cadastrar = function(id) {

		var Inscrito = {
			inscNomeCompleto : $scope.inscNomeCompleto,
			inscRg : $scope.inscRg,
			inscCpf : $scope.inscCpf,
			inscTelefone : $scope.inscTelefone,
			inscCelular : $scope.inscCelular,
			inscCidade : $scope.inscCidade,
			inscEstado : $scope.inscEstado,
			inscPremId : 0,
			inscEmail : $scope.inscEmail,
			inscStinId : 1,
			inscNumeroInscricao : Math.random(),
			inscEstudante : $("input[name=cartao]").val(),
			
		}

			$http({
				method : "POST",
				url : '/AcessoRestrito/rest/inscrito/Cadastrar',
				data : Inscrito,
				cache : false
			}).success(function(data) {
				$location.path("/Confirmada");
				$('#showToastSucesso').click();
			});

	};

}
