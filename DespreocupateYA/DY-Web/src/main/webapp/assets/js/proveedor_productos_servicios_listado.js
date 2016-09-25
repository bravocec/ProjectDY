index.controller("proveedorProductosServiciosListadoController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCositasValidador();

    $scope.proveedor_productos_servicios_listado = {};
    $scope.proveedor_productos_servicios_listado.titulo = "Productos y Servicios";

    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }

    var requestGetProductos = {
    	url : commonContext + "/proveedor/productos/list",
    	method : "GET"
    }
    $scope.productos = new Array();
    $http(requestGetProductos).then(function(success){
    	if(responseOk(success)){
    		console.log(success);
    	}else{
    		mensajeDY("Productos","Hubo un error al momento de obtener tus productos, intentalo más tarde");
    	}
    },function(error){
    	console.log(error);
    	mensajeDY("Productos","Hubo un error al momento de obtener tus productos, intentalo más tarde");
    });

});