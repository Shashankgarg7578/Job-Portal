$(document).ready(
	function() {
		$("#saveJobDetails").submit(function(event) {
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			var data = new FormData($("#saveJobDetails")[0]);

			$.ajax({

				type: "POST",
				enctype: 'multipart/form-data',
				url: "/Recruiter/saveJob",
				data: data,
				processData: false,
				contentType: false,
				cache: false,
				success: function(data) {
                   if (data != null) 
					{
						
                    	$("#postResultDiv").html
                    	(
							"" + data.companyName +" in "+data.city
							+ "  Save Successfully! </p>"
						);
						setInterval('location.reload()', 5000);
					}
				}
			});
		}
	});
