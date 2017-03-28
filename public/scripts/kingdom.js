angular.module('conquerorApp').controller('KingdomCtrl', function ($scope, $rootScope, ModalService, $http, $window) {
    $scope.myCastles = [];
    $scope.myRes = {};

    $http.get('api/userresources/getResources', $scope.myRes).then(function(data){
        $scope.myRes = data.data;
    })

    $http.get('api/castle/myCastles', $scope.myCastles).then(function(data){
        $scope.myCastles = data.data
    })

});