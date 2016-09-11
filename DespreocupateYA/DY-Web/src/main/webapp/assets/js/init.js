var index = angular.module("dy", ["ngResource", "ngRoute", "ngCookies", "ngSanitize", "angularUtils.directives.dirPagination"]);

index.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: "templateParts/iniciar_session_part1.html",
            controller: "lo"
        }).when('/logout', {
            templateUrl: "templateParts/index_part1.html",
            controller: "logout"
        }).when("/index", {
            templateUrl: "templateParts/index_part1.html",
            controller: "dyController"
        }).when("/registro_cliente", {
            templateUrl: "templateParts/registro_cliente_part1.html",
            controller: "registroClienteController"
        }).when("/sobre_dy", {
            templateUrl: "templateParts/sobre_dy_part1.html",
            controller: "sobredyController"
        }).when("/politicas_privacidad", {
            templateUrl: "templateParts/politicas_privacidad_part1.html",
            controller: "politicasController"
        }).when("/recuperar_password", {
            templateUrl: "templateParts/recuperar_password_part1.html",
            controller: "recuperarPasswordController"
        }).when("/proveedor_registro", {
            templateUrl: "templateParts/proveedor_registro_part1.html",
            controller: "proveedorRegistroController"
        }).when("/proveedor_registro_documentos", {
            templateUrl: "templateParts/proveedor_registro_documentos_part1.html",
            controller: "proveedorRegistroDocumentosController"
        }).when("/terminos_condiciones", {
            templateUrl: "templateParts/terminos_condiciones_part1.html",
            controller: "terminosCondicionesController"
        }).when("/categoria_hogar", {
            templateUrl: "templateParts/categoria_hogar_part1.html",
            controller: "categoriaHogarController"
        }).when("/categoria_mascotas", {
            templateUrl: "templateParts/categoria_mascotas_part1.html",
            controller: "categoriaMascotasController"
        }).when("/productos", {
            templateUrl: "templateParts/productos_part1.html",
            controller: "productosController"
        }).when("/carrito", {
            templateUrl: "templateParts/carrito_part1.html",
            controller: "carritoController"
        }).when("/cliente_datos_personales", {
            templateUrl: "templateParts/cliente_datos_personales_part1.html",
            controller: "clienteDatosPersonalesController"
        }).when("/cliente_direcciones", {
            templateUrl: "templateParts/cliente_direcciones_part1.html",
            controller: "clienteDireccionesController"
        }).when("/cupones", {
            templateUrl: "templateParts/cupones_part1.html",
            controller: "cuponesController"
        }).when("/vistos_recientemente", {
            templateUrl: "templateParts/vistos_recientemente_part1.html",
            controller: "vistosRecientementeController"
        }).when("/favoritos", {
            templateUrl: "templateParts/favoritos_part1.html",
            controller: "favoritosController"
        }).when("/notificaciones", {
            templateUrl: "templateParts/notificaciones_part1.html",
            controller: "notificacionesController"
        }).when("/direccion_agregar", {
            templateUrl: "templateParts/direccion_agregar_part1.html",
            controller: "direccionAgregarController"
        }).when("/direcciones_detalles", {
            templateUrl: "templateParts/direcciones_detalles_part1.html",
            controller: "direccionesDetallesController"
        }).when("/cambiar_password", {
            templateUrl: "templateParts/cambiar_password_part1.html",
            controller: "cambiarPasswordController"
        }).when("/mis_pedidos", {
            templateUrl: "templateParts/mis_pedidos_part1.html",
            controller: "misPedidosController"
        }).when("/mis_pedidos_detalles", {
            templateUrl: "templateParts/mis_pedidos_detalles_part1.html",
            controller: "misPedidosDetallesController"
        }).when("/buscar", {
            templateUrl: "templateParts/buscar_part1.html",
            controller: "buscarController"
        }).when("/administrador", {
            templateUrl: "templateParts/administrador_part1.html",
            controller: "administradorController"
        }).when("/administrador_detalle_proveedores", {
            templateUrl: "templateParts/administrador_proveedores_detalles_part1.html",
            controller: "administradorDetalleProveedores"
        }).when("/administrador_proveedores", {
            templateUrl: "templateParts/administrador_proveedores_part1.html",
            controller: "administradorProveedoresController"
        }).when("/administrador_media", {
            templateUrl: "templateParts/administrador_media_part1.html",
            controller: "administradorMediaController"
        }).when("/proveedor_index", {
            templateUrl: "templateParts/proveedor_index_part1.html",
            controller: "proveedorIndexController"
        }).when("/proveedor_productos_servicios_listado", {
            templateUrl: "templateParts/proveedor_productos_servicios_listado_part1.html",
            controller: "proveedorProductosServiciosListadoController"
        }).when("/proveedor_productos_servicios_agregar", {
            templateUrl: "templateParts/proveedor_productos_servicios_agregar_part1.html",
            controller: "proveedorProductosServiciosAgregarController"
        }).when("/proveedor_productos_servicios_detalles", {
            templateUrl: "templateParts/proveedor_productos_servicios_detalles_part1.html",
            controller: "proveedorProductosServiciosDetallesController"
        }).when("/proveedor_datos_personales", {
            templateUrl: "templateParts/proveedor_datos_personales_part1.html",
            controller: "proveedorDatosPersonalesController"
        }).when("/proveedor_documentacion", {
            templateUrl: "templateParts/proveedor_documentacion_part1.html",
            controller: "proveedorDocumentacionController"
        }).when("/proveedor_notificaciones", {
            templateUrl: "templateParts/proveedor_notificaciones_part1.html",
            controller: "proveedorNotificacionesController"
        }).when("/proveedor_sucursales", {
            templateUrl: "templateParts/proveedor_sucursales_part1.html",
            controller: "proveedorSucursalesController"
        }).when("/proveedor_sucursal_principal", {
            templateUrl: "templateParts/proveedor_sucursal_principal_part1.html",
            controller: "proveedorSucursalPrincipalController"
        }).when("/proveedor_sucursales_adicionales", {
            templateUrl: "templateParts/proveedor_sucursales_adicionales_part1.html",
            controller: "proveedorSucursalesAdicionalesController"
        }).when("/proveedor_sucursales_adicionales_detalles", {
            templateUrl: "templateParts/proveedor_sucursales_adicionales_detalles_part1.html",
            controller: "proveedorSucursalesAdicionalesDetallesController"
        }).when("/proveedor_sucursales_agregar_registro", {
            templateUrl: "templateParts/proveedor_sucursales_agregar_registro_part1.html",
            controller: "proveedorSucursalesAgregarRegistroController"
        }).when("/proveedor_sucursales_agregar_documentos", {
            templateUrl: "templateParts/proveedor_sucursales_agregar_documentos_part1.html",
            controller: "proveedorSucursalesAgregarDocumentosController"
        }).when("/proveedor_pedidos_abiertos", {
            templateUrl: "templateParts/proveedor_pedidos_abiertos_part1.html",
            controller: "proveedorPedidosAbiertosController"
        }).when("/proveedor_mis_pedidos", {
            templateUrl: "templateParts/proveedor_mis_pedidos_part1.html",
            controller: "proveedorMisPedidosController"
        }).when("/proveedor_mis_pedidos_detalles", {
            templateUrl: "templateParts/proveedor_mis_pedidos_detalles_part1.html",
            controller: "proveedorMisPedidosDetallesController"
        }).when("/proveedor_pedidos_historial", {
            templateUrl: "templateParts/proveedor_pedidos_historial_part1.html",
            controller: "proveedorPedidosHistorialController"
        }).when("/proveedor_pedidos_historial_detalles", {
            templateUrl: "templateParts/proveedor_pedidos_historial_detalles_part1.html",
            controller: "proveedorPedidosHistorialDetallesController"
        }).when("/proveedor_pedidos_detalles", {
            templateUrl: "templateParts/proveedor_pedidos_detalles_part1.html",
            controller: "proveedorPedidosDetallesController"
        }).when("/proveedor_pedidos_detalles", {
            templateUrl: "templateParts/proveedor_pedidos_detalles_part1.html",
            controller: "proveedorPedidosDetallesController"
        }).when("/proveedor_personal", {
            templateUrl: "templateParts/proveedor_personal_part1.html",
            controller: "proveedorPersonalController"
        }).when("/proveedor_personal_agregar", {
            templateUrl: "templateParts/proveedor_personal_agregar_part1.html",
            controller: "proveedorPersonalAgregarController"
        }).when("/proveedor_personal_detalles", {
            templateUrl: "templateParts/proveedor_personal_detalles_part1.html",
            controller: "proveedorPersonalDetallesController"
        }).when("/tarjetas", {
            templateUrl: "templateParts/tarjetas_part1.html",
            controller: "tarjetasController"
        }).when("/tarjetas_info", {
            templateUrl: "templateParts/tarjetas_info_part1.html",
            controller: "tarjetasInfoController"
        }).when("/tarjetas_agregar", {
            templateUrl: "templateParts/tarjetas_agregar_part1.html",
            controller: "tarjetasAgregarController"
        }).when("/filtro", {
            templateUrl: "templateParts/filtro_part1.html",
            controller: "filtroController"
        }).when("/direccion_envio", {
            templateUrl: "templateParts/direccion_envio_part1.html",
            controller: "direccionEnvioController"
        }).when("/pago", {
            templateUrl: "templateParts/pago_part1.html",
            controller: "pagoController"
        }).when("/confirmacion", {
            templateUrl: "templateParts/confirmacion_part1.html",
            controller: "confirmacionController"
        }).otherwise({
            redirectTo: "/index"
        });
    }]);

