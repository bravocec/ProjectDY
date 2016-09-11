index.controller("tarjetasController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    initCosasVarias();//Siempre
    $scope.tarjetas = {};
    $scope.tarjetas.titulo = "Tarjetas";
    
    if (!validaSesion($cookies, $http)) {//Siempre
        $location.path("/index");
        return;
    }

    //$scope.tarjetas.nombre = "Judith";
    //$scope.tarjetas.terminacion = "990011";
    
    //peticionAjax
    var html = "<div class='col-md-4'>";
    html+= '<a href="#tarjeta_principal">';
    html+= '<div class="service">';
    html+= '<i class="fa fa-cc-amex service-icon"></i>';
    html+= '<div class="desc">';
    html+= '<span>{{tarjetas.nombre}}<br>{{tarjetas.terminacion}}</span>';
    html+= '<p>Eliminar</p>';
    html+= '</div>';
    html+= '</div>';
    html+= '</a>';
    html+= '</div>';
    
     var numeroTarjetasDeCredito = 10;
    var completeHtml = '';
    for(var x=0 ; x < numeroTarjetasDeCredito ;x++){
        completeHtml += html;
    }
    
    $("#divtarjetas").html(completeHtml);
    
    
    
    
});