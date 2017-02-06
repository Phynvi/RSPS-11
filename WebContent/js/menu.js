$(function(){
	var path = $('#path').val();
	$('#menu1').on('click', function(){
		window.location =  path+"/game";
	});
	
	$('#menu2').on('click', function(){
		window.location =  path+"/history";
	});
	
	$('#menu3').on('click', function(){
		window.location =  path+"/board";
	});
});

