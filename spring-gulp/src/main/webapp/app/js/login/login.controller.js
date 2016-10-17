angular.module('login').controller('LoginController', LoginController);

function LoginController($scope, loginService, $cookies, $window) {
    $scope.form = {
        username : '',
        password :  ''
    }

    $scope.submit = submit;

    function submit() {
        console.log($scope.form);
        loginService.login($scope.form).then(loginSuccess).catch(loginError);

        function loginSuccess(payload) {
            console.log(payload.data);
            $cookies.put('token', payload.data.token);
            $window.location.href = '/app/#/'
        }

        function loginError(error) {
            console.log(error);
        }
    }
}
