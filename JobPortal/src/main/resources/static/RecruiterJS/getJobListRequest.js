	$(document).ready(
	function() {

		// SUBMIT FORM
		$("#getJob").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxGet();
		});

	});
	
// DO GET
function ajaxGet() {
    var keyword = $("#keyword").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/Recruiter/jobList",
		data:{keyword: keyword},
		success: function(data) {
			
			data.forEach(function(item) {
				tableData += '<tr>' +
					'<td id = "Id' + item.id + '">' + item.id + '</td>' +
					'<td id = "companyName' + item.id + '">' + item.companyName + '</td>' +
					'<td id = "jobSkills' + item.id + '">' + item.jobSkills + '</td>' +
					'<td id = "date' + item.id + '">' + item.date + '</td>' +
					'<td id = "city' + item.id + '">' + item.city + '</td>' +
					'<td id = "description' + item.id + '">' + item.description + '</td>' +
					
					'<td>' +
					'<button type = "button" id = "edit' + item.id + '" class = "btn btn-warning btn-md edit">Edit</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "delet' + item.id + '" class = "btn btn-danger btn-md delet" onclick = "delet(' + item.id + ')">Delete</button>' +
					'</td>' +
					 
					'<td id = "Appliers' + item.id + '">' + '<a href="/Recruiter/getAppliers/' + item.id + '"+" class = "btn btn-warning btn-md "">' + 'Appliers' + 
					'</td>' +
					'</tr>';
			});
			$("#myTable>tbody").html(tableData);
		},
		
	});
}


$(document).ready(function() {
	ajaxGet();
})

/* Delete */
function delet(id){
	if (confirm('Do you really want to delete record?')) {
		var parent = $(this).parent().parent();
		alert(id);
		$.ajax({
			type: "DELETE",
			url: "/Recruiter/deleteJob/" +id,
			cache: false,
			success: function() {
				parent.fadeOut('slow', function() {
					$(this).remove();
				});
				location.reload(true)
			},
			error: function() {
				$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
					$(this).remove();
				});
			}
		});
	}
};


/* Edit */

$(document).delegate('.edit', 'click', function() {

	var parent = $(this).parent().parent();

	var id = parent.children("td:nth-child(1)");
	var companyName = parent.children("td:nth-child(2)");
	var jobSkills = parent.children("td:nth-child(3)");
	var date = parent.children("td:nth-child(4)");
	var city = parent.children("td:nth-child(5)");
	var description = parent.children("td:nth-child(6)");
	
    var buttons = parent.children("td:nth-child(7)");
    

	jobSkills.html("<input type='text' id='jobSkills' value='" + jobSkills.html() + "'/>");
	city.html("<input type='text' id='city' value='" + city.html() + "'/>");
	description.html("<textarea id=description >"+ description.html() +"</textarea>");
	
	buttons.html("<button id='save' class= 'btn btn-success'>Save</button>");

});

$(document).delegate('#save', 'click', function() {
	var parent = $(this).parent().parent();

	var id = parent.children("td:nth-child(1)");
	var companyName = parent.children("td:nth-child(2)");
	var jobSkills = parent.children("td:nth-child(3)");
	var date = parent.children("td:nth-child(4)");
	var city = parent.children("td:nth-child(5)");
	var description = parent.children("td:nth-child(6)");
	
    var buttons = parent.children("td:nth-child(7)");
 
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "/Recruiter/updateJob",
		data: JSON.stringify({
			'id': id.html(), 'companyName': companyName.html(), 'jobSkills': jobSkills.children("input[type=text]").val(), 'date': date.html(),
			'city': city.children("input[type=text]").val(),'description':description.children("textarea").val(),
		}),
		cache: false,
		success: function() {
	    	jobSkills.html(jobSkills.children("input[type = text]").val());
	        city.html(city.children("input[type = text]").val());
	        description.html(description.children("textarea").val());
	        
			buttons.html("<button class='btn btn-warning edit' id='" + id.html() + "'>Edit</button>");
		},

	});

});
