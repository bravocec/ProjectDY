index.controller("proveedorSucursalesAdicionalesDetallesController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre   
    $scope.proveedor_sucursales_adicionales_detalles = {};
    $scope.proveedor_sucursales_adicionales_detalles.titulo = "Sucursal + nombreSucursal";
});