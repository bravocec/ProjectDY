index.controller("administradorDetalleProveedores", function ($scope, $http, $httpParamSerializer, $cookies, $location, $window, $timeout, $sce) {
    $scope.administradordetalles = {};
    $scope.administradordetalles.titulo = "Detalles Proveedor";

    initCosasVarias();
    initPestanas();

    if (!validaSesion($cookies, $http)) {
        $location.path("/index");
        return;
    }


    $scope.data = {
        nombres: "",
        apellidos: "",
        email: "",
        razon_social: "",
        rfc: "",
        telefono: "",
        telefono_movil: "",
        calle: "",
        noExterior: "",
        noInterior: "",
        colonia: "",
        delegacion: "",
        estado: "",
        cp: "",
        entreCalle: "",
        ycalle: ""
    };

    var idProveedor = $cookies.get("idProveedor");

    var detalleRequest = {
        url: commonContext + "/admin/detalleProveedor",
        method: "GET",
        params: {
            idProveedor: idProveedor
        }
    };

    $http(detalleRequest).then(function successCallback(response) {
        if (responseOk(response)) {
            var provedor = response.data.proveedor;

            $scope.data.nombres = provedor.nombres;
            $scope.data.apellidos = provedor.apellidos;
            $scope.data.email = provedor.email;
            $scope.data.razon_social = provedor.razon;
            $scope.data.rfc = provedor.rfc;
            $scope.data.telefono = provedor.telefono;
            $scope.data.telefono_movil = provedor.telefonomovil;
            $scope.data.calle = provedor.calle;
            $scope.data.noExterior = provedor.noexterior;
            $scope.data.noInterior = provedor.nointerior;
            $scope.data.colonia = provedor.colonia;
            $scope.data.delegacion = provedor.delegacion;
            $scope.data.estado = provedor.estado;
            $scope.data.cp = provedor.cp;
            $scope.data.entreCalle = provedor.entrecalle1;
            $scope.data.ycalle = provedor.entrecalle2;
        } else {
            mensajeDY("Error en datos", "Hubo un error al momento de obtener los datos del proveedor, intentalo más tarde.");
        }
    }, function errorCallback(error) {
        mensajeDY("Error en datos", "Hubo un error al momento de obtener los datos del proveedor, intentalo más tarde: " + error.status);
    });

    var requestDocs = {
        url: commonContext + "/admin/detalleDocs",
        method: "GET",
        params: {
            idProveedor: idProveedor
        }
    };



    $http(requestDocs).then(function (response) {
        if (responseOk(response)) {
            var docs = response.data.documentos;
            if (docs.imagenSucursal !== '0') {
                $("#imgActual").attr('src', 'data:image/' + docs.extencion + ';base64,' + docs.imagenSucursal);
            }



            if (docs.ife) {
                //$("#ifeImg").attr('src', 'assets/img/pdfIcon.png');
                $("#ifeImg").css({
                    'display': 'none'
                });

                var nombreArchivo = docs.ife;

                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function (e) {
                    if (this.readyState == 4 && this.status == 200) {
                        //this.response is what you're looking for
                        var file = new window.Blob([this.response], {type: 'application/pdf'});
                        var url = window.URL || window.webkitURL;
                        var fileURL = url.createObjectURL(file);
                        var htmlIfe = '<embed  src="' + fileURL + '"  style="width: 100%;height: 500px;"></embed>';
                        $("#ifeSection").append(htmlIfe);


                        //var downloadUrl = URL.createObjectURL(blob);


                    }
                };
                xhr.open('GET', commonContext + "/admin/getFile?idProveedor=" + idProveedor + "&nombreArchivo=" + nombreArchivo);
                xhr.setRequestHeader("Authorization", 'Bearer ' + $cookies.get("access_token"));
                xhr.responseType = 'blob';
                xhr.send();

            }
            if (docs.comprobantedomicilio) {
                $("#imgFileCD").css({
                    'display': 'none'
                });


                var nombreArchivo = docs.comprobantedomicilio;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function (e) {
                    if (this.readyState == 4 && this.status == 200) {
                        //this.response is what you're looking for
                        var file = new window.Blob([this.response], {type: 'application/pdf'});
                        var url = window.URL || window.webkitURL;
                        var fileURL = url.createObjectURL(file);
                        var htmlIfe = '<embed  src="' + fileURL + '"  style="width: 100%;height: 500px;"></embed>';
                        $("#cdSection").append(htmlIfe);


                        //var downloadUrl = URL.createObjectURL(blob);


                    }
                };
                xhr.open('GET', commonContext + "/admin/getFile?idProveedor=" + idProveedor + "&nombreArchivo=" + nombreArchivo);
                xhr.setRequestHeader("Authorization", 'Bearer ' + $cookies.get("access_token"));
                xhr.responseType = 'blob';
                xhr.send();
            }
            if (docs.edocuenta) {
                $("#imgEdoCuenta").css({
                    'display': 'none'
                });

                var nombreArchivo = docs.edocuenta;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function (e) {
                    if (this.readyState == 4 && this.status == 200) {
                        //this.response is what you're looking for
                        var file = new window.Blob([this.response], {type: 'application/pdf'});
                        var url = window.URL || window.webkitURL;
                        var fileURL = url.createObjectURL(file);
                        var htmlIfe = '<embed  src="' + fileURL + '"  style="width: 100%;height: 500px;"></embed>';
                        $("#edoCuentaSection").append(htmlIfe);


                        //var downloadUrl = URL.createObjectURL(blob);


                    }
                };
                xhr.open('GET', commonContext + "/admin/getFile?idProveedor=" + idProveedor + "&nombreArchivo=" + nombreArchivo);
                xhr.setRequestHeader("Authorization", 'Bearer ' + $cookies.get("access_token"));
                xhr.responseType = 'blob';
                xhr.send();
            }
        } else {
            mensajeDY("Documentos", "Error al obtener los documentos del proveedor, intentalo más tarde");
        }
    }, function errorCallback(error) {
        mensajeDY("Documentos", "Error al obtener los documentos del proveedor, intentalo más tarde");
    });
    
    //guardar
    
    $scope.guardarImagenes = function(){
        var imagenSucursal  = $scope.imgSucursal;
        var ife = $scope.fileIfe;
        var comrobanteDomicilio = $scope.fileCD;
        var edoCuenta = $scope.fileEdoCuenta;
        
        
        var arrayDocumentos = {
            'imagenSucursal' : imagenSucursal,
            'ife': ife,
            'comrobanteDomicilio' : comrobanteDomicilio,
            'edoCuenta' : edoCuenta
        };
        
        var contador = 0;
        var cuerpoMensajes = new Array();
        
        for(var key in arrayDocumentos){
            var current = arrayDocumentos[key];
            if(current !== null && current !== undefined){
                console.log(key);
                if(key == "imagenSucursal" ){
                    if(/\.(jpe?g|png)$/i.test(current.name)){
                        var formDataImagen = new FormData();
                        formDataImagen.append('file',current);
                        $http({
                            url : commonContext + "/admin/documentosproveedor",
                            method : "POST",
                            data : formDataImagen,
                            headers: {
                            'Content-Type': undefined
                            },
                            params : {
                                idProveedor : idProveedor,
                                nombreArchivo : key
                            }
                        }).then(function (response){
                        },function(error){
                            console.log("Error al subir la imagen de la sucursal");
                            console.log(error);
                        });
                    }else{
                        contador++;
                        cuerpoMensajes[contador] = "Imagen Sucursal: Solo son admitidas imagenes con extención jpg, png y bmp";
                    }
                }else{
                    if(/\.(pdf)$/i.test(current.name)){
                        console.log(current.name);
                        var formData = new FormData();
                        formData.append('file',current);
                        $http({
                            url : commonContext + "/admin/documentosproveedor",
                            method : "POST",
                            data : formData,
                            headers: {
                            'Content-Type': undefined
                            },
                            params : {
                                idProveedor : idProveedor,
                                nombreArchivo : key
                            }
                        }).then(function (response){
                            console.log(key + " subido con éxito");
                        },function(error){
                            console.log("Error al subir la imagen de la sucursal");
                            console.log(error);
                        });
                    }else{
                        contador++;
                        cuerpoMensajes[contador] = "El archivo "+current.name+" debe de tener una extención .pdf";
                    }
                }
            }
        }
        
        if(contador !== 0){
            
            if (contador > 1) {
                var tituloVarios = "Documentos invalidos!!!";
                var ulHtml = "<ul>";
                for (var key in cuerpoMensajes) {
                    ulHtml += "<li>" + cuerpoMensajes[key] + "</li>";
                }
                ulHtml += "</ul>";

                //pintaCositas("#mensaje_docs", getErrorMessage(tituloVarios, ulHtml));
                mensajeDY(tituloVarios, ulHtml);
            } else {
                var titulo = "Documento inválido!!";
                mensajeDY(titulo, cuerpoMensajes[contador]);
                //pintaCositas("#mensaje_docs", getErrorMessage(titulo, cuerpoMensajes[contador]));
            }
            
        }else{
            $location.path("/administrador_detalle_proveedores");
            mensajeDY("Documentos actualizados", "Los documentos de actualizaron con éxito, en breve los veras reflejados");
            
        }
        
    };


});

function initPestanas() {
    $("#datos").click(function (e) {
        e.preventDefault();
        $("#lidatos").addClass("active");
        $("#lidocs").removeClass("active");
        $("#liestatus").removeClass("active");

        $("#datos_personales").addClass("in active");
        $("#documentacion").removeClass("in active");
        $("#estatus").removeClass("in active");
    });
    $("#docs").click(function (e) {
        e.preventDefault();
        $("#lidatos").removeClass("active");
        $("#lidocs").addClass("active");
        $("#liestatus").removeClass("active");

        $("#datos_personales").removeClass("in active");
        $("#documentacion").addClass("in active");
        $("#estatus").removeClass("in active");
    });
    $("#status").click(function (e) {
        e.preventDefault();
        $("#lidatos").removeClass("active");
        $("#lidocs").removeClass("active");
        $("#liestatus").addClass("active");

        $("#datos_personales").removeClass("in active");
        $("#documentacion").removeClass("in active");
        $("#estatus").addClass("in active");
    });

}
