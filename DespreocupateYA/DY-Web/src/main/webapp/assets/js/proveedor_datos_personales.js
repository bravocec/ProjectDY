index.controller("proveedorDatosPersonalesController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre    
    $scope.proveedor_datos_personales = {};
    $scope.proveedor_datos_personales.titulo = "Datos personales";
});