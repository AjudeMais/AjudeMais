/**
 * @ngdoc Controller
 * @name Login Controller
 *
 * @description Controller para pagina de login
 *
 * @author <a href="https://franckaj.github.io/">Franck Aragão</a>
 */
(function () {
    angular.module('amApp').controller('LoginController', LoginController);

    LoginController.$inject = ['authenticationService', '$state'];

    function LoginController(authenticationService, $state) {
        var vm = this;
        vm.account = {};

        /**
         *
         */
        vm.doLogin = function () {
            authenticationService.doLogin(vm.account, function (response) {
                authenticationService.storageToken(response.token);
                authenticationService.getUserLogged(function () {
                    redirect();
                });
            }, function (response) {
                if (response.status == 401) {
                    vm.error = "Nome de usuário ou senha inválido";
                }
                else {
                    vm.error = response.msg;
                }
            });
        };

        function redirect() {
            if (isFirstAccess()) {
                $state.go("home");

            } else {
                $state.go("home");
            }
        }

        /**
         *
         */
        function isFirstAccess() {
            var user = sessionStorage.sessionUser;
            if (user && user.ultimoAcesso) {
                return false;
            }
            else {
                return true;
            }
        }
    };
})();
