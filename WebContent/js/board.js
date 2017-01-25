$(function(){
	
	var path = $('#path').val();
	$('.b_content').attr('style', 'display:none');
	$('.boardtitle').on('click', function(){
		var id = this.dataset.id;
		var content = $('html').find('tr.b_content')[id - 1];
		
		if(content.style.display != "" ){
			$('.b_content').attr('style', 'display:none');
			$('#content'+id).attr('style', 'display:grid');
		}else{
			$('#content'+id).attr('style', 'display:none');
		}
	});
	
	$('#writeBtn').on('click', function(){
		if($('#writer').val() == "" ){
			window.alert("작성자를 입력해주세요");
			return false;
		}
		
		if($('#title').val() == "" ){
			window.alert("제목을 입력해주세요");
			return false;
		}
		
		if($('#content').val() == "" ){
			window.alert("내용을  입력해주세요");
			return false;
		}
		$('#writeFrm').submit();
	});
	$('#cancleBtn').on('click', function(){
		history.go(-1);
	});
});

