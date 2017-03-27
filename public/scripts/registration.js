angular.module('conquerorApp').controller('RegistrationCtrl', function ($scope, $rootScope, $http, $window, close) {
     $scope.userForm = {};
     $rootScope.user = {};
     $scope.castleName = {};
     $rootScope.userCastle = false;

     $scope.signUp = function () {
        $http.post('api/users', $scope.userForm).then(function (response) {
            console.log('signed up');
            $rootScope.isSignedUp = true;
            },function(response){
                $rootScope.isSignedUp = false;
                });
        $scope.userForm = {};
        };

     $scope.signIn = function () {
        $http.post('api/login', $scope.userForm).then(function (response) {
            localStorage.setItem('jwt', response.headers()['x-auth-token']);
            $scope.getUser();
            console.log('signed in');
            $http.get('api/castle/checkCastle').then(function (response) {
                $rootScope.userCastle = true;
                },function(response){
                    $rootScope.userCastle = false;
                    closeModal(undefined);
                    });
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
     $scope.castleCreation = function(){
        var fd = new FormData();
        fd.append('name', $scope.castleName.name);
        $http.post('api/castle/create', fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
            }).then(function(response){
                closeModal(undefined);
                })
        };

     });