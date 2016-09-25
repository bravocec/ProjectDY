index.controller("direccionAgregarController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre
    $scope.direccion_agregar = {};
    $scope.direccion_agregar.titulo = "Agregar dirección";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }
    $scope.data = {
    	
    };


    $scope.guardaDireccion = function(){
    	if($scope.data.$valid){
    		var requestGuardadoDirecciones = {
    			url : commonContext + "/micuenta/guardaDomicilio",
    			method : "POST",
    			data : $scope.data 
    		};
    		$http(requestGuardadoDirecciones).then(function(response){
    				if(responseOk(response)){
    					mensajeDY("Dirección","Dirección agregada con éxito");
    					$location.path("/cliente_direcciones");
    				}else{
    					mensajeDY("Dirección","Hubo un error al guardar la dirección, intentalo más tarde.");
    				}
    		},function(error){
    			console.log(error);
    			mensajeDY("Dirección","Hubo un error al guardar la dirección, intentalo más tarde.");
    		});
    	}else{
    		console.log("No valido");
    	}
    };

});