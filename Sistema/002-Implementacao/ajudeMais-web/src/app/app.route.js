/**
 * @ngdoc Route
 * @name amRoute
 *
 * @description mapeamento de rotas usando ui-router
 *
 * @author <a href="https://franckaj.github.io/">Franck Aragão</a>
 */

(function () {
    var app = angular.module('amRoute', ['ui.router']);

    app.config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/login");

        $stateProvider.state('login', {
            url: '/login',
            templateUrl: 'app/components/auth/login.html',
            controller: 'LoginController',
            controllerAs: 'vm',
            data: {
                pageTitle: 'Login'
            }
        })

            .state('passwdChange', {
                url: '/passwdChange',
                templateUrl: 'app/components/auth/password/password.html',
                controller: "PasswordController",
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Primeiro Acesso'
                }
            })

            .state('home.403', {
                url: '/403',
                templateUrl: 'app/components/auth/403/403.html',
                data: {
                    pageTitle: 'Acesso Negado'
                }
            })

            .state('home.500', {
                url: '/500',
                templateUrl: 'app/components/auth/500/500.html',
                data: {
                    pageTitle: 'Erro no Servidor'
                }
            })
            .state('home', {
                url: '/home',
                templateUrl: 'app/layouts/layout.html',
                data: {
                    pageTitle: 'Home'
                }
            })

            .state('home.instituicao', {
                url: "/instituicao",
                templateUrl: "app/components/instituicao/instituicoes.html",
                controller: "InstituicaoController",
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Instituições'
                }
            })
            .state('home.instituicaoEdit', {
                url: "/instituicaoEdit",
                templateUrl: "app/components/instituicao/instituicao.edit.html",
                controller: "InstituicaoEditController",
                controllerAs: 'vm',
                params: {
                    instituicaoEdit: null
                },
                data: {
                    pageTitle: 'Edição de Instituição'
                }
            })
            .state('home.instituicaoDetail', {
                url: "/instituicaoDetail/:instituicaoDetail",
                templateUrl: "app/components/instituicao/instituicao.detail.html",
                controller: "InstituicaoDetailController",
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Detalhes de Instituição'
                }
            })
            .state('home.categoria', {
                url: "/categoria",
                templateUrl: "app/components/categoria/categorias.html",
                controller: "CategoriaController",
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Itens Doáveis'
                }
            })
            .state('home.mensageirosAss', {
                url: "/mensageirosAss",
                templateUrl: "app/components/mensageiroAssociado/mensageiros-associados.html",
                controller: "MensageiroAssociadoController",
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Mensageiros Associados'
                }
            })
            .state('home.mensageiroAssEdit', {
                url: "/mensageiroAssEdit",
                templateUrl: "app/components/mensageiroAssociado/mensageiro-associado.edit.html",
                controller: "MensageiroAssociadoEditController",
                controllerAs: 'vm',
                params: {
                    mensageiroAssEdit: null
                },
                data: {
                    pageTitle: 'Edição de Mensageiro Associado'
                }
            });
    });
})();