index.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

/*
 index.service('fileUpload', ['$http', function ($http) {
 this.uploadFileToUrl = function (file, uploadUrl) {
 var fd = new FormData();
 for(var key in file){
 fd.append('file', file[key]);
 }
 $http.post(uploadUrl, fd, {
 transformRequest: angular.identity,
 headers: {'Content-Type': undefined}
 })
 .success(function () {
 })
 .error(function () {
 });
 }
 }]);
 
 */
index.controller("logout", function ($location, $cookies) {
    $('body').removeClass("sliding-panel-ini-open");

    $cookies.remove("access_token");
    changueInitSession(true);
    //evaluaErrorCallback(null, $cookies);
    initCosasVarias();
});

index.controller("dyController", function ($scope, $http, $cookies, $sce, $route,$httpParamSerializer) {

    //Set general Scope
    initCosasVarias();
    $scope.index = {};
    $scope.index.titulo = "DespreocupateYa";
    
    validaSesion($cookies,$http);
    
    var categoriasMenuAux = [
        {nombrecat : "Hogar",imgsrc : "assets/img/index/DY1.png"}
    ];
	
    
    /*
    var requestSecciones = {
      url : commonContext + "/media/secciones",
      method : "GET"
    };
    
    $http(requestSecciones).then(function successCallback(success){
        if(responseOk(success)){
            var secciones = success.data.secciones;
            var seccionesArray = new Array();
            if(secciones !== '0'){
                for(var key  in secciones){
                    seccionesArray.push({
                        nombrecat : ""+key,imgsrc : "data:image/png;base64,"+secciones[key]
                    });
                }
                $scope.catsmenu = seccionesArray;
            }else{
                $scope.catsmenu = categoriasMenuAux;
            }
        }else{
            $scope.catsmenu = categoriasMenuAux;
        }
    },function errorCallback(){
        $scope.catsmenu = categoriasMenuAux;
    });
    */
});

