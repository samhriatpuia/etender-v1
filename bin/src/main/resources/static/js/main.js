/**
 * 
 */
$(document).ready(function(){
	
	
	$('.table .eBtn').on('click',function(event){
		
		event.preventDefault();
		var href = $(this).attr('href');
		
		$.get(href,function(tempEmployee,status){
			
			
			$('.myForm #id').val(tempEmployee.id);
			$('.myForm #firstName').val(tempEmployee.firstName);
			$('.myForm #lastName').val(tempEmployee.lastName);
			$('.myForm #email').val(tempEmployee.email);
			
		});
		
		
		$('.myForm #exampleModal').modal();
	});
	
	
	
	$('.table .dBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		
		$('#myModal #delRef').attr('href',href);
		$('#myModal').modal();
	});
	
	});