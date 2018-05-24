$(document).ready( function(){
	  var client = filestack.init('AtDjIemxmTYaaLRZ07D7kz');
	    
	    $('#subirImagen').click( function(){
	        client.pick({accept: 'image/*'}).then(function(result) {
	            $('#urlImagen').val(result.filesUploaded[0].url); // esto guarda en un input la url del archivo recien subido. pueden 
	            //ponerlo hidden despues para que el usuario no lo vea
	        });
	    });
});
