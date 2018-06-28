$(document).ready(function () {

	// $('#birthday').datepicker();

	/*$('.saveDialog #saveDetailsButton').on('click', function (event) {
		event.preventDefault();

		if ($('.saveDialog #male').prop('checked'))
			console.log("MALE");
		else
			console.log("FEMALE");
	});*/

	$('.addButton, .table .editButton').on('click', function (event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();

		if (text == 'Изменить') {
			$.get(href, function (client, status) {
				$('.saveDialog #id').val(client.id);
				$('.saveDialog #firstName').val(client.firstName);
				$('.saveDialog #lastName').val(client.lastName);
				$('.saveDialog #middleName').val(client.middleName);
				// $('.saveDialog #gender').val(client.gender);
				$('.saveDialog #birthday').val(client.birthday);
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
			$('.saveDialog #birthday').val('');
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
		debugger
		$('.deleteDialog #deleteRef').attr('href', href);
		$('.deleteDialog #deleteClientModal').modal();
	});
});