angular.module('spring-gulp').factory('Access', Access);

function Access($q, UserProfile) {
    var Access = {
        OK: 200,

        // "we don't know who you are, so we can't say if you're authorized to access
        // this resource or not yet, please sign in first"
        UNAUTHORIZED: 401,

        // "we know who you are, and your profile does not allow you to access this resource"
        FORBIDDEN: 403,

        isAuthenticated: isAuthenticated,
        hasRole: hasRole
    };

    function isAuthenticated() {
        console.log('isAuthenticated?');
        return UserProfile.then(function (userProfile) {
            if (userProfile.$isAuthenticated()) {
                console.log('Yes');
                return Access.OK;
            } else {
                console.log('No');
                return $q.reject(Access.FORBIDDEN);
            }
        });
    }

    function hasRole(role) {
        return UserProfile.then(function (userProfile) {
            if (userProfile.$hasRole(role)) {
                console.log('Yes');
                return Access.OK;
            } else {
                console.log('No');
                return $q.reject(Access.UNAUTHORIZED);
            }
        });
    }

    return Access;
}
