$(document).ready(function () {

	console.log("REFRESH")

	var position;
	var check;

	$('.table #checkListDialog').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');
		$.get(href, function (checkList, status) {
			console.log(checkList);
		})
	});

	$('.table #delCheckRef').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function (ch, status) {
			check = ch;
		});
		$('#deleteCheckDialog').modal();
	});

	$('.modal #applyCheckDelBtn').on('click', function (event) {
		event.preventDefault();

		var checkJSON = JSON.stringify(check);
		console.log(checkJSON);

		$.ajax({
			url: "/checks",
			method: "DELETE",
			data: checkJSON,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				location.reload(true);
			},
			error: function (obj, status, error) {
				console.log('status: ' + status);
				console.log('error: ' + error);
			}
		});

		$('.modal #deleteCheckDialog').modal('hide');
	});

	$('.table #deletePosRef').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function (pos, status) {
			position = pos;
		});
		$('#deletePositionDialog').modal();
	});

	$('.modal #applyPosDelBtn').on('click', function (event) {
		event.preventDefault();

		var positionJSON = JSON.stringify(position);
		console.log(positionJSON);

		$.ajax({
			url: "/checks/positions",
			method: "DELETE",
			data: positionJSON,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				location.reload(true);
			},
			error: function (obj, status, error) {
				console.log('status: ' + status);
				console.log('error: ' + error);
			}
		});

        $('.modal #deletePositionDialog').modal('hide');
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