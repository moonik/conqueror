angular.module('conquerorApp').controller('MailsCtrl', function ($scope, $rootScope, ModalService, $http, $window, castleId) {
    $scope.id = castleId;

    $scope.sendGold = function(receiver,amount)
    {
        var fd = new FormData();
        fd.append('amount', amount);
        fd.append('idSender', $scope.id);
        console.log($scope.castleId);
        console.log(receiver);
        $http.post('api/userresources/sendGold/' + receiver, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
            }).then(function(data){
                alert(amount + "gold was sent");
                },function(response){
                    alert("Not enought gold!");
                    })
        };

    });