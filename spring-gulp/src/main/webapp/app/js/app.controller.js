angular.module('spring-gulp').controller('AppController', AppController);

function AppController($scope, $cookies, $window, UserProfile) {
    $scope.logOut = logOut;

    init();
    /////////////////////////////////

    function init() {
        UserProfile.then(function(userProfile) {
            console.log(userProfile);
            $scope.username = userProfile.username;
        });
    }

    function logOut() {
        $cookies.remove("token");
        $window.location.href = '/app/login.html'

    }
}