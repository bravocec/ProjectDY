index.controller("clienteDireccionesController", function ($scope, $http, $httpParamSerializer, $cookies, $location,$route) {
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
        method: "GET"
    };

    var direcciones = new Array();
    $http(requestObtenerDirecciones).then(function successCallback(successResponse) {
        if (responseOk(successResponse)) {
            var resposeData = successResponse.data;

            if (resposeData.direcciones == 0) {
                mensajeDY("Direcciones","Aun no cuentas con direcciones");
            } else {
                console.log(resposeData.direcciones);
                for(var key in resposeData.direcciones){
                    var dir = resposeData.direcciones[key];
                    var primera = "";
                    if(dir.numInt == null){
                        primera += dir.calle + " " + dir.numExt ;
                    }else{
                        primera += dir.calle + " " + dir.numExt + " " +dir.numInt;
                    }
                    direcciones.push({
                        direccion: primera + ", " + dir.colonia + ", " + dir.delegacionMunicipio + ", " + dir.estado,
                        idDomicilio : dir.idDomicilio
                    });
                }
                
            }



        } else {
            mensajeDY("Oops!", "Algo salió mal, no se pueden obtener las direcciones");
        }
        
                
        $scope.direcciones = direcciones;
    }, function errorCallback(error) {
        mensajeDY("Oops!", "Algo salió mal, intentalo mas tarde");
    });

    $scope.eliminarDireccion = function(id){
        console.log("Direccion a borrar "+id);
        var request = {
            url : commonContext + "/micuenta/eliminarDomicilio",
            method : "POST",
            data :  {
                idDomicilio : id
            }
        };
        $http(request).then(function(success){
            if(responseOk(success)){
                mensajeDY("Direcciones","Dirección eliminada con éxito");
                $route.reload();
            }else{
                mensajeDY("Oops!", "Algo salió mal, intentalo mas tarde");
            }
        },function(error){
            console.log(error);
            mensajeDY("Oops!", "Algo salió mal, intentalo mas tarde");
        });
    };

    $scope.detalleDireccion = function(id){
        $cookies.put("idDomicilio",id);
        $location.path("/direcciones_detalles");
    };


});