/*
 var loginModule = angular.module("lo",["ngResource","ngRoute","ngCookies"]);
 
 loginModule.controller("sessionMainController",function($scope, $resource, $http, $httpParamSerializer, $cookies){
 $scope.data = {
 grant_type : "password", username : "", password : "" , client_id : "restapp"
 };
 $scope.encoded = btoa("restapp:secret");
 
 $scope.login = function(){
 var req = {
 method : "POST",
 url : commonContext+"/oauth/token",
 headers : {
 "Authorization" : "Basic " + $scope.encoded,
 "Content-type" : "application/x-www-form-urlencoded; charset=utf-8"
 },
 data : $httpParamSerializer($scope.data)
 };
 $http(req).then(function successCallback(response){
 $http.defaults.headers.common.Authorization= 'Bearer ' + response.data.access_token;
 console.log(response);
 //window.location.redirect = "index.html";
 },function errorCallback(response){
 if(response.data.error == "invalid_grant"){
 alert("Usuario/Password invalido");
 }else{
 console.log(response.data.error_description);
 alert("Hubo un error, intentalo mÃƒÂ¡s tarde");
 }
 });
 };
 });
 */

/*
 function validaToken(http,scope,pinta){
 var request = {
 method : "GET",
 url: commonContext+"/log/valida"
 };
 
 http(request).then(function successCallback(userResponse){
 console.log("Token Valido");
 return true;
 },function errorCallback(response){
 console.log("Token invalido");
 return false;
 });
 }
 */
 
 
