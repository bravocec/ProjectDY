index.controller("direccionesDetallesController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    initCositasValidador();//Siempre    
    $scope.direcciones_detalles = {};
    $scope.direcciones_detalles.titulo = "Editar Dirección";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }


    var idDomicilio = $cookies.get("idDomicilio");

    var request = {
    	url : commonContext + "/micuenta/domicilio/"+idDomicilio,
    	method : "GET"
    };
    $scope.datadedit = {
    	
    };

    $http(request).then(function(success){
    	if(responseOk(success)){
    		$scope.datadedit = success.data.domicilio;
    	}else{
    		mensajeDY("Dirección","Hubo un error al obtener el detalle de la dirección, intentalo más tarde");
    	}
    },function(error){
    	console.log(error);
    	mensajeDY("Dirección","Hubo un error al obtener el detalle de la dirección, intentalo más tarde");
    });


    $scope.actualiza = function(){
    	if($scope.data.$valid){
    		var updateRequest = {
    			url : commonContext + "/micuenta/actualizarDomicilio",
    			method : "POST",
    			data : $scope.datadedit
    		};
    		
    		$http(updateRequest).then(function(success){
    			if(responseOk(success)){
    				mensajeDY("Dirección","La dirección se actulizó con éxito");
    				$location.path("/cliente_direcciones");
    			}else{
    				mensajeDY("Dirección","Hubo un error al actualizar la dirección, intentalo más tarde");
    			}
    		},function(error){

    		});
    	}
    };

});