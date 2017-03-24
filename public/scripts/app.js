var gameProject = angular.module('conquerorApp', ['ngRoute', 'angularModalService', 'angular.filter'])

gameProject.config(function($routeProvider, $httpProvider){

$routeProvider.when('/registration', {
    templateUrl: 'registration.html',
    controller: 'MainCtrl'
}).when('/my', {
    templateUrl: 'MyCastle.html',
    controller: 'CastleCtrl'
});

$httpProvider.interceptors.push('httpRequestInterceptor');
});

//testProject.run(function ($rootScope, $location, ModalService, $http) {
//    $rootScope.me = {};
//    $rootScope.isSignedIn = false;
//
//    getUser();
//
//    $rootScope.signUp = function () {
//        ModalService.showModal({
//            templateUrl: 'signUpModal.html',
//            controller: 'SignUpModalCtrl'
//        }).then(function (modal) {
//            modal.element.modal();
//            modal.close.then(function (result) {
//                getUser();
//            });
//        });
//    };
//
//    $rootScope.logIn = function () {
//        ModalService.showModal({
//            templateUrl: 'logInModal.html',
//            controller: 'LogInModalCtrl'
//        }).then(function (modal) {
//            modal.element.modal();
//            modal.close.then(function (result) {
//                getUser();
//            });
//        });
//    };
//
//    $rootScope.logOut = function () {
//        $rootScope.isSignedIn = false;
//        localStorage.removeItem('jwt');
//    };
//
//    function getUser() {
//        if (localStorage.getItem('jwt') === null) {
//            return;
//        }
//        $http.get('api/users/me').then(function (response) {
//            $rootScope.me = response.data;
//            $rootScope.isSignedIn = true;
//        }, function (response) {
//            localStorage.removeItem('jwt');
//            $rootScope.isSignedIn = false;
//        })
//    }
//});

gameProject.factory('httpRequestInterceptor', function () {
    return {
        request: function (config) {
            var token = localStorage.getItem('jwt');
            if(token === null) {
                token = '';
            }
            config.headers['x-auth-token'] = token;
            return config;
        }
    };
});