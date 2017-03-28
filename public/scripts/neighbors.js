angular.module('conquerorApp').controller('NeighborsCtrl', function ($scope, $rootScope, ModalService, $http, $window, castleId) {
    $scope.nearestCastles = [];

    $http.get('api/castle/nearestCastles/' + castleId).then(function(data){
        $scope.nearestCastles = data.data;
    })

    $scope.close = function () {
        closeModal(undefined);
    };

    function closeModal(data) {
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
        close(data, 500);
    }

});