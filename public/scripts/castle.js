angular.module('conquerorApp').controller('CastleCtrl', function ($scope, $rootScope, ModalService, $http, $window, $routeParams) {
$scope.myCastle = {};
$scope.castleId = $routeParams['id'];
$scope.myCastle = {};
$scope.castlerRes = {};
    $http.get('api/castle/castle/' + $scope.castleId, $scope.myCastle).then(function(data){
        $scope.myCastle = data.data;
    })

    $http.get('api/userresources/getGold/' + $scope.castleId, $scope.castlerRes).then(function(data){
        $scope.castlerRes = data.data;
    })

    $scope.sendGold = function()
    {
        ModalService.showModal({
        templateUrl: 'sendGold.html',
        controller: 'MailsCtrl',
        inputs: {
            castleId: $scope.myCastle.id
            }
            }).then(function(modal) {
            modal.element.modal();
            });
        };

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