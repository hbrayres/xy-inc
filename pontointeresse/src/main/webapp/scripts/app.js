'use strict';

angular.module('pontointeresse',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/PontoInteresses',{templateUrl:'views/PontoInteresse/search.html',controller:'SearchPontoInteresseController'})
      .when('/PontoInteresses/new',{templateUrl:'views/PontoInteresse/detail.html',controller:'NewPontoInteresseController'})
      .when('/PontoInteresses/edit/:PontoInteresseId',{templateUrl:'views/PontoInteresse/detail.html',controller:'EditPontoInteresseController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
