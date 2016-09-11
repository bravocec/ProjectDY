index.controller("proveedorRegistroController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    initCosasVarias();
    initCositasValidador();
    $scope.proveedor_registro = {};
    $scope.proveedor_registro.titulo = "Registro proveedor";
    
    $("#terminoscondiciones").click(function (e){
        e.preventDefault();
        mensajeDY("Términos y Condiciones",lorem);
    });

    $scope.data = {
        nombres: "",
        apellidos: "",
        rfc: "",
        password: "",
        email: ""
    };

    $scope.datap = {
        razon_social: "",
        telefono: "",
        telefono_movil: ""
    };

    $scope.datad = {
        calle: "",
        noExterior: "",
        noInterior: "",
        cp: "",
        estado: "",
        delegacion: "",
        colonia: "",
        entreCalle: "",
        ycalle: ""
    };



    navigator.geolocation.getCurrentPosition(function (location) {
        var latitud = location.coords.latitude;
        var longitud = location.coords.longitude;
        $cookies.put("latlng", latitud + "," + longitud);
        console.log("latlng", latitud + "," + longitud);
        var geolocationRequest = {
            url: "https://maps.googleapis.com/maps/api/geocode/json",
            method: "GET",
            params: {
                latlng: latitud + "," + longitud,
                language: "es"
            },
            headers: {
                'Authorization': function () {
                    return null;
                }
            }
        };
        //$http.defaults.headers.common.Authorization= '';
        $http(geolocationRequest).then(function successCallback(okResponse) {
            if (okResponse.status == 200 && okResponse.data.status === "OK") {
                var responseArray = okResponse.data.results[0].address_components;
                for (var key in responseArray) {
                    var type = responseArray[key].types.join(",");
                    if (type == "street_number") {
                        $scope.datad.noExterior = responseArray[key].long_name;
                    }
                    if (type == "route" || type == "point_of_interest,establishment") {
                        $scope.datad.calle = responseArray[key].long_name;
                    }
                    if (type == "sublocality_level_1,sublocality,political" || type == "sublocality_level_1" || type == "sublocality" || type == "sublocality_level_1,sublocality") {
                        $scope.datad.colonia = responseArray[key].long_name;
                    }
                    if (type == "sublocality,political" || type == "locality,political" || type == "neighborhood,political" || type == "administrative_area_level_3,political" || type == "administrative_area_level_2,political") {
                        $scope.datad.delegacion = responseArray[key].long_name;
                    }
                    if (type == "administrative_area_level_1,political") {
                        $scope.datad.estado = responseArray[key].long_name;
                    }

                    if (type == "postal_code") {
                        $scope.datad.cp = responseArray[key].long_name;
                    }
                }
            } else {
                alert("No se pudo obtener la localización del usuario");
            }

        }, function errorCallback(errorResponse) {
            console.log("Error");
            console.log(errorResponse);
        });

    });

    $scope.registrar = function () {



        if ($scope.datap.telefono === "") {
            $scope.datap.telefono = $("#phone").val();
        }

        var direccion = $scope.datad.calle
                + "," + $scope.datad.noExterior
                + "," + $scope.datad.noInterior
                + "," + $scope.datad.colonia
                + "," + $scope.datad.delegacion
                + "," + $scope.datad.estado
                + "," + $scope.datad.cp;

        if ($scope.datad.entreCalle !== '' && $scope.datad.ycalle !== '') {
            direccion += "," + $scope.datad.entreCalle + " y " + $scope.datad.ycalle;
        }
        console.log(direccion);

        var direccionGoogleMaps = $scope.datad.calle + " " + $scope.datad.noExterior + ", " + $scope.datad.colonia + ", " + $scope.datad.cp + " " + $scope.datad.delegacion + ", " + $scope.datad.estado;



        //$cookies.put("latlng", latitud + "," + longitud);
        var latitud_longitud = "";

        latitud_longitud = $cookies.get("latlng");

        if (latitud_longitud == null || latitud_longitud === '') {
            console.log("Direccion Google maps: " + direccionGoogleMaps);
            $.ajax({
                url: "https://maps.googleapis.com/maps/api/geocode/json",
                type: "GET",
                async: false,
                data: {
                    address: direccionGoogleMaps
                },
                success: function (response) {
                    console.log(response);
                    latitud_longitud = response.results[0].geometry.location.lat + "," + response.results[0].geometry.location.lng;
                    console.log("Peticion Ok");
                    console.log(latitud_longitud);
                }
            });
        }



        var array = new Array();

        for (var key in $scope.datap) {
            array[key] = $scope.datap[key];
        }

        array['direccion'] = direccion;
        //TODO: Cambiar este códifo hardcode por un combo con las categorias existentes en la base de datos
        array['categoria'] = 1;

        //Latitud y longitud
        array['latlng'] = latitud_longitud;



        var request = {
            method: "POST",
            url: commonContext+"/registro/proveedores",
            data: $scope.data,
            params: array
        };

        $http(request).then(function successCallback(responseSuccess) {
            console.log("Response Ok");
            if (responseOk(responseSuccess)) {
                $cookies.put("idProveedor", responseSuccess.data.idProveedor);
                var userName = $scope.data.email;
                $scope.data = {
                    grant_type: "password", username: $scope.data.email, password: $scope.data.password, client_id: "restapp"
                };

                $scope.encoded = btoa("restapp:secret");

                var inicioSession = {
                    method: "POST",
                    url: commonContext+"/oauth/token",
                    headers: {
                        "Authorization": "Basic " + $scope.encoded,
                        "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: $httpParamSerializer($scope.data)
                };

                $http(inicioSession).then(function successCallback(response) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.access_token;
                    $cookies.put("access_token", response.data.access_token);

                    var requestUserInfo = {
                        method: "GET",
                        url: commonContext+"/log/userInfo",
                        params: {
                            user: userName
                        }
                    };

                    $http(requestUserInfo).then(function successCallback(userResponse) {


                        if (responseOk(userResponse)) {
                            $cookies.putObject("user_info", userResponse.data);
                            changueInitSession(false,userResponse.data);
                            $location.path("/proveedor_registro_documentos");
                        } else {
                            mensajeDY("Oops!", "Hubo un error en el registro");
                        }

                    }, function errorCallback(errorUserCallBack) {
                        mensajeDY("Oops!", "Hubo un error en el registro");
                        //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!', 'Algo salió mal, intentalo más tarde.'));
                    });

                }, function errorCallback(responseError) {
                    if (responseError.data.error == "invalid_grant") {
                        console.log(responseError.data);
                        mensajeDY("Usario/Passwor Inválidos", "El Usuario/Password ingresado es inválido, intentalo de nuevo");
                        //$scope.mensaje_login = $sce.trustAsHtml(getWarningMessage('Credenciales inválidas', 'El Usuario/Password ingresado es inválido, intentalo de nuevo.'));
                    } else {
                        console.log(responseError.data.error_description);
                        mensajeDY("Oops!", 'Algo salió mal, intentalo más tarde' );
                        //$scope.mensaje_login = $sce.trustAsHtml(getErrorMessage('Oops!', 'Algo salió mal, intentalo más tarde.'));
                    }
                });
            } else {
                mensajeDY("Oopss!!", "Algo salió mal, intentalo más tarde");
                //pintaCositas("#mensaje_registro_prov", getErrorMessage("Oopss!!", "Algo salió mal, intentalo más tarde "))
            }
        }, function errorCallback(errorResponse) {
            mensajeDY("Oopss!!", "Algo salió mal, intentalo más tarde");
            //pintaCositas("#mensaje_registro_prov", getErrorMessage("Oopss!!", "Algo salió mal, intentalo más tarde "))
        });

    };

    $scope.back = function () {
        $location.path("/index");
    };

});




