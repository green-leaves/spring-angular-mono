angular.module('spring-gulp').run(function ($rootScope, $http, $window, Access, $location) {
    //$http.defaults.headers.common['auth-token'] = '6e77ed9e-9e68-4870-960b-ff4c448a9018';

    $rootScope.$on('$stateChangeStart', function (event) {
        //console.log(event);
    });

    $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
        console.log(error);
        //$window.location.href = '/app/login.html'
        console.log($window.location);
        if (error.status == Access.UNAUTHORIZED) {
            $window.location.href = $window.location.pathname + 'login.html';
        } else if (error.status == Access.FORBIDDEN) {
            $window.location.href = $window.location.pathname + 'login.html';
        }

    });
});