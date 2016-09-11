index.controller("proveedorSucursalPrincipalController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre    
    $scope.proveedor_sucursal_principal = {};
    $scope.proveedor_sucursal_principal.titulo = "Sucursal principal";
});