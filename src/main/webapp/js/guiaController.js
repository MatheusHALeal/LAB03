angular.module('guiaDeSeries').controller('guiaDeSeriesCtrl', function ($scope, $mdDialog, $http, $timeout, $mdSidenav, $log) {
  $scope.toggleLeft = buildDelayedToggler('left');
  $scope.serie  ="";
  $scope.catalogo = [];
  $scope.minhasSeries = [];
  $scope.exibicao = [];
  $scope.watchlist = [];
  $scope.nadaEncontrado = false;
  $scope.episodio = [
  "0",
  "1",
  "2",
  "3",
  "4",
  "5",
  "6",
  "7",
  "8",
  "9",
  "10"
  ];
  $scope.episodio = "";

  $scope.getfunction = function(login,senha){

    var url =  "/loginUsuario";
    var data ={
      login: login,
      senha: senha
    };

    $http.post(url, data).then(function (response) {

      $scope.result = "Sucessful!";
      $state.go('home');
      $scope.usuarioLogado = response.data;

    }, function (response) {
      $scope.result = "Dados inválidos!";
    });

  };


  $scope.pesquisarSeries = function(serie) {
    var pesquisa = serie.split(" ");
    var resultado = "https://omdbapi.com/?s=";
    for (i = 0; i < pesquisa.length; i++) {
      if (i == pesquisa.length -1) {
        resultado += pesquisa[i];
      } else {
        resultado += pesquisa[i] + "%20";
      }
    } resultado += "&apikey=93330d3c&type=series";



    $http.get(resultado).then(function(response){
      $scope.catalogo = response.data.Search;
      $scope.exibicao = $scope.catalogo;


      if (response.data.Response == "False") {
        $scope.nadaEncontrado = true;
      } else {
        $scope.nadaEncontrado = false;
      }
    })



  }

  $scope.cadastrar = function() {
    var nome = null;
    var senha = null;
    var usuarioCadastrado = {"nome": nome, "senha": senha};
    var promise = $http.post("/register", usuarioCadastrado).then(function(response) {
      $scope.resposta = "Cadastrado!"
      $scope.state.go('main.home')

    }, function error (error) {
      console.log(error);
    });
  };

  $scope.adicionarMinhasSeries = function (serie) {
    if ($scope.serieExiste(serie, $scope.minhasSeries)) {
      alert("A série selecionada já está no seu perfil.")
    } else {
      if ($scope.serieExiste(serie, $scope.watchlist)) {
        $scope.minhasSeries.push(serie);
        $scope.removeDaWatchlist(serie);
      } else {
        $scope.minhasSeries.push(serie);
        alert('"'+serie.Title+'" foi adicionada ao seu perfil')
      }

    }
  }

  $scope.removeMinhaSerie = function (serie) {
    if (confirm('Tem certeza que deseja remover "'+serie.Title+'"?') === true) {
      var indice = $scope.minhasSeries.indexOf(serie);
      if (indice > -1) {
        $scope.minhasSeries.splice(indice, 1);
        alert('"'+serie.Title+'" foi removida do seu perfil.')
      }
    }
  };



  $scope.adicionarWatchlist = function (serie) {
    if($scope.serieExiste(serie, $scope.watchlist)) {
      alert('"'+serie.Title+'" já está na sua Watchlist');
    } else if ($scope.serieExiste(serie, $scope.minhasSeries)) {
      alert('"'+serie.Title+'" já está no seu perfil.')
    } else {
      $scope.watchlist.push(serie);
      alert('"'+serie.Title+'" foi adicionada à sua Watchlist')
    }
  }

  $scope.removeDaWatchlist = function (serie) {
    var indice = $scope.watchlist.indexOf(serie);

    if (indice > -1) {
      $scope.watchlist.splice(indice, 1);
    }
  }

  $scope.serieExiste = function (serie, list) {
    return (list.indexOf(serie) != -1);
  }

  $scope.verInfo = function (ev, serie) {
    $http.get("https://omdbapi.com/?i="+ serie.imdbID +"&apikey=93330d3c&type=series").then(function (response) {
      $scope.serieDialog = response.data;

      $mdDialog.show({
        controller: DialogController,
        templateUrl: 'info.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose:true,
        locals: {
          serieDialog: $scope.serieDialog
        }
      });
    });



  }

  function DialogController($scope, $mdDialog, serieDialog) {
    $scope.serie = serieDialog;

    $scope.hide = function() {
      $mdDialog.hide();
    };

    $scope.cancel = function() {
      $mdDialog.cancel();
    };

    $scope.answer = function(answer) {
      $mdDialog.hide(answer);
    };
  }

  function debounce(func, wait, context) {
    var timer;

    return function debounced() {
      var context = $scope,
      args = Array.prototype.slice.call(arguments);
      $timeout.cancel(timer);
      timer = $timeout(function() {
        timer = undefined;
        func.apply(context, args);
      }, wait || 10);
    };
  }

  function buildDelayedToggler(navID) {
    return debounce(function() {
      $mdSidenav(navID)
      .toggle()
      .then(function () {
        $log.debug("toggle " + navID + " is done");
      });
    }, 200);
  }

  function buildToggler(navID) {
    return function() {
      $mdSidenav(navID)
      .toggle()
      .then(function () {
        $log.debug("toggle " + navID + " is done");
      });
    };
  }

})

.controller('LeftCtrl', function ($scope, $timeout, $mdSidenav, $log) {
  $scope.close = function () {
    $mdSidenav('left').close()
    .then(function () {
      $log.debug("close LEFT is done");
    });

  };
})
