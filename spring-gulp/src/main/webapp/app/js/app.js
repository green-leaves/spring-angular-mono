
angular.module('spring-gulp', [
    'ui.router',
    'ngCookies'
]);

angular.module('spring-gulp').config(function ($stateProvider, $httpProvider, $urlRouterProvider ) {
    $httpProvider.interceptors.push('httpInterceptor');
    //$locationProvider.html5Mode(true);
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state({
            name: 'home',
            url: '/',
            controller: 'HomeController',
            templateUrl: 'js/home/home.html',
            resolve: {
                Access: ['Access', function (Access) {
                    return Access.isAuthenticated();
                }]
            }
        })

        .state({
            name: 'chart',
            url: '/chart',
            controller: 'ChartController',
            templateUrl: 'js/chart/chart.html',
            resolve: {
                Access: ['Access', function (Access) {
                    return Access.isAuthenticated();
                }]
            }
        })

        .state({
            name: 'admin',
            url: '/admin',
            template: 'admin',
            resolve: {
                Access: ['Access', function (Access) {
                    return Access.hasRole('ROLE_ADMIN');
                }]
            }
        });
});