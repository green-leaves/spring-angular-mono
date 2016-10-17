angular.module('spring-gulp').factory('httpInterceptor', httpInterceptor);

function httpInterceptor($cookies) {
    var httpInterceptor  = {
        request: request
    }

    function request(config) {
        config.headers['auth-token'] = $cookies.get('token');
        return config;
    }

    return httpInterceptor;
}
