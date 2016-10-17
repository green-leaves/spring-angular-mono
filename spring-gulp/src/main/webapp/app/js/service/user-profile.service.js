angular.module('spring-gulp').factory('UserProfile', UserProfile)

function UserProfile(Auth) {

    var userProfile = {};

    var fetchUserProfile = function () {
        return Auth.getProfile().then(function (response) {

            for (var prop in userProfile) {
                if (userProfile.hasOwnProperty(prop)) {
                    delete userProfile[prop];
                }
            }

            return angular.extend(userProfile, response.data, {

                $refresh: fetchUserProfile,

                $hasRole: function (role) {
                    return userProfile.roles.indexOf(role) >= 0;
                },

                $hasAnyRole: function (roles) {
                    return !!userProfile.roles.filter(function (role) {
                        return roles.indexOf(role) >= 0;
                    }).length;
                },

                $isAuthenticated: function () {
                    return userProfile.roles.length > 0;
                }

                //$isAnonymous: function () {
                //    return userProfile.anonymous;
                //},
                //
                //$isAuthenticated: function () {
                //    return !userProfile.anonymous;
                //}

            });

        });
    };

    return fetchUserProfile();
}