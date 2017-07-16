var app = angular.module("guiaDeSeries", ['ui.router']);

app.config(function($stateProvider, $urlRouterProvider, $locationProvider) {

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('main', {
      url: '',
      abstract:true,
      templateUrl: '<div ui-view></div>',
      controller:'guiaDeSeries',
        })

    .state( 'main.home',{
        url: '/home',
        templateUrl: 'home.html'
    })



    // Utilizando o HTML5 History API
    $locationProvider.html5Mode(true);
});