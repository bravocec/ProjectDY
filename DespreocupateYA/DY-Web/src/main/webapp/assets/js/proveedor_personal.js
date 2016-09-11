index.controller("proveedorPersonalController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    $scope.proveedor_personal = {};
    $scope.proveedor_personal.titulo = "Personal";
});