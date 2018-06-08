$(document).ready(function () {

	$('#birthday').datepicker();

	var clientsTable = $('#clientsTable').DataTable({
		"paging" : false,
		"ordering" : false,
		"info" : false
	});

	$('#editButton').addClass('disabled');
	$('#deleteButton').addClass('disabled');

	$('#clientsTable tbody').on('click', 'tr', function () {
		$(this).toggleClass('selected');

		var selectedCount = $('.selected').length;

		if (selectedCount == 1) {
			$('#editButton').prop('disabled', false);
		} else {
			$('#editButton').prop('disabled', true);
		}

		if (selectedCount == 0) {
			$('#deleteButton').addClass('disabled');
		}

		if (selectedCount > 0) {
			$('#deleteButton').removeClass('disabled');
		}
	});

	$("#update").bind("click", function(event) {

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