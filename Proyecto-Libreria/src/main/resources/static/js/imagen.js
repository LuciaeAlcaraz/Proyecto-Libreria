$(document).ready(function() {
	
	$("#subirImagen").click(function () {
		cloudinary.openUploadWidget({
	
		upload_preset : 'ntumij50', 
		cloud_name: 'dbvpxjxay',
		theme: 'white',
		multiple: false,
		max_image_width: 750,
		max_image_height: 500,
		max_files: 5,
		folder: 'mi_carpeta',
		sources: [ 'local', 'url', 'facebook'], 
	}, function(error, result) {
		 $('#urlImagen').val(result[0].secure_url).ready(function() {
				$('#subirImagen').prop('value', 'Imagen subida');
		 }); 
	})
})

});

