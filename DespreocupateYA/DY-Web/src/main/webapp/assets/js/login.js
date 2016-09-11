index.controller("lo",function($scope, $resource, $http, $httpParamSerializer, $cookies,$location,$sce){
    initCosasVarias();
    initCositasValidador();
    $('body').removeClass("sliding-panel-ini-open");
    if($cookies.get("access_token")){
        $http.defaults.headers.common.Authorization= 'Bearer ' + $cookies.get("access_token");
        var validRequest = {
            method : "GET",
            url: commonContext+"/log/valida"
        };
        $http(validRequest).then(function successCallback(){
            $location.path("/index");
        },function errorCallback(responseError){
            evaluaErrorCallback(responseError);
           //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!','Algo salió mal, intentalo más tarde.'));
        });
    }
    $scope.data = {
        grant_type : "password", username : "", password : "" , client_id : "restapp"
    };
    $scope.encoded = btoa("restapp:secret");
    
    $scope.login = function(){
        var req = {
            method : "POST",
            url : commonContext+"/oauth/token",
            headers : {
                 "Authorization" : "Basic " + $scope.encoded,
                 "Content-type" : "application/x-www-form-urlencoded; charset=utf-8"
            },
            data : $httpParamSerializer($scope.data)
        };
        //mauricio@propat.com.mx
        $http(req).then(function successCallback(response){
            $http.defaults.headers.common.Authorization= 'Bearer ' + response.data.access_token;
            $cookies.put("access_token", response.data.access_token);
            
            var requestUserInfo = {
                method : "GET",
                url : commonContext+"/log/userInfo",
                params : {
                    user : $scope.data.username
                }
            };
            
            $http(requestUserInfo).then(function successCallback(userResponse){
                
                console.log("Info OK");
                console.log(userResponse);
                if(userResponse.status === 200 && userResponse.data.status === "200"){
                    $cookies.putObject("user_info",userResponse.data);
                    var roles = userResponse.data.roles_usuario;
                    var redirect = "";
                    var rol = "";
                    for(var key in roles){
                        if(roles[key].descRol === "Proveedor"){
                            redirect = "/proveedor_index";
                            rol = roles[key].descRol;
                            break;
                        }
                        if(roles[key].descRol === "Cliente" && roles.length <2){
                            redirect = "/index";
                            rol = roles[key].descRol;
                            break;
                        }
                        if(roles[key].descRol === "Administrador"){
                            redirect = "/administrador";
                            rol = roles[key].descRol;
                            break;
                        }
                    }
                    changueInitSession(false,userResponse.data);
                    $location.path(redirect);
                }else{
                    console.log("Erorr al obtener los datos del usuario");
                    alert("Hubo un error, intentalo más tarde");
                }
                
            },function errorCallback(errorUserCallBack){
                console.log("Error en la obtención de datos");
                console.log(errorUserCallBack.status);
                //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!','Algo salió mal, intentalo más tarde.'));
                mensajeDY("Oops!",'Algo salió mal, intentalo más tarde.');
            });
            
            
        },function errorCallback(response){
            if(response.data.error == "invalid_grant"){
                //$scope.mensaje_login = $sce.trustAsHtml(getWarningMessage('Credenciales inválidas','El Usuario/Password ingresado es inválido, intentalo de nuevo.'));
                mensajeDY("Usario/Passwor Inválidos","El Usuario/Password ingresado es inválido, intentalo de nuevo.");
            }else{
                console.log(response.data.error_description);
                mensajeDY("Oops!",'Algo salió mal, intentalo más tarde.');
            }
        });
    };
});