function getAddressInfoByZip(latlang) {
    if (latlang.length >= 5 && typeof google != 'undefined') {
        var addr = {};
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({'latlng': latlang}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK && results.length >= 1) {
                console.log("El result ");
                console.log(results);
                for (var ii = 0; ii < results[0].address_components.length; ii++) {
                    var street_number = route = street = city = state = zipcode = country = formatted_address = '';
                    var types = results[0].address_components[ii].types.join(",");
                    if (types == "street_number") {
                        addr.street_number = results[0].address_components[ii].long_name;
                    }
                    if (types == "route" || types == "point_of_interest,establishment") {
                        addr.route = results[0].address_components[ii].long_name;
                    }
                    if (types == "sublocality,political" || types == "locality,political" || types == "neighborhood,political" || types == "administrative_area_level_3,political") {
                        addr.city = (city == '' || types == "locality,political") ? results[0].address_components[ii].long_name : city;
                    }
                    if (types == "administrative_area_level_1,political") {
                        addr.state = results[0].address_components[ii].short_name;
                    }
                    if (types == "postal_code" || types == "postal_code_prefix,postal_code") {
                        addr.zipcode = results[0].address_components[ii].long_name;
                    }
                    if (types == "country,political") {
                        addr.country = results[0].address_components[ii].long_name;
                    }
                }
                addr.success = true;
                for (name in addr) {
                    console.log('### google maps api ### ' + name + ': ' + addr[name]);
                }
                return addr;

            } else {
                return {success: false};
            }
        });
    } else {
        alert("google.maps.js Not Found");
    }
    return {success: false};
}
