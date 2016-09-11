index.controller("administradorProveedoresController",function($scope,$http, $httpParamSerializer,$cookies,$location){
    $scope.administrador_proveedores = {};
    $scope.administrador_proveedores.titulo = "Proveedores";
    initCosasVarias();
    
    if (!validaSesion($cookies,$http)) {
        $location.path("/index");
        return;
    }
    
    var providerRequest = {
        url : commonContext+"/admin/obtenerProveedores",
        method : "GET"
    };
    
    $http(providerRequest).then(function successCallback(response){
        if(response.data.status === "200"){
            var p = response.data.proveedores;
            var html = "";
            for(var key in p){
                html += "<tr>";
                html += "<td>"+p[key].idProveedor+"</td>";
                html += "<td><a class='detalleproveedor' id='"+p[key].idProveedor+"' href='#'><strong style='color: #18BA9B;text-decoration: underline #5CB85C;'>"+p[key].razonSocial+"</strong></a></td>";
                html += "<td>"+p[key].rfc+"</td>";
                html += "<td>"+p[key].nombres+"</td>";
                html += "<td>"+p[key].correo+"</td>";
                html += "<td>"+p[key].telefono+"</td>";
                html += "<td><span class='label label-success'>"+p[key].estatus+"</span></td>";
                //html += "<td><span class='label label-purple'>Pendiente</span></td>";
                html += "</tr>";
            }
            $("#tabla-proveedores").html(html);
            $(".detalleproveedor").click(function (e){
                e.preventDefault();
                var idProveedor = $(this).attr('id');
               $cookies.put("idProveedor",idProveedor); 
               
               
               //$location.path("/administrador_detalle_proveedores");
               window.location.href = "#administrador_detalle_proveedores";
            });
            $("#tableprove").DataTable();
        }else{
            console.log("No hay proveedores");
        }
    },function errorCallback(error){
        console.log("Hubo un error en la obtencion de proveedores");
        console.log(error);
    });
    
});