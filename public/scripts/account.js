angular.module('conquerorApp').controller('MainCtrl', function ($scope, $rootScope, ModalService, $http, $window) {
    $scope.userForm = {};
    $rootScope.user = {};

    $rootScope.getUser = function () {
        $http.get('api/users/me').then(function (response) {
            $rootScope.user = response.data;
            $rootScope.isSignedIn = true;
        }, function(response) {
            localStorage.removeItem('jwt');
            $rootScope.isSignedIn = false;
        })
    };

    $scope.signUp = function () {
        $http.post('api/users', $scope.userForm).then(function (response) {
         $window.location.href = "#/login"
            console.log('signed up');
            alert("Registration completed, you can log in now");
        });
    };

    $scope.signIn = function () {
        $http.post('api/login', $scope.userForm).then(function (response) {
            localStorage.setItem('jwt', response.headers()['x-auth-token']);
            $scope.getUser();
            console.log('signed in');
            $window.location.href = "#/home"
        })
         $scope.userForm = {};
    };

    $scope.signOut = function () {
        $rootScope.isSignedIn = false;
        localStorage.removeItem('jwt');
        console.log('signed out');
    };

    $scope.getUser();

        $rootScope.search = function(search)
        {
             if (angular.isDefined(search) && search !== "") {
                         $http.get('api/users/search/' + search).then(function (response) {
                         $scope.searchUser = response.data;
                         $rootScope.showBttn = true;
                       },
                       function(response){
                       $rootScope.showBttn = false;
                       alert("User not found");
                       });
                       };
        };

        $rootScope.registration = function(){
         ModalService.showModal({
         templateUrl: 'registration.html',
         controller: 'RegistrationCtrl',
         }).then(function(modal) {
          modal.element.modal();
           });
            };

              $rootScope.login = function(){
                     ModalService.showModal({
                     templateUrl: 'enter.html',
                     controller: 'LoginCtrl',
                     }).then(function(modal) {
                      modal.element.modal();
                       });
                        };

});