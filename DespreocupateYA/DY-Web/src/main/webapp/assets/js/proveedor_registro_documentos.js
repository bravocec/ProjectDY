index.controller("proveedorRegistroDocumentosController", ['$scope', "$sce", "$http", "$cookies", "$location", function ($scope, $sce, $http, $cookies, $location) {
        $scope.proveedor_registro_documentos = {};
        $scope.proveedor_registro_documentos.titulo = "Documentos proveedor";
        initCosasVarias();
        
        if(!validaSesion($cookies,$http)){
            $location.path("/index");
            return;
        }
        $("#terminoscondiciones").click(function (e) {
            e.preventDefault();
            mensajeDY("Términos y Condiciones", lorem);
        });

        $scope.subirDocs = function () {
            var imageFileName = $("#fileNameSucursal").val();
            var ifeFileName = $("#fileNameIfe").val();
            var cdFileName = $("#fileNameCd").val();
            var edoFileName = $("#fileNameEdoCuenta").val();
            
            
            var imagenSucursal = $scope.imgSucursal;
            var ife = $scope.ife;
            var comprobanteDomicilio = $scope.comprobanteDomicilio;
            var edoCuenta = $scope.edoCuenta;
            var contador = 0;
            var cuerpoMensajes = new Array();

            if (imagenSucursal === undefined || imagenSucursal === null) {
                contador++;
                cuerpoMensajes[contador] = "La magen de tu sucursal es necesaria";
                //$scope.mensaje_docs = $sce.trustAsHtml(getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));
                //pintaCositas("#mensaje_docs",getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));

            }else if (!/\.(jpe?g|png)$/i.test(imageFileName)){
                contador++;
                cuerpoMensajes[contador] = "Imagen Sucursal: Solo son admitidas imagenes con extención jpg, png y bmp";
            }

            if (ife === undefined || ife === null) {

                contador++;
                cuerpoMensajes[contador] = "El IFE del proveedor es necesaria";
                //$scope.mensaje_docs = $sce.trustAsHtml(getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));
                //pintaCositas("#mensaje_docs",getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));

            }else if(!/\.(pdf)$/i.test(ifeFileName)){
                contador++;
                cuerpoMensajes[contador] = "IFE: Solo son admitidos documentos con extención pdf, jpg, png y bmp";
            }

            if (comprobanteDomicilio === undefined || comprobanteDomicilio === null) {

                contador++;
                cuerpoMensajes[contador] = "El comprobante de domicilio es necesario";
                //$scope.mensaje_docs = $sce.trustAsHtml(getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));
                //pintaCositas("#mensaje_docs",getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));

            }else if(!/\.(pdf)$/i.test(cdFileName)){
                contador++;
                cuerpoMensajes[contador] = "Comprobante domicilio: Solo son admitidos documentos con extención pdf, jpg, png y bmp";
            }

            if (edoCuenta === undefined || edoCuenta === null) {
                contador++;
                cuerpoMensajes[contador] = "El estado de cuenta bancario es necesario";
                //$scope.mensaje_docs = $sce.trustAsHtml(getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));
                //pintaCositas("#mensaje_docs",getErrorMessage("Documento inválido!!","La magen de tu sucursal es necesaria"));

            }else if(!/\.(pdf)$/i.test(edoFileName)){
                contador++;
                cuerpoMensajes[contador] = "Edo. cuenta bancario: Solo son admitidos documentos con extención pdf";
            }

            if (contador == 0) {
                var fileArray = new Array();
                fileArray[0] = imagenSucursal;
                fileArray[1] = ife;
                fileArray[2] = comprobanteDomicilio;
                fileArray[3] = edoCuenta;

                var fd = new FormData();

                for (var key in fileArray) {
                    fd.append('file', fileArray[key]);
                }

                //fileUpload.uploadFileToUrl(fileArray,commonContext+"/registro/documentos_proveedor");
                var idProveedor = $cookies.get("idProveedor");

                if (idProveedor !== undefined && idProveedor !== null && idProveedor !== "") {
                    var request = {
                        url: commonContext + "/registro/documentos_proveedor",
                        method: "POST",
                        data: fd,
                        headers: {
                            'Content-Type': undefined
                        },
                        params: {
                            idProveedor: idProveedor,
                            imagenSucursal : imageFileName,
                            ife : ifeFileName,
                            comprobanteDomicilio : cdFileName,
                            edoCuenta : edoFileName
                        }
                    };

                    $http(request).then(function successCallback(successResponse) {
                        if (!responseOk(successResponse)) {
                            mensajeDY("Servidor ocupado", "Por el momento no es posbile recibir tus documentos, en breve uno de nuestros agentes se pondrá en contacto");
                        } 
                        $location.path("/proveedor_index");
                    }, function errorCallback(error) {
                        mensajeDY("Servidor ocupado", "Por el momento no es posbile recibir tus documentos, en breve uno de nuestros agentes se pondrá en contacto");
                        $location.path("/proveedor_index");
                        //pintaCositas("#mensaje_docs",getErrorMessage("Oopss!!","Algo salió mal, intentalo más tarde "))
                    });
                } else {
                    mensajeDY("Servidor ocupado", "Por el momento no es posbile recibir tus documentos, en breve uno de nuestros agentes se pondrá en contacto");
                    //pintaCositas("#mensaje_docs",getWarningMessage("Documentos!!","Ya estás dado de alta, favor de subir tus documentos en la sección documentos de tu sesion."))
                }

            } else {

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
                return;
            }
        };
    }]);


function onChangueDocs(input){
    input.parentNode.nextSibling.value = input.value;

}