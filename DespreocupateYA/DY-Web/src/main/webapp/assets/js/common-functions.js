var generalScope = null;
var iniciarSession = "<li id='login-info'><a href='#login'>Iniciar sesion</a></li>";
var initSession = "<a href='#login'>Iniciar sesión</a>";
var closeSession = "<a href='#logout'>Cerrar sesión</a>";
//var commonContext = "http://despreocupateya.jl.serv.net.mx/DY";
var commonContext = "http://localhost:9080/DY-Web";
var lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";

function initCosasVarias() {
    App.init();
    App.initScrollBar();
    App.initParallaxBg();
    OwlCarousel.initOwlCarousel();
    RevolutionSlider.initRSfullWidth();
    StyleSwitcher.initStyleSwitcher();
}

function initCositasProductos(){
    MouseWheel.initMouseWheel();
    OwlCarousel.initOwlCarousel(); 
    MasterSliderShowcase2.initMasterSliderShowcase2();
}

function initCositasValidador() {

    Masking.initMasking();
    Datepicker.initDatepicker();
    Validation.initValidation();
    RegForm.initRegForm();
    LoginForm.initLoginForm();
    ContactForm.initContactForm();
    CommentForm.initCommentForm();    

}

function initApp() {

    App.init();

}

function evaluaErrorCallback(obj, cookies) {
    var sCookies = false;
    if (obj && obj.error == "invalid_token") {
        if (cookies) {
            console.log("The token in Remove Token is: " + cookies.get("acces_token"));
            cookies.remove("acces_token");
            cookies.remove("user_info");
            console.log("After Remove Token: " + cookies.get("acces_token"));

            //scope.initsession = iniciarSession;
            $("#initsession").html("");
            $("#initsession").html(iniciarSession);
            sCookies = true;

        }
    } else {
        if (cookies) {
            console.log("Removiendo Datos...");
            console.log(cookies);
            console.log("The token in Remove Token is: " + cookies.get("acces_token"));
            cookies.remove("acces_token");
            cookies.remove("user_info");
            console.log("After Remove Token: " + cookies.get("acces_token"));

            //scope.initsession = iniciarSession;
            $("#initsession").html("");
            $("#initsession").html(iniciarSession);
            sCookies = true;

        }
    }
    if (!sCookies) {
        //scope.initsession = iniciarSession;
        $("#initsession").html("");
        $("#initsession").html(iniciarSession);
    }
}

function removeElement(elementToRemove) {
    //angular.element(angular.element('body').find(elementToRemove)).remove();
    var tag = angular.element(document.querySelector(elementToRemove))
    tag.remove();
}

function getWarningMessage(title, body) {
    /*
     <div class="alert alert-warning fade in alert-dismissable">
     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
     <strong>Warning!</strong> Best check yo self, you're not looking too good.
     </div>
     */
    var html = '<div class="alert alert-warning fade in alert-dismissable">';
    html += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>';
    html += '<strong>' + title + '</strong>';
    html += body;
    html += '</div>';
    return html;
}

function getErrorMessage(title, body) {
    /*
     <div class="alert alert-danger fade in alert-dismissable">
     <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
     <strong>Oh snap!</strong> Change a few things up and try submitting again.
     </div> 
     */
    var html = '<div class="alert alert-danger fade in alert-dismissable">';
    html += '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>';
    html += '<strong>' + title + ' </strong>';
    html += body;
    html += '</div>';



    return html;
}

function pintaCositas(id, mensaje) {
    $(id).html("");
    $(id).html(mensaje);
}

function changueInitSession(bool, rol) {
    if (bool) {
        $("#superlogin").html(initSession);
        $("#registrocliente").css({
            'display': 'block'
        });
        $("#registroproveedor").css({
            'display': 'block'
        });
    } else {
        $("#superlogin").html(closeSession);
        $("#registrocliente").css({
            'display': 'none'
        });
        $("#registroproveedor").css({
            'display': 'none'
        });
    }
    if (rol) {
        var roles = rol.roles_usuario;
        var currentRol = "";
        for(var key in roles){
            if(roles[key].descRol === "Proveedor"){
                currentRol = roles[key].descRol;
                break;
            }
            currentRol = roles[key].descRol;
        }
        switch (currentRol) {
            case "Proveedor":
                $("#menuprincipal").css({
                    'display': 'none'
                });
                $("#menuadmin").css({
                    'display': 'none'
                });
                $("#menuprovedor").css({
                    'display': 'block'
                });
                break;
            case "Administrador":
                $("#menuprincipal").css({
                    'display': 'none'
                });
                $("#menuadmin").css({
                    'display': 'block'
                });
                $("#menuprovedor").css({
                    'display': 'none'
                });
                break;
                
            case "Cliente":
                $("#menuprincipal").css({
                    'display': 'block'
                });
                $("#menuadmin").css({
                    'display': 'none'
                });
                $("#menuprovedor").css({
                    'display': 'none'
                });
            break;
            default:
                goBackMenus();
                break;
        }
    } else {
        goBackMenus();
    }

}

function goBackMenus() {
    $("#menuprincipal").css({
        'display': 'none'
    });
    $("#menuadmin").css({
        'display': 'none'
    });
    $("#menuprovedor").css({
        'display': 'none'
    });
}

function mensajeDY(header, body) {
    $("#myLargeModalLabel3").html(header);
    $("#textmodal").html("<p>" + body + "</p>");
    $("#modaldy").modal('show');
    /*
     $("#modaldy").addClass("in");
     $("#modaldy").css({
     'display' : 'block'
     });
     */
}

function responseOk(response) {
    if (response) {
        if ((response.data && response.data.status)) {
            if(response.status === 200 && response.data.status === "200"){
                return true;
            }
        } else if (response.status === 200) {
            return true;
        }
    }
    return false;
}

function validaSesion(element,httpAngularElement) {
    var token = element.get("access_token");
    var userinfo = element.getObject("user_info");
    var statusSession = false;
    if (typeof token == 'undefined' || token == '') {
        changueInitSession(true);
        return statusSession;
    }
    $.ajax({
        url: commonContext + "/log/valida",
        type: "GET",
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", 'Bearer ' + token);
        },
        success: function (data) {
            if (data.status == 200) {
                statusSession = true;
                changueInitSession(false,userinfo);
                httpAngularElement.defaults.headers.common.Authorization= 'Bearer ' + element.get("access_token");
            }else{
                element.remove("access_token");
                element.remove("user_info");
                changueInitSession(true);
                mensajeDY("Sesion Terminada", "Su sesion ha terminado,inicie sesion nuevamente");
            }
        },
        error: function (errorData) {
            element.remove("access_token");
            element.remove("user_info");
            changueInitSession(true);
            mensajeDY("Sesion Terminada", "Su sesion ha terminado,inicie sesion nuevamente");
        }
    });
    return statusSession;
}

function saveAs(blob, fileName){
    var url = window.URL.createObjectURL(blob);

    var doc = document.createElement("a");
    doc.href = url;
    doc.download = fileName;
    doc.click();
    window.URL.revokeObjectURL(url);
}