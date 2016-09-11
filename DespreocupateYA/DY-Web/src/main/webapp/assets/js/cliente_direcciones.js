index.controller("clienteDireccionesController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    initCosasVarias();//Siempre
    $scope.cliente_direcciones = {};
    $scope.cliente_direcciones.titulo = "Direcciones";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }

    var userInfo = $cookies.getObject("user_info");
    console.log(userInfo);
    console.log("ID Usuario: " + userInfo.id_usuario);

    var requestObtenerDirecciones = {
        url: commonContext + "/micuenta/obtenerDirecciones",
        method: "GET",
        params: {
            idUsuario: userInfo.id_usuario
        }
    };

    var direcciones = new Array();
    $http(requestObtenerDirecciones).then(function successCallback(successResponse) {
        if (responseOk(successResponse)) {
            var resposeData = successResponse.data;

            if (resposeData.direcciones === 0) {
                console.log("el usuario no cuenta con direcciones aun");
            } else {
                console.log("Las direcciones del usuario son");
                console.log(resposeData.direcciones);
                
                direcciones.push({
                    direccion: "Avenida de los peces, Int 1, Col. Shehwuweghuifdwe"
                });
            }



        } else {
            mensajeDY("Oops!", "Algo salió mal, no se pueden obtener las direcciones");
        }
        
                
        $scope.direcciones = direcciones;
    }, function errorCallback(error) {
        mensajeDY("Oops!", "Algo salió mal, intentalo mas tarde");
    });


});