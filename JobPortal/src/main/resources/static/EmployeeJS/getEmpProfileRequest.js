$(document).ready(
	function() {
		$("#submit").submit(function(event) {
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {
		var data = new FormData($("#submit")[0]);
		
			$.ajax({
				type: 'POST',
				enctype: 'multipart/form-data',
				data: data,
				url: "/Employee/EmpProfile",
				processData: false,
				contentType: false,
				cache: false,
				success: function() {
					
					$("#postResultDiv").html
                    	(
							"  Update Successfully! </p>"
						);
						setInterval('location.reload()', 2000);
					
				}
			});
		}
	});
