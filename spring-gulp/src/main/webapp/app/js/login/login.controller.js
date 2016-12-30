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
            console.log($window.location);
            $cookies.put('token', payload.data.token);
            $window.location.href = $window.location.pathname.replace("login.html", "");

        }

        function loginError(error) {
            console.log(error);
        }
    }
}
