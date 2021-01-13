/**
 * 
 */

$(document).ready(function(){
$('.table .meBtn').on('click',function(event){
		
		event.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href,function(tempUser,status){
			
			
			$('.myForm #id').val(tempUser.id);
			$('.myForm #name').val(tempUser.name);
			$('.myForm #parent').val(tempUser.parent);
			
			
		});
		
		
		$('.myForm #exampleModal').modal();
	});
	
	
	
	$('.table .mdBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		
		$('#myModal #delRef').attr('href',href);
		$('#myModal').modal();
	});

	
});	
	
	