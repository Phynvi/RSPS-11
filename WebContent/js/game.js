$(function(){
	  $('#start').on('click', function(){
		  var pcType = Math.floor(Math.random() * 3) + 1;
		  var pcTypeText = "";
		  var type = "";
		  var typeText = "";
		  var resultText = "";
		  
		  $("select option:selected").each(function () { 
			  	type = $(this).val();
			  	typeText = $(this).text();
			  });
	
		  if(pcType == 1){
			  pcTypeText = "가위";
		  }else if(pcType == 2){
			  pcTypeText = "바위";
		  }else{
			  pcTypeText = "보";
		  }
		  var result = type-pcType;
		  var path = $('#path').val();
		$.ajax({
	          type : "get",
	          url : path+"/game",
	          data: {
	        	  		 pcType : pcType,
	        			 userType : type,
	        		  	 vsResult : result
	                    },
	                    success : function(data) {
	                    	if(result == 0){
	                    		resultText = "당신은" + typeText +"를 냈고 PC도 " +  pcTypeText +"를 내서 비겼습니다.";
	                    	}else if(result == -1 || result == 2){
	                    		resultText = "당신은" + typeText +"를 냈고 PC는 " +  pcTypeText +"를 내서 당신이 졌습니다.";
	                    	}else if(result == 1 || result == -2){
	                    		resultText = "당신은" + typeText +"를 냈고 PC는 " +  pcTypeText +"를 내서 당신이 이겼습니다.";
	                    	}
	                    	if($('p').length == 10){
	                    		$('p')[9].remove();
	                    	}
	                    	$('.resultDescription').prepend('<p>' + resultText+ '</p>');
	                    },
	                    error : function(xhr, status, error) {
	                          alert("에러발생" + error);
	                    }
	        });
	  })
});