$(document).ready(function () {

	console.log("REFRESH")

	$('.saveDialog #saveDetailsButton').on('click', function (event) {
		event.preventDefault();

		var check = {}
		check["id"] = $("#id").val();
		check["number"] = $("#number").val();
		check["registrationTime"] = getNormalizedDate($("#registrationTime").val());
		check["shopNumber"] = $("#shopNumber").val();
		check["status"] = $('.saveDialog #created').prop('checked') ? "CREATED" : "PROCESSED";

		var checkJSON = JSON.stringify(check);

		$.ajax({
			url: "/checks/save",
			type: "POST",
			data: checkJSON,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				location.reload(true);
			},
			error: function () {
				console.log('error');
			}
		});

		$('.saveDialog #checkDetailsModal').modal('hide');
	});

	$('.addButton, .table .editButton').on('click', function (event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();

		if (text == 'Изменить') {
			$.get(href, function (check, status) {
				console.log(check);

				$('.saveDialog #id').val(check.id);
				$('.saveDialog #number').val(check.number);
				$('.saveDialog #registrationTime').val(getCalendarDate(check.registrationTime));
				$('.saveDialog #shopNumber').val(check.shopNumber);

				if (check.status == 'PROCESSED') $('.saveDialog #processed').prop('checked', 'checked');
				else $('.saveDialog #created').prop('checked', 'checked');
			});
		} else {
			$('.saveDialog #id').val('');
			$('.saveDialog #number').val('');
			$('.saveDialog #registrationTime').val(Date.now());
			$('.saveDialog #shopNumber').val('');
		}

		$('.saveDialog #checkDetailsModal').modal();
	});

	$('.table .deleteButton').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');
		$('.deleteDialog #deleteRef').attr('href', href);
		$('.deleteDialog #deleteCheckModal').modal();
	});
});

function getNormalizedDate(date) {
	var splits = date.split('-');
	var year = splits[0];
	var month = splits[1];
	splits = splits[2].split('T');
	var day = splits[0];

	splits = splits[1].split(':');
	var hour = splits[0];
	var min = splits[1];

	var result = day + '.' + month + '.' + year + ' ' + hour + ':' + min + ':00';
	console.log(result);
	return result;
}

function getCalendarDate(date) {
	var splits = date.split('.');
	var day = splits[0];
	var month = splits[1];
	var last = splits[2].split(' ');
	var year = last[0];
	var time = last[1];
	splits = time.split(':');
	var hour = splits[0];
	var min = splits[1];
	var sec = splits[2];

	var result = year + '-' + month + '-' + day + 'T' + hour + ':' + min;

	console.log(result);
	return result;
}