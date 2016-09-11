index.controller("recuperarPasswordController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCositasValidador();
    $scope.recuperar_password = {};
    $scope.recuperar_password.titulo = "Recuperar password";
});