$(document).ready(function () {

	$('#birthday').datepicker();

	var clientsTable = $('#clientsTable').DataTable({
		"paging": false,
		"ordering": false,
		"info": false
	});

	$('#editButton').prop('disabled', false);
	$('#editButton').addClass('disabled');

	$('#deleteButton').prop('disabled', false);
	$('#deleteButton').addClass('disabled');

	$('#clientsTable tbody').on('click', 'tr', function () {
		$(this).toggleClass('selected');

		var selectedCount = $('.selected').length;

		if (selectedCount == 1) {
			$('#editButton').prop('disabled', false);
			$('#editButton').removeClass('disabled');
		} else {
			$('#editButton').prop('disabled', true);
			$('#editButton').addClass('disabled');
		}

		if (selectedCount == 0) {
			$('#deleteButton').prop('disabled', true);
			$('#deleteButton').addClass('disabled');
		}

		if (selectedCount > 0) {
			$('#deleteButton').prop('disabled', false);
			$('#deleteButton').removeClass('disabled');
		}
	});

	$('#editButton').on('click', function (event) {
		// $('#clientDetailsModal').modal();
	})

	$("#update").bind("click", function (event) {

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
				location.reload();
			}
		});

		// $('#registrationModal').modal('hide');
	});

	$("#deleteClientsBtn").bind("click", function (event) {
		event.preventDefault();

		var requestUrl = "/clients/delete?";
		var selectedRows = $('#clientsTable .selected');

		if (selectedRows.length == 0) return;

		selectedRows.each(function () {
			requestUrl = requestUrl.concat("id=" + $(this).find('td').first().html() + "&");
		});

		// delete last & in request url
		requestUrl = requestUrl.substr(0, requestUrl.length - 1);

		console.log(requestUrl);

		$.ajax({
			url: requestUrl,
			type: "DELETE",
			success: function () {
				location.reload();
			}
		})
	});
});