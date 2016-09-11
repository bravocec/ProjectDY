index.controller("tarjetasAgregarController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    $scope.tarjetas_agregar = {};
    $scope.tarjetas_agregar.titulo = "Agregar tarjeta";
    initCosasVarias();
    OrderForm.initOrderForm();
    ReviewForm.initReviewForm();
    CheckoutForm.initCheckoutForm();
});