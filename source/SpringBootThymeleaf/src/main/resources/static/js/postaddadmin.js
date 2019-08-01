$(document).ready(function() {
	var onload = 0;

	// SUBMIT FORM
	$("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();

		if (onload == 0) {
			onload = onload + 1;

			addClientajaxPost();
		}

	});
	
	function IDGenerator() {

		this.length = 8;
		this.timestamp = +new Date;

		var _getRandomInt = function(min, max) {
			return Math.floor(Math.random() * (max - min + 1)) + min;
		}

		this.generate = function() {
			var ts = this.timestamp.toString();
			var parts = ts.split("").reverse();
			var id = "";

			for (var i = 0; i < this.length; ++i) {
				var index = _getRandomInt(0, parts.length - 1);
				id += parts[index];
			}

			return id;
		}

	}
	
	var generator = new IDGenerator();

	function addClientajaxPost() {

	
		document.getElementById("iduser").innerHTML = generator.generate();

		// PREPARE FORM DATA
		var formData = {
			name : $("#username").val(),
			iD : $("#iduser").html(),

		}

		// DO POST
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/admin/addadmin",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {

				console.log("add success admin");
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});

		// Reset FormData after Posting

	}

})