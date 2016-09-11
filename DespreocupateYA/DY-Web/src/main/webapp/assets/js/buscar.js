index.controller("buscarController", function ($scope, $http, $httpParamSerializer, $cookies, $location) {
    $scope.buscar = {};
    $scope.buscar.titulo = "Buscar en DespreocupateYa";
    
    initCosasVarias();

    $.widget("custom.catcomplete", $.ui.autocomplete, {
        _create: function () {
            this._super();
            this.widget().menu("option", "items", "> :not(.ui-autocomplete-category)");
        },
        _renderMenu: function (ul, items) {
            var that = this,
                    currentCategory = "";
            $.each(items, function (index, item) {
                var li;
                if (item.category != currentCategory) {
                    ul.append("<li class='ui-autocomplete-category'>" + item.category + "</li>");
                    currentCategory = item.category;
                }
                li = that._renderItemData(ul, item);
                if (item.category) {
                    li.attr("aria-label", item.category + " : " + item.label);
                    li[0].innerHTML = "<img style='width:25px;height:25px' src='"+item.img+"'>&nbsp;&nbsp;"+  li[0].innerHTML  ;
                }
                
                
            });
        }
    });

    var data = [
        {label: "Ana's Meseros", category: "Hogar",img : "assets/img/productos/meseros_dy.jpg"},
        {label: "Meseros Full", category: "Hogar",img : "assets/img/productos/meseros_1.jpg"},
        {label: "Limpiando tu casa", category: "Hogar",img : "assets/img/productos/domesticas_2.jpg"},
        {label: "Anerson y sus Mascotas", category: "Mascotas",img : "assets/img/productos/jhon.jpg"},
        {label: "Pequeños amigos", category: "Mascotas",img : "assets/img/productos/amigos.jpg"},
        {label: "Proplan Croquetas 1Kg", category: "Mascotas",img : "assets/img/productos/croquetas_1.jpg"}
    ];

    $("#buscar").catcomplete({
        delay: 0,
        source: data
    });
    
    
    
    $scope.ver = function(){
        var htmlValor = $("#ui-id-2").html();
        console.log(htmlValor);
    };

});