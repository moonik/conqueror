angular.module('conquerorApp').controller('LoginCtrl', function ($scope, $rootScope, $http, $window, close) {
     $scope.userForm = {};
     $rootScope.user = {};
     $scope.castleName = {};
     $scope.userCastle = false;

     $scope.signIn = function () {
        $http.post('api/login', $scope.userForm).then(function (response) {
            localStorage.setItem('jwt', response.headers()['x-auth-token']);
            $scope.getUser();
            console.log('signed in');
            closeModal(undefined);
        })
        $scope.userForm = {};
     };

     $rootScope.getUser = function () {
        $http.get('api/users/me').then(function (response) {
            $rootScope.user = response.data;
            $rootScope.isSignedIn = true;
        }, function(response) {
            localStorage.removeItem('jwt');
            $rootScope.isSignedIn = false;
        })
     };

     $scope.close = function () {
        closeModal(undefined);
     };

     function closeModal(data) {
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        close(data, 500);
     }

});