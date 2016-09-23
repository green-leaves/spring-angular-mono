
angular.module('spring-gulp', ['ui.router']);

angular.module('spring-gulp').config(function ($stateProvider) {
    $stateProvider
        .state({
            name: 'home',
            url: '/home',
            controller: 'HomeController',
            templateUrl: 'js/home/home.html'
        })
        .state({
            name: 'admin',
            url: '/admin',
            template: 'admin'
        });
});