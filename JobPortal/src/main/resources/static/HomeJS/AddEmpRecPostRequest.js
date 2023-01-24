$(document).ready(
	function() {

		// SUBMIT FORM
		$("#saveRoleWise").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			// PREPARE FORM DATA
			var formData = {
				name: $("#name").val(),
				email: $("#email").val(),
				password: $("#password").val(),
				role: $("#role").val(),
				dob: $("#dob").val(),
				gender: $("#gender").val(),
				phone: $("#phone").val(),
				city: $("#city").val()
			}

			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/saveByRole",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(data) {

					if (data != null) 
					{
						console.log(data);
                    	$("#postResultDiv").html
                    	(
							"" + data.email
							+ "  Save Successfully! </p>"
						);
						setInterval('location.reload()', 5000);
					}
					else 
					{
						console.log("wrong data");
						$("#postResultDiv").html(
							"" + "email exist </p>");
						setInterval('location.reload()', 5000);
					}
				},

			});

		}

	})
