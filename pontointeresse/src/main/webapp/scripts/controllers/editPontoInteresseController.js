

angular.module('pontointeresse').controller('EditPontoInteresseController', function($scope, $routeParams, $location, flash, PontoInteresseResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pontoInteresse = new PontoInteresseResource(self.original);
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'Ponto de interesso não encontrado.'});
            $location.path("/PontoInteresses");
        };
        PontoInteresseResource.get({PontoInteresseId:$routeParams.PontoInteresseId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.pontoInteresse);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'Ponto de interesse atualizado com sucesso.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Falha ao atualizar o registro.'}, true);
            }
        };
        $scope.pontoInteresse.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/PontoInteresses");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'Registro removido com sucesso.'});
            $location.path("/PontoInteresses");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Falha ao deletar registro.'}, true);
            }
        }; 
        $scope.pontoInteresse.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});