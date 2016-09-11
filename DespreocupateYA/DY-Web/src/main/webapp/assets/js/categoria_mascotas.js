//index.controller("categoriaMascotasController",function($scope,$http, $httpParamSerializer,$cookies,$location){
//    initCosasVarias();
//    $scope.categoria_mascotas = {};
//    $scope.categoria_mascotas.titulo = "Mascotas";  
//});

index.controller('categoriaMascotasController',function($scope, $http, $cookies, $location, $httpParamSerializer){
    initCosasVarias();
    $scope.categoria_mascotas = {};
    $scope.categoria_mascotas.titulo = "Mascotas";
    $scope.productos = []; //declare an empty array
	$http.get("datos.json").success(function(response){ 
		$scope.productos = response;  //ajax request to fetch data into $scope.data
	});
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
});

//con dataResource inyectamos la factoría
//index.controller("categoriaMascotasController", function ($scope, $http, dataResource, $cookies, $location, $httpParamSerializer) {

    
    //hacemos uso de $http para obtener los datos del json
    //$http.get('data.json').success(function (data) {
        //Convert data to array.
        //datos lo tenemos disponible en la vista gracias a $scope
//        $scope.datos = data;
//    });
    //datosResource lo tenemos disponible en la vista gracias a $scope
//    $scope.datosResource = dataResource.get();
//});

//de esta forma tan sencilla consumimos con $resource en AngularJS
//index.factory("dataResource", function ($resource) {
//    return $resource("data.json", //la url donde queremos consumir
 //       {}, //aquí podemos pasar variables que queramos pasar a la consulta
        //a la función get le decimos el método, y, si es un array lo que devuelve
        //ponemos isArray en true
//        { get: { method: "GET", isArray: true }
//    });
//});

