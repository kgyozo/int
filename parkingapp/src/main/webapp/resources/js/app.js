var app = angular.module('ParkingApp', []);

app.controller('ParkingAppController', function ($scope, $http) {

    $scope.customertype = 'private';
    $scope.fromDate = new Date('2010-01-01');
    $scope.toDate = new Date();

    $scope.reset = function() {
        $scope.data = {};
    }

    $scope.search = function() {
      $http.get('parkingapp/search', { params: {fromDate : $scope.fromDate, toDate : $scope.toDate, customer : $scope.customertype}})
        .success(function(response) {
            $scope.data = response;
            console.log($scope.data);
        }).
        error(function(data, status, headers, config) {
            console.debug('Error - data: ', data);
            console.debug('Error: - config', config);
            console.debug(JSON.parse(JSON.stringify(data)));
        });;
    };
});

app.controller('ExampleController', ['$scope', function($scope) {
    $scope.customer = {
            type: 'company'
    };
}]);