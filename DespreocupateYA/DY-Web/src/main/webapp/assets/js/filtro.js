index.controller("filtroController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre    
    MouseWheel.initMouseWheel();
    $scope.filtro = {};
    $scope.filtro.titulo = "Filtrar";
});