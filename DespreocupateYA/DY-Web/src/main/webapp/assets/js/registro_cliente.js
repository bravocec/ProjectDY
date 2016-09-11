index.controller("registroClienteController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    $scope.registro = {};
    $scope.registro.titulo = "Registro Cliente";
    initCosasVarias();
    initCositasValidador();
    
    $("#terminoscondiciones").click(function (e){
        e.preventDefault();
        mensajeDY("Términos y Condiciones",lorem);
    });
    
    $scope.data = {
        nombres: "",
        apellidos: "",
        email: "",
        password: ""
    };
    
    $scope.datad = {
        telefono : ""
    };



    $scope.send = function () {
        console.log("click");
        console.log($scope.data);
        var request = {
            method: "POST",
            url: commonContext+"/registro/cliente",
            data: $scope.data,
            params : {
                telefono : $scope.datad.telefono
            }
        };

        $http(request).then(function successCallback(success) {

            if (success.data.status === "200") {
                var userName = $scope.data.email;
                $scope.data = {
                    grant_type: "password", username: $scope.data.email, password: $scope.data.password, client_id: "restapp"
                };

                $scope.encoded = btoa("restapp:secret");

                var inicioSession = {
                    method: "POST",
                    url: commonContext+"/oauth/token",
                    headers: {
                        "Authorization": "Basic " + $scope.encoded,
                        "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: $httpParamSerializer($scope.data)
                };

                $http(inicioSession).then(function successCallback(response) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.access_token;
                    $cookies.put("access_token", response.data.access_token);

                    var requestUserInfo = {
                        method: "GET",
                        url: commonContext+"/log/userInfo",
                        params: {
                            user: userName
                        }
                    };

                    $http(requestUserInfo).then(function successCallback(userResponse) {


                        if (userResponse.status == "200") {
                            $cookies.putObject("user_info", userResponse.data);
                            changueInitSession(false,userResponse.data);
                            $location.path("/index");
                        } else {
                            mensajeDY("Oops!","Hubo un error en el registro: "+userResponse.data.status);
                        }

                    }, function errorCallback(errorUserCallBack) {
                        mensajeDY("Oops!","Hubo un error en el registro: "+errorUserCallBack.status);
                        //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!', 'Algo salió mal, intentalo más tarde.'));
                    });

                }, function errorCallback(responseError) {
                    if (responseError.data.error == "invalid_grant") {
                        console.log(responseError.data);
                        mensajeDY("Usario/Passwor Inválidos","El Usuario/Password ingresado es inválido, intentalo de nuevo.");
                        //$scope.mensaje_login = $sce.trustAsHtml(getWarningMessage('Credenciales inválidas', 'El Usuario/Password ingresado es inválido, intentalo de nuevo.'));
                    } else {
                        console.log(responseError.data.error_description);
                        mensajeDY("Oops!",'Algo salió mal, intentalo más tarde: '+responseError.data.error_description);
                        //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!', 'Algo salió mal, intentalo más tarde.'));
                    }
                });
            } else {
                //TODO: Cambiar esto por mensajes constructores a partir del código de error devuelto
                console.log(success);
                if(success.data.descripcion){
                    mensajeDY("Oops!",'Algo salió mal, intentalo más tarde: '+success.data.status + ": "+success.data.descripcion);
                }else{
                    mensajeDY("Oops!",'Algo salió mal, intentalo más tarde: '+success.data.status);
                }
            }



        }, function errorCallback(error) {
            console.log(error);
        });
    };
});
