index.controller("productosController",function($scope,$http, $httpParamSerializer,$cookies,$location){
   // initCosasVarias();
    $scope.productos = {};
    $scope.productos.titulo = "Productos";
});

//function initPestanas() {
//    $("#description").click(function (e) {
//        e.preventDefault();
//        $("#descripcion").addClass("active");
//        $("#reseña").removeClass("active");
//
//        $("#description").addClass("in active");
//        $("#reviews").removeClass("in active");
//        
//    });
//    $("#reviews").click(function (e) {
//        e.preventDefault();
//        $("#descripcion").removeClass("active");
//        $("#reseña").addClass("active");
//
//        $("#description").removeClass("in active");
//        $("#reviews").addClass("in active");
//
//    });
//
//}