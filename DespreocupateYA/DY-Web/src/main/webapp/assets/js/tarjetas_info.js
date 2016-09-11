index.controller("tarjetasInfoController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    $scope.tarjetas_info = {};
    $scope.tarjetas_info.titulo = "Información tarjeta";
    initCosasVarias();
    OrderForm.initOrderForm();
    ReviewForm.initReviewForm();
    CheckoutForm.initCheckoutForm();
});