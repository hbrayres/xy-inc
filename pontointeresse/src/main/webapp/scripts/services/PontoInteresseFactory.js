angular.module('pontointeresse').factory('PontoInteresseResource', function($resource){
    var resource = $resource('api/pontointeresses/:PontoInteresseId',{PontoInteresseId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});