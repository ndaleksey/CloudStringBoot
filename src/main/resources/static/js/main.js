$(document).ready(function () {

	/*$('#button').click( function () {
		alert( clientsTable.rows('.selected').data().length +' row(s) selected' );
	} );*/


	$("#register").bind("click", function(event) {

		event.preventDefault();

		var client = {}
		client["firstName"] = $("#firstName").val();
		client["lastName"] = $("#lastName").val();
		client["middleName"] = $("#middleName").val();
		client["gender"] = $("#gender").val();
		client["birthday"] = $("#birthday").val();
		client["email"] = $("#email").val();
		client["phone"] = $("#phone").val();
		client["city"] = $("#city").val();
		client["cardId"] = $("#cardId").val();

		var clientJSON = JSON.stringify(client);

		$.ajax({
			url: "/clients/add",
			type: "POST",
			data: clientJSON,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				console.log("УРАА");
			}
		});

		$('#registrationModal').modal('hide');
	});
});