index.controller("administradorController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    $scope.administrador = {};
    $scope.administrador.titulo = "Resumen proveedores";

    if (!validaSesion($cookies,$http)) {
        $location.path("/index");
        return;
    }

    var proveedoresRequest = {
        method: "GET",
        url: commonContext + "/admin/resumenProveedores"
    };
    
    $http(proveedoresRequest).then(function successCallback(response){
        if(response.status === 200 && response.data.status === "200"){
            var resumen = response.data.resumen;
            $("#aceptados").html(resumen.aceptado);
            $("#nuevos").html(resumen.nuevo);
            $("#pendientes").html(resumen.pendiente);
            $("#rechazados").html(resumen.rechazado);
        }else{
            
            $("#aceptados").html("Error");
            $("#nuevos").html("Error");
            $("#pendientes").html("Error");
            $("#rechazados").html("Error");
        }
    },function errorCallback(error){
    });

});