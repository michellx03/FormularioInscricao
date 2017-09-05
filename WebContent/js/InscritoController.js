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
			inscPremId : $("#premio option:selected").val(),
			inscEmail : $scope.inscEmail,
			inscStinId : $("#status option:selected").val(),
			inscNumeroInscricao : $scope.inscNumeroInscricao,
			inscEstudante : $("input[name=cartao]").val(),
			
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
	
	$scope.ListaPremio = {};
	$scope.Status = {};
	$scope.Inscrito;
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/premio/list',
		//cache : false
	}).success(function(data) {
		$scope.ListaPremio = data;
	});
	
	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/status/list',
		//cache : false
	}).success(function(data) {
		$scope.Status = data;
		
	
	});

			$http({
				method : "GET",
				url : '/AcessoRestrito/rest/inscrito/obterInscritos?id='+$routeParams.id+'',
				cache : false
			}).success(function(data) {
				$scope.Inscrito = data;
				$scope.inscNomeCompleto = data.inscNomeCompleto;
				$scope.inscRg = data.inscRg;
				$scope.inscCpf = data.inscCpf;
				$scope.inscTelefone = data.inscTelefone ;
				$scope.inscCelular = data.inscCelular;
				$scope.inscCidade = data.inscCidade;
				$scope.inscEstado = data.inscEstado;
				
				var premio = data.premPremio == null?"Selecione uma opcao":data.premPremio;
				$('#premio').append(
						'<option selected="true" value="' + data.inscPremId
								+ '">' + premio + ' </option> ');
				
				$scope.inscEmail = data.inscEmail;
				
				var status = data.stinStatus == null?"Selecione uma opcao":data.stinStatus;
				$('#status').append(
						'<option selected="true" value="' + data.inscStinId
								+ '">' + status + ' </option> ');
				
				$scope.inscNumeroInscricao = data.inscNumeroInscricao;
				$scope.inscEstudante = $("input[value='"+data.stinEstudante+"']").prop("checked", true);
				
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
					inscEmail : $scope.inscEmail,
					inscNumeroInscricao : $scope.inscNumeroInscricao,
					inscPremId :$("#premio option:selected").val() == null?"":$("#premio option:selected").val(),
					inscStinId : $("#status option:selected").val() == null?"":$("#status option:selected").val(),
					inscEstudante : $("input[name=cartao]").val(),
					
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