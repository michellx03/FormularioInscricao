function NoticiaConsultaController($scope, $http, $routeParams, $location) {

	$scope.ListaNoticia = {};

	$http({
		method : "GET",
		url : '/AcessoRestrito/rest/noticia/list',
		cache : false
	}).success(function(data) {
		//alert("Sucess")
		$scope.ListaNoticia = data;
	});
	
	$scope.deleteNoticia = function(id) {
		//

		$http({
			method : "DELETE",
			url : '/AcessoRestrito/rest/noticia/delete?id='+id+'',
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

function NoticiaCadastroController($scope, $http,  $location) {

	$scope.Cadastrar = function(id) {

		var Noticia = {
			notiNoticia : $scope.notiNoticia,
			notiFoto : $scope.notiFoto,
		}

			$http({
				method : "POST",
				url : '/AcessoRestrito/rest/noticia/Cadastrar',
				data : Noticia,
				cache : false
			}).success(function(data) {
				$location.path("/NoticiaConsulta");
				$('#showToastSucesso').click();
			});

	};

}

function NoticiaAlteracaoController($scope, $http, $routeParams, $location) {
	//alert($routeParams.id);

			$http({
				method : "GET",
				url : '/AcessoRestrito/rest/noticia/alteracao?id='+$routeParams.id+'',
				cache : false
			}).success(function(data) {
				$scope.notiNoticia = data.notiNoticia;
				$scope.notiFoto = data.notiFoto;
			});
			
			$scope.Alterar = function() {

				var Noticia = {
					notiId : $routeParams.id,
					notiNoticia : $scope.notiNoticia,
					notiFoto : $scope.notiFoto
				}

					$http({
						method : "POST",
						url : '/AcessoRestrito/rest/noticia/alterar',
						data : Noticia,
						cache : false
					}).success(function(data) {
						$location.path("/NoticiaConsulta");
						$('#showToastSucesso').click();
					});

				}
}