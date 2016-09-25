index.controller("cambiarPasswordController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCositasValidador();
    $scope.cambiar_password = {};
    $scope.cambiar_password.titulo = "Cambiar password";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }
    
    $scope.cambiarPassword = function(){
    	if($scope.datapassword.$valid){
    		var request = {
    			url : commonContext + "/micuenta/cambiarPassword",
    			method : "POST",
    			data : $scope.data
    		};

    		$http(request).then(function(success){
    			if(responseOk(success)){
    				if(success.data.message){
    					mensajeDY("Password",success.data.message);
    				}else{
    					mensajeDY("Password","Password actualizado con éxito");
    					$location.path("/cliente_datos_personales");
    				}
    				
    			}else{
    				mensajeDY("Password","Hubo un error al actualizar el password, intentalo más tarde");
    			}
    		},function(error){
    			console.log(error);
    			mensajeDY("Password","Hubo un error al actualizar el password, intentalo más tarde");
    		});
    	}
    };
});