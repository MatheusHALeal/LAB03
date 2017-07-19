const app = angular.module("guiaDeSeries", ['ui.router', 'ngMaterial']);

app.config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise('/login');

  $stateProvider
    .state('main', {
      url: '',
      abstract: true,
      template: '<div ui-view></div>',
      controller:'guiaDeSeriesCtrl'
        })

    .state( 'main.login',{
        url: '/login',
        templateUrl: 'login.html',
    })

    .state('home', {
      url:'/home',
      templateUrl: 'home.html',
    });




});

