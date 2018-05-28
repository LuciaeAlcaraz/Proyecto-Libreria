$(document).ready(function() {
	$(".tarjeta-libros").click(function() {
		$(this).toggleClass("seleccionada");
	});
	$('#seleccionarTodas').change(function() {
		if ($(this).is(":checked")) {
			$(".tarjeta-libros").addClass("seleccionada");
		} else {
			$(".tarjeta-libros").removeClass("seleccionada");
		}
	});
});

//$(document).ready(function() {
	//$('#genero').click(function(result) {
		//	 if ($(this).is(":checked")){
			//	 $(".genero").val("Fantasía");
			 //}else{
				// $(".genero").val("");
			 //}
//	});
//});