index.controller("proveedorIndexController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    $scope.proveedor_index = {};
    $scope.proveedor_index.titulo = "Perfil de proveedor";
    
    initCosasVarias();
//    if (!validaSesion($cookies, $http)) {
//        $location.path("/index");
//        return;
//    }
    
});