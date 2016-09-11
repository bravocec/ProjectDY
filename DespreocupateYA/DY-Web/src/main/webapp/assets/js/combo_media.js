function slctr(texto,valor){
	this.texto = texto
	this.valor = valor
}

var hogar=new Array()
	hogar[0] = new slctr('- -Hogar- -')
	hogar[1] = new slctr("Img1 Slide",'img1_slide')
	hogar[2] = new slctr("Img2 Slide",'img2_slide')


var index=new Array()
	index[0] = new slctr('- -Index- -')
	index[1] = new slctr("Img1 Banner",'img1_banner_index')
	index[2] = new slctr("Img2 Banner",'img2_banner_index')
	index[3] = new slctr("Img Hogar",'img_hogar_index')
	index[4] = new slctr("Img Mascotas",'img_mascotas_index')
	index[5] = new slctr("Img Tecnología",'img_tecnología_index')
	
var mascotas=new Array()
	mascotas[0] = new slctr('- -Mascotas- -')
	mascotas[1] = new slctr("Img1 Slide",'img1_slide')
	mascotas[2] = new slctr("Img2 Slide",'img2_slide')	
	
var tecnologia=new Array()
	tecnologia[0] = new slctr('- -Tecnología- -')
	tecnologia[1] = new slctr("Img1 Slide",'img1_slide')
	tecnologia[2] = new slctr("Img2 Slide",'img2_slide')	

function slctryole(cual,donde){
	if(cual.selectedIndex != 0){
		donde.length=0
		cual = eval(cual.value)
		for(m=0;m<cual.length;m++){
			var nuevaOpcion = new Option(cual[m].texto);
			donde.options[m] = nuevaOpcion;
			if(cual[m].valor != null){
				donde.options[m].value = cual[m].valor
			}
			else{
				donde.options[m].value = cual[m].texto
			}
		}
	}
}