index.controller("clienteDatosPersonalesController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre
    $scope.cliente_datos_personales = {};
    $scope.cliente_datos_personales.titulo = "Datos personales";

    $scope.data = {
        nombres: "",
        apellidos: "",
        email: "",        
        telefono: ""
    };

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }

    var userInfo = $cookies.getObject("user_info");//Lo necesitas para obtener datos del usuario, como su id, rol, email, etc.
    console.log(userInfo);
    console.log("ID Usuario: " + userInfo.id_usuario);



    var request = {
        url: commonContext + "/micuenta/datosPersonales",
        method: "GET",
        params: {
            idUsuario: userInfo.id_usuario
        }
    };

    $http(request).then(function successCallback(response) {
        if (responseOk(response)) {
            console.log(response);
            var datos = response.data;
            $scope.data.nombres = datos.nombres;
            $scope.data.apellidos = datos.apellidos;
            $scope.data.email = datos.email;
            $scope.data.telefono = datos.telefono;

        } else {
            mensajeDY("Error en obtención de datos", "Algo salió mal, intentalo más tarde");
        }
    }, function errorCallback(error) {
        console.log("error");
        mensajeDY("Error en obtención de datos", "Algo salió mal, intentalo más tarde");
    });

    $("#actualizaDatos").click(function (e) {
        e.preventDefault();
        console.log("Voy a actualizar los datos");

        var request = {
            url: commonContext + "/micuenta/guardaDatos",
            method: "POST",
            params: {
                idUsuario: userInfo.id_usuario,
                nombres: $scope.data.nombres,
                apellidos: $scope.data.apellidos,
                email: $scope.data.email,
                telefono: $scope.data.telefono
            }
        };

        $http(request).then(function successCallback(response) {

            console.log(response);

            if (responseOk(response)) {
                var pez = response.data.estatusactualizacion;
                mensajeDY("Estatus actualizacion", pez);
            } else {
                mensajeDY("Error actualización de datos", "Hubo un error, intentalo más tarde")
            }

        });
    });

    
     $scope.actualizarDatos = function (){
    console.log("Voy a actualizar los datos");
    var request = {
        url : commonContext + "/micuenta/guardaDatos",
        method: "POST",
        data: {
            idUsuario: userInfo.id_ususario,
            nombres: $scope.data.nombres,
            apellidos: $scope.data.apellidos,
            email: $scope.data.email,
            telefono: $scope.data.telefono
        }
        
    };
        $http(request).then(function successCallback(response){
             console.log(response);
             if(responseOk(response)){
                 var estatus2 = response.data.estatusactualizacion;
                 mensajeDY("Estatus actualizacion",estatus2);
             }else{
                 mensajeDY("Error actualización de datos","Hubo un error");
             }
    }); 
     
    /*$location.path("/cliente_datos_personales");*/
    };
    

});         