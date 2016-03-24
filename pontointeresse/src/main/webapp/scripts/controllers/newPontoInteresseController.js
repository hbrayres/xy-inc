
angular.module('pontointeresse').controller('NewPontoInteresseController', function ($scope, $location, locationParser, flash, PontoInteresseResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pontoInteresse = $scope.pontoInteresse || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'Ponto de interesse criado com Sucesso.'});
            $location.path('/PontoInteresses');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Falha ao salvar o registro.'}, true);
            }
        };
        PontoInteresseResource.save($scope.pontoInteresse, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/PontoInteresses");
    };
});