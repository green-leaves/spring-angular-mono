angular.module('spring-gulp').controller('AppController', AppController);

function AppController($scope, $cookies, $window, UserProfile) {
    $scope.logOut = logOut;
    $scope.isAuthenticated = false;

    init();
    /////////////////////////////////

    function init() {
        UserProfile.then(function(userProfile) {
            console.log(userProfile);
            $scope.username = userProfile.username;
            $scope.isAuthenticated = true;
        });
    }

    function logOut() {
        $cookies.remove("token");
        console.log($window.location);
        $window.location.href = $window.location.pathname + 'login.html';

    }
}