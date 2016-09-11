index.controller("proveedorPersonalAgregarController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();
    $scope.proveedor_personal_agregar = {};
    $scope.proveedor_personal_agregar.titulo = "Agregar";
});