index.controller("proveedorProductosServiciosAgregarController",function($scope,$http, $httpParamSerializer,$cookies,$location){
	initCosasVarias();
	initCositasValidador();
    $scope.proveedor_productos_servicios_agregar = {};
    $scope.proveedor_productos_servicios_agregar.titulo = "Agregar";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }


    $scope.p = {

    };

    
    

    /*
    $scope.cats.push({
    	idCatprodserv : 1,
    	descripcion : "Descripcion"
    });

    $scope.cats.push({
    	idCatprodserv : 2,
    	descripcion : "Descripcion 2"
    });
    */

    $http({
    	url : commonContext + "/proveedor/productos/listSucursalesByProveedor",
    	method : "GET"
    }).then(function(success){
    	if(responseOk(success)){
    		$scope.sucs = success.data.sucs;
    	}else{
    		$scope.sucs = new Array();
    	}
    },function(error){
    	console.log(error);
    	$scope.sucs = new Array();
    });

    $http({
    	url : commonContext + "/proveedor/productos/listSubcategorias",
    	method : "GET"
    }).then(function(success){
    	if(responseOk(success)){
    		$scope.cats = success.data.subCats;
    	}else{
    		$scope.cats = new Array();
    	}
    },function(error){
    	console.log(error);
    	$scope.cats = new Array();
    });

    $scope.agregarProducto = function(){
    	if($scope.p.$valid){
    		console.log("Valido");
    		$http({
    			url : commonContext + "/proveedor/productos/persist",
    			method : "POST",
    			data : $scope.p
    		}).then(function(success){
    			if(responseOk(success)){
    				mensajeDY("Productos/Servicios","Producto/servicio guardado con éxito");
    				$location.path("/proveedor_productos_servicios_listado");
    			}else{
    				mensajeDY("Productos/Servicios","Hubo un error al momento de guardar tu producto/servicio, intentenlo más tarde");	
    			}
    		},function(error){
    			console.log(error);
    			mensajeDY("Productos/Servicios","Hubo un error al momento de guardar tu producto/servicio, intentenlo más tarde");
    		});
    	}else{
    		console.log("Inválido");
    	}
    };

});