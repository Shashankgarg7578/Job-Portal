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
	$.ajax
		({
			type: "GET",
			url: "/Employee/getJobs",
			data: { keyword: keyword },
			success: function(data) {
				console.log(data);
				data.forEach(function(item) {
					tableData += '<tr>' +
						'<td id = "Id' + item.id + '">' + item.id + '</td>' +
						'<td id = "companyName' + item.id + '">' + item.companyName + '</td>' +
						'<td id = "jobSkills' + item.id + '">' + item.jobSkills + '</td>' +
						'<td id = "date' + item.id + '">' + item.date + '</td>' +
						'<td id = "city' + item.id + '">' + item.city + '</td>' +
						'<td id = "description' + item.id + '">' + item.description + '</td>' +

						'<td>' +
						'<button type = "button" id = "apply' + item.id + '" class = "btn btn-danger btn-md apply" onclick = "apply(' + item.id + ')">Apply</button>' +
						'</td>' +
						'</tr>';
				});
				$("#myTable>tbody").html(tableData);
			},
			error: function(response) {
				console.log(response);
			},
		});
}

$(document).ready(function() {
	ajaxGet();
});




function apply(id) {
	var parent = $(this).parent().parent();

	var button = parent.children("td:nth-child(8)");
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "/Employee/applyJobs/" + id,
		cache: false,
		success: function() {
			alert("Applied");
			location.reload();
		},
	});
};


