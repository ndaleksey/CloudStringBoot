$(document).ready(function () {

	// $('#birthday').datepicker();

	$('.table .editButton').on('click', function (event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function (client, status) {

			// $('.saveDialog #id').val(client.id);
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

		$('.saveDialog #clientDetailsModal').modal();
	});

	$("#deleteClientsBtn").bind("click", function (event) {
		event.preventDefault();


	});
});