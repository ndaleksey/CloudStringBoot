$(document).ready(function () {

	console.log("REFRESH")

	// $('#birthday').datepicker();

	$('.saveDialog #saveDetailsButton').on('click', function (event) {
		event.preventDefault();

		var client = {}
		client["id"] = $("#id").val();
		client["firstName"] = $("#firstName").val();
		client["lastName"] = $("#lastName").val();
		client["middleName"] = $("#middleName").val();
		client["gender"] = $('.saveDialog #male').prop('checked') ? "MALE" : "FEMALE";
		client["birthday"] = getNormalizedDate($("#birthday").val(), '-', '.');
		client["email"] = $("#email").val();
		client["phone"] = $("#phone").val();
		client["city"] = $("#city").val();
		client["cardId"] = $("#cardId").val();
		client["gender"] = $('.saveDialog #active').prop('checked') ? "ACTIVE" : "INACTIVE";

		var clientJSON = JSON.stringify(client);

		$.ajax({
			url: "/clients/save",
			type: "POST",
			data: clientJSON,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				location.reload(true);
			},
			error: function () {
				console.log('error');
			}
		});

		$('.saveDialog #clientDetailsModal').modal('hide');
	});

	$('.addButton, .table .editButton').on('click', function (event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();

		if (text == 'Изменить') {
			$.get(href, function (client, status) {
				console.log(client);

				$('.saveDialog #id').val(client.id);
				$('.saveDialog #firstName').val(client.firstName);
				$('.saveDialog #lastName').val(client.lastName);
				$('.saveDialog #middleName').val(client.middleName);
				// $('.saveDialog #gender').val(client.gender);

				$('.saveDialog #birthday').val(getNormalizedDate(client.birthday, '.', '-'));
				$('.saveDialog #email').val(client.email);
				$('.saveDialog #phone').val(client.phone);
				$('.saveDialog #city').val(client.city);
				$('.saveDialog #cardId').val(client.cardId);
			});
		} else {
			$('.saveDialog #id').val('');
			$('.saveDialog #firstName').val('');
			$('.saveDialog #lastName').val('');
			$('.saveDialog #middleName').val('');
			// $('.saveDialog #gender').val(client.gender);
			$('.saveDialog #birthday').val('1990-01-01');
			$('.saveDialog #email').val('');
			$('.saveDialog #phone').val('');
			$('.saveDialog #city').val('');
			$('.saveDialog #cardId').val('');
		}

		$('.saveDialog #clientDetailsModal').modal();
	});

	$('.table .deleteButton').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');
		$('.deleteDialog #deleteRef').attr('href', href);
		$('.deleteDialog #deleteClientModal').modal();
	});
});

function getNormalizedDate(date, oldSep, newSep) {
	var dateArray = date.split(oldSep);
	var result = dateArray[2] + newSep + dateArray[1] + newSep + dateArray[0];
	console.log(result);
	return result;
}