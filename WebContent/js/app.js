angular
		.module(
				'exampleApp',
				[ 'ngRoute', 'ngCookies', 'exampleApp.services', 'oc.lazyLoad' ])
		.config(
				[
						'$routeProvider',
						'$locationProvider',
						'$httpProvider',

						function($routeProvider, $locationProvider,
								$httpProvider) {

							$routeProvider.when('/UsuarioConsulta', {
								templateUrl : 'partials/Usuario/Consulta.html',
								controller : UsuarioConsultaController,

							});
							$routeProvider.when('/InformacaoConsulta', {
								templateUrl : 'partials/Informacao/Consulta.html',
								controller : InformacaoConsultaController,

							});							
							$routeProvider.when('/NoticiaConsulta', {
								templateUrl : 'partials/Noticia/Consulta.html',
								controller : NoticiaConsultaController,

							});
							$routeProvider.when('/StatusConsulta', {
								templateUrl : 'partials/Status/Consulta.html',
								controller : StatusConsultaController,

							});
							$routeProvider.when('/StatusPremioConsulta', {
								templateUrl : 'partials/StatusPremio/Consulta.html',
								controller : StatusPremioConsultaController,

							});
							$routeProvider.when('/InscritoConsulta', {
								templateUrl : 'partials/Inscrito/Consulta.html',
								controller : InscritoConsultaController,

							});
							$routeProvider.when('/PremioConsulta', {
								templateUrl : 'partials/Premio/Consulta.html',
								controller : PremioConsultaController,

							});
							$routeProvider
							.when(
									'/NoticiaAlteracao/:id',
									{
										templateUrl : 'partials/Noticia/Alteracao.html',
										controller : NoticiaAlteracaoController,

									});
							$routeProvider
							.when(
									'/InscritoAlteracao/:id',
									{
										templateUrl : 'partials/Inscrito/Alteracao.html',
										controller : InscritoAlteracaoController,

									});
							$routeProvider
							.when(
									'/InformacaoAlteracao/:id',
									{
										templateUrl : 'partials/Informacao/Alteracao.html',
										controller : InformacaoAlteracaoController,

									});
							$routeProvider
							.when(
									'/PremioAlteracao/:id',
									{
										templateUrl : 'partials/Premio/Alteracao.html',
										controller : PremioAlteracaoController,

									});
							$routeProvider
									.when(
											'/UsuarioAlteracao/:id',
											{
												templateUrl : 'partials/Usuario/Alteracao.html',
												controller : UsuarioAlteracaoController,

											});

							$routeProvider.when('/UsuarioCadastro', {
								templateUrl : 'partials/Usuario/Cadastro.html',
								controller : UsuarioCadastroController,

							});
							$routeProvider.when('/NoticiaCadastro', {
								templateUrl : 'partials/Noticia/Cadastro.html',
								controller : NoticiaCadastroController,

							});
							$routeProvider.when('/InscritoCadastro', {
								templateUrl : 'partials/Inscrito/Cadastro.html',
								controller : InscritoCadastroController,

							});
							$routeProvider.when('/PremioCadastro', {
								templateUrl : 'partials/Premio/Cadastro.html',
								controller : PremioCadastroController,

							});
							$routeProvider
									.when(
											'/ControleAcessoConsulta',
											{
												templateUrl : 'partials/ControleAcesso/Consulta.html',
												controller : AcessoConsultaController,

											});
							$routeProvider
									.when(
											'/ControleAcessoAlteracao/:id',
											{
												templateUrl : 'partials/ControleAcesso/Alteracao.html',
												controller : ControleAcessoAlteracaoController,

											});

							$routeProvider.when('/login', {
								templateUrl : 'partials/login/formLogin.html',
								controller : LoginController
							});

							$routeProvider
									.when('/Dashboard', {
												template : '<div></div>',
												controller : DashboardController
											});

							$routeProvider.when('/DashboardIndex', {
								template : '<div></div>',
								controller : DashboardIndexController
							});

							$routeProvider.otherwise({
								template : '<div></div>',
								controller : IndexController
							});

							$locationProvider.hashPrefix('!');

							$httpProvider.interceptors
									.push(function($q, $rootScope, $location) {
										return {
											'responseError' : function(
													rejection) {
												var status = rejection.status;
												var config = rejection.config;
												var method = config.method;
												var url = config.url;

												if (status == 401) {

													window.location.href = '/AcessoRestrito/login.html#!/login'

												} else {
													$rootScope.error = method
															+ " on "
															+ url
															+ " failed with status "
															+ status;

												}
												return $q.reject(rejection);
											}
										};
									});

							$httpProvider.interceptors
									.push(function($q, $rootScope, $location) {
										return {
											'request' : function(config) {
												var isRestCall = config.url
														.indexOf('/AcessoRestrito/rest') == 0;
												if (isRestCall
														&& angular
																.isDefined($rootScope.accessToken)) {
													var accessToken = $rootScope.accessToken;
													if (exampleAppConfig.useAccessTokenHeader) {
														config.headers['X-Access-Token'] = accessToken;
													} else {
														config.url = config.url
																+ "?token="
																+ accessToken;
													}
												}
												return config
														|| $q.when(config);
											}
										};
									});

						} ]

		).run(function($rootScope, $location, $cookieStore, UserService) {

			$rootScope.$on('$viewContentLoaded', function() {
				delete $rootScope.error;
			});

			$rootScope.hasRole = function(role) {

				if ($rootScope.user === undefined) {
					return false;
				}

				if ($rootScope.user.roles[role] === undefined) {
					return false;
				}

				return $rootScope.user.roles[role];
			};

			$rootScope.logout = function() {
				delete $rootScope.user;
				delete $rootScope.accessToken;
				$cookieStore.remove('accessToken');
				window.location.href = '/AcessoRestrito/login.html#!/login'

			};

			var originalPath = $location.path();

			var accessToken = $cookieStore.get('accessToken');

			if (accessToken !== undefined) {
				$rootScope.accessToken = accessToken;
				UserService.get(function(user) {
					$rootScope.user = user;
					$location.path(originalPath);
				});
			} else {
				window.location.href = '/AcessoRestrito/login.html#!/login'

			}

			$rootScope.initialized = true;
		});

function DashboardIndexController($scope, $cookieStore) {
	window.location.href = '/AcessoRestrito/index.html#!/Dashboard'
}

function DashboardController($scope) {

}

function IndexController($scope, $cookieStore) {

}
