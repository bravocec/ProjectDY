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
    
    $http(requestGetProductos).then(function(success){
    	if(responseOk(success)){
    		$scope.productos = success.data.productos;
    	}else{
    		mensajeDY("Productos","Hubo un error al momento de obtener tus productos, intentalo más tarde");
    		$scope.productos = new Array();
    	}
    },function(error){
    	console.log(error);
    	$scope.productos = new Array();
    	mensajeDY("Productos","Hubo un error al momento de obtener tus productos, intentalo más tarde");
    });

    $scope.verDetalleProducto = function(idProducto){
    	$cookies.put("currentIdProducto",idProducto);
    };

});