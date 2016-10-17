angular.module('spring-gulp').factory('Auth', Auth);

function Auth($http) {
    var Auth = {
        getProfile: getProfile,
        login: login
    };

    return Auth;

    //////////////////////////////

    function getProfile() {
        return $http({
            method: 'GET',
            url: '/api/user/auth'
        })
    }

    function login(request) {
        return $http({
            method: 'POST',
            url: '/login',
            data: request
        });
    }


}
