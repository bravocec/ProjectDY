index.controller("direccionAgregarController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre
    $scope.direccion_agregar = {};
    $scope.direccion_agregar.titulo = "Agregar dirección";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }

});