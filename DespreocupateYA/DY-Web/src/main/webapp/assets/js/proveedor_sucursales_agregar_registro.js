index.controller("proveedorSucursalesAgregarRegistroController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre  
    $scope.proveedor_sucursales_agregar_registro = {};
    $scope.proveedor_sucursales_agregar_registro.titulo = "Registro sucursal";
});