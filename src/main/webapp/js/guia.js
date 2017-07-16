var app = angular.module("guiaDeSeries", ['ui.router', 'ngMaterial']);

app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

  $urlRouterProvider.otherwise('/home');

  $stateProvider
    .state('main', {
      url: '',
      abstract:true,
      templateUrl: '<div ui-view></div>',
      controller:'guiaController'
        })

    .state( 'main.home',{
        url: '/home',
        templateUrl: 'home.html'
    })



    // Utilizando o HTML5 History API
    $locationProvider.html5Mode(true);
});