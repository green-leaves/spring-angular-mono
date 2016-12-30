angular.module('login').factory('loginService', loginService);

function loginService($http) {
    var loginService = {
        login: login,
    }

    function login(request) {
        return $http({
            method: 'POST',
            url: '/api/login',
            data: request
        });
    }

    return loginService;
}
