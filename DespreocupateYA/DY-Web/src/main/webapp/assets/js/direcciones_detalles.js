index.controller("direccionesDetallesController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre    
    $scope.direcciones_detalles = {};
    $scope.direcciones_detalles.titulo = "Detalles";
});