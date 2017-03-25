angular.module('conquerorApp').controller('ShopCtrl', function ($scope, $rootScope, $http, $window, close, castleId) {

$scope.items = [];

$http.get('api/shop/get', $scope.items).then(function(data){
    $scope.items = data.data;
    })

$scope.buy = function(amount, warrior)
{
    var fd = new FormData();
    fd.append('amount', amount);
    fd.append('warrior', warrior);
    $http.post('api/shop/buy/' + castleId, fd, {
    transformRequest: angular.identity,
    headers: {'Content-Type': undefined}
    }).then(function(data){
        alert("You bought" + ": " + amount + " "+ warrior);
    },function(response){
        alert("Not enought gold!");
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