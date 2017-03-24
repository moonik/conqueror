angular.module('conquerorApp').controller('CastleCtrl', function ($scope, $rootScope, ModalService, $http, $window) {
$scope.myCastle = {};

    $http.get('api/castle/myCastle', $scope.myCastle).then(function(data){
        $scope.myCastle = data.data;
    })

    $scope.getNearest = function(){
     ModalService.showModal({
     templateUrl: 'neighbors.html',
     controller: 'NeighborsCtrl',
     inputs: {
     castleId: $scope.myCastle.id
     }
     }).then(function(modal) {
     modal.element.modal();
     });
    };

    $scope.getShopItems = function(){
        ModalService.showModal({
        templateUrl: 'shop.html',
        controller: 'ShopCtrl',
        inputs: {
             castleId: $scope.myCastle.id
             }
        }).then(function(modal) {
        modal.element.modal();
        });
        };

});