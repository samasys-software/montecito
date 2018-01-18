'use strict';

// declare modules
angular.module('Authentication', []);
angular.module('Home', ["ng-fusioncharts", "ngStomp"]);
angular.module('Registration', []);

angular.module('BasicHttpAuthExample', [
    'Authentication',
    'Home','Registration',
    'ngRoute',
    'ngCookies'
])
 
.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })
 
        .when('/api/tasks/user', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html'
        })
        .when('/registration',{
        controller: 'RegistrationController',
        templateUrl: 'modules/registration/views/registration.html'
    })
 
        .otherwise({ redirectTo: '/login' });
}])
 
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser && $location.path() !== '/registration') {
                $location.path('/login');
            }
        });
    }]);