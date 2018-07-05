$(document).ready(function () {

	console.log("REFRESH")


	$('#deletePosRef').on('click', function (event) {
		event.preventDefault();

		var href = $(this).attr('href');
		$('#posId').attr('value', href);
		$('#deletePositionDialog').modal();
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