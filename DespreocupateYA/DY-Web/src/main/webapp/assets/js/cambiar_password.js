index.controller("cambiarPasswordController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCositasValidador();
    $scope.cambiar_password = {};
    $scope.cambiar_password.titulo = "Cambiar password";
});