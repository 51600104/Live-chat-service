//////////OKKKKKKKKKKKKK
var valueofbutton;
$(document)
		.ready(
				function() {

					
					var intervalchat = null;
					var intervalloaduser = null

					// GET REQUEST
					var onload = 0;

					// SUBMIT FORM
					$("#customerForm").submit(function(event) {
						// Prevent the form from submitting via the browser.
						event.preventDefault();

						if (onload == 0) {
							onload = onload + 1;

							addClientajaxPost();
							intervalloaduser = setInterval(ajaxGet, 3000);
						}

					});
					
					
					$("#getAllCustomerId").click(function(event) {
						event.preventDefault();
						ajaxGet();
					});

					$("#id_Form").submit(function(event) {
						
						clearInterval(intervalchat);// end if change id
						event.preventDefault();
						intervalchat = setInterval(loadchatAdminajaxGet, 2000);
						resetDatamsgform();
						pickUpIdAdminajaxPost(valueofbutton);
						

					});

					$("#sendchatForm").submit(function(event) {
						event.preventDefault();
						chatAdminajaxPost();
						resetDatainputform();
						$('#msg_historic')
						.stop()
						.animate(
								{
									scrollTop : $('#msg_historic')[0].scrollHeight
								});
						

					});

					// DO GET
					function ajaxGet() {
						$
								.ajax({
									type : "GET",
									url : "/api/admin/showuser",
									success : function(result) {
										console.log("Success: ", result);
										var x = "";
										$
												.each(
														result,
														function(i, customer) {

															var customer1 = '<button type="submit" onClick="m(this.value)" id="'
																	+ customer.iD
																	+ '" value = "'
																	+ customer.iD
																	+ '"><div class="chat_list active_chat"> <div class="chat_people"> <div class="chat_img"> <img src="https://robohash.org/'
																	+ customer.image
																	+ '" alt="sunil"> </div> <div class="chat_ib"> <h5>'
																	+ customer.name
																	+ '<span class="chat_date">'
																	+ customer.date
																	+ '</span></h5> <p>Test, which is a new approach to have all solutions  astrology under one roof.</p></div> </div> </div></button>';

															x = x + customer1;

														});
										document.getElementById("id_Form").innerHTML = x;

									},
									error : function(e) {
										$("#showuserchat").html(
												"<strong>Error</strong>");
										console.log("ERROR: ", e);
									}
								});
					}
					
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

					function chatAdminajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							textMessage : $("#text_Message_input").val(),
						}

						// DO POST
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/api/admin/sendmessageadmin",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(result) {

								console.log("admin put chat success");
							},
							error : function(e) {
								alert("Error!")
								console.log("ERROR: ", e);
							}
						});

						// Reset FormData after Posting

					}

					function pickUpIdAdminajaxPost(valueofbutton) {

						// PREPARE FORM DATA
						var formData = {
							iD : $('#' + valueofbutton).attr('value'),
						}

						// DO POST
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/api/admin/takeid",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(result) {

								console.log("Success load chat with id");
								loadchatAdminajaxGet();
							},
							error : function(e) {
								alert("Error!")
								console.log("ERROR: ", e);
							}
						});

						// Reset FormData after Posting

					}

					function loadchatAdminajaxGet() {

						$
								.ajax({
									type : "GET",
									url : "/api/admin/loadchatadmin",
									success : function(result) {
										var x = "";
										$
												.each(
														result,
														function(i, customer) {

															if (customer.sender.role === 'admin') {
																var customer1 = '<div class="outgoing_msg">'
																		+ '<div class="sent_msg">'
																		+ '<p>'
																		+ customer.textMessage
																		+ '</p>'
																		+ '<span class="time_date">'
																		+ customer.date
																		+ '</span>'
																		+ '</div></div>';
															} else {
																	//watch out
																var customer1 = '<div class="incoming_msg">'
																		+ '<div class="incoming_msg_img"> <img src="https://robohash.org/'
																		+ customer.sender.image
																		+ '" alt="sunil"> </div>'
																		+ '<div class="received_msg">'
																		+ '<div class="received_withd_msg">'
																		+ '<p>'
																		+ customer.textMessage
																		+ '</p>'
																		+ '<span class="time_date">'
																		+ customer.date
																		+ '</span></div>'
																		+ '</div>'
																		+ '</div>';

															}

															x = x + customer1;

														});
										document
												.getElementById("msg_chat_form").innerHTML = x;
										
										
										$('#msg_historic')
										.stop()
										.animate(
												{
													scrollTop : $('#msg_historic')[0].scrollHeight
												});
										

									},
									error : function(e) {
										$("#msg_chat_form").html(
												"<strong>Error</strong>");
										console.log("ERROR: ", e);
									}
								});

					}

					function resetDatamsgform() {
						var x = "";
						x = x + '<form  id="msg_chat_form"></form>';
						document.getElementById("msg_historic").innerHTML = x;
					}
					
					function resetDatainputform() {
						$("#text_Message_input").val("");
						
					}
					

					function test(t) { // defining a function
						if (t === undefined) { // if t=undefined, call tt
							console.log(t) // call t
						}
						return t;
					}

				})

function m(value) {

	valueofbutton = value;
}