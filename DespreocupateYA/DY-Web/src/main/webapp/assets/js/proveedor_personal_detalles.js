index.controller("proveedorPersonalDetallesController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();
    $scope.proveedor_personal_detalles = {};
    $scope.proveedor_personal_detalles.titulo = "Detalles";
});