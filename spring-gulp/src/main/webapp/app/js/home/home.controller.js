angular.module('spring-gulp').controller('HomeController', HomeController);

function HomeController($location, $window) {
    console.log('Home Controller');
    //console.log($location.url());
    //$window.location.href = '/app/login.html';
};