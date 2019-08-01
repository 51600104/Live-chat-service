//////////OKKKKKKKKKKKKK
var valueofbutton;
$(document).ready(function() {

					
					var intervalchat = null;

					// GET REQUEST
					$("#customerForm").submit(function(event) {
						// Prevent the form from submitting via the browser.
						event.preventDefault();

						if (onload === 0) {
							onload = onload + 1;

							addClientajaxPost();
							
						}

					});

					$("#sendchatForm").submit(function(event) {
						event.preventDefault();
						chatChatajaxPost();
						intervalchat = setInterval(LoadChatClientajaxPost,3000);
						 resetData();
						 $('#loop')
							.stop()
							.animate(
									{
										scrollTop : $('#loop')[0].scrollHeight
									});
						
						

					});

					function chatChatajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							textMessage : $("#btn-input").val(),
							owner : $("#iduser").html(),
						}

						// DO POST
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/api/admin/sendmessageclient",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(result) {
								
								
								
								
								
								var x = "";
								$
										.each(
												result,
												function(i, customer) {

													if (customer.sender.role === 'user') {

														var customer2 = '<div class="chatbox__body__message chatbox__body__message--left">		'
																+ '					<div class="chatbox_timing">				'
																+ '				<ul>		'
																+ '							<li><a href="#"><i class="fa fa-calendar"></i>							'
																+customer.date+'</a></li>			'
																+ '						<li><a href="#"><i class="fa fa-clock-o"></i></a></a></li>				'
																+ '				</ul>			'
																+ '				</div>		'
																+ '					<img src="https://robohash.org/'+ customer.sender.image+'" alt="Picture">'		
																+ '				<div class="clearfix"></div>		'
																+ '					<div class="ul_section_full">				'
																+ '				<ul class="ul_msg" value = "kem">			'
																+ '						<li><strong>'+customer.sender.name+'</strong></li>			'
																+ '						<li>'+customer.textMessage+'				'
																+ '					</li>			'
																+ '					</ul>		'
																+ '						<div class="clearfix"></div>				'
																+ '				<ul class="ul_msg2">				'
																+ '			<li><a href="#"><i class="fa fa-pencil"></i> </a></li>			'
																+ '				<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>						</ul>				</div>				</div>';
														
														
						
													} else {
														// watch out
														var customer2 = '<div class="chatbox__body__message chatbox__body__message--right">		'
															+ '					<div class="chatbox_timing">				'
															+ '				<ul>		'
															+ '							<li><a href="#"><i class="fa fa-calendar"></i>							'
															+ '				'+customer.date+'</a></li>			'
															+ '						<li><a href="#"><i class="fa fa-clock-o"></i></a></a></li>				'
															+ '				</ul>			'
															+ '				</div>		'
															+ '					<img src="https://robohash.org/'+customer.sender.image+'" alt="Picture">	'+'				<div class="clearfix"></div>		'
															+ '					<div class="ul_section_full">				'
															+ '				<ul class="ul_msg" value = "kem">			'
															+ '						<li><strong>'+customer.sender.name+'</strong></li>			'
															+ '						<li>'+customer.textMessage+'				'
															+ '					</li>			'
															+ '					</ul>		'
															+ '						<div class="clearfix"></div>				'
															+ '				<ul class="ul_msg2">				'
															+ '			<li><a href="#"><i class="fa fa-pencil"></i> </a></li>			'
															+ '				<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>						</ul>					</div>				</div>';

													}

													x = x + customer2;

												});
								document
										.getElementById("msg_chat_form").innerHTML = x;
								
								console.log("user load chat success");
								$('#msg_chat_form')
										.stop()
										.animate(
												{
													scrollTop : $('#msg_chat_form')[0].scrollHeight
												});
								
								console.log("user put and load chat success");
							},
							error : function(e) {
								alert("Error!")
								console.log("ERROR: ", e);
							}
						});

						// Reset FormData after Posting

					}

					
					
					function LoadChatClientajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							textMessage : $("#btn-input").val(),
							owner : $("#iduser").html(),
						}

						// DO POST
						$.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/api/admin/loadchatclient",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(result) {
								
								
								
								
								
								var x = "";
								$
										.each(
												result,
												function(i, customer) {

													if (customer.sender.role === 'user') {

														var customer2 = '<div class="chatbox__body__message chatbox__body__message--left">		'
																+ '					<div class="chatbox_timing">				'
																+ '				<ul>		'
																+ '							<li><a href="#"><i class="fa fa-calendar"></i>							'
																+customer.date+'</a></li>			'
																+ '						<li><a href="#"><i class="fa fa-clock-o"></i></a></a></li>				'
																+ '				</ul>			'
																+ '				</div>		'
																+ '					<img src="https://robohash.org/'+ customer.sender.image+'" alt="Picture">'		
																+ '				<div class="clearfix"></div>		'
																+ '					<div class="ul_section_full">				'
																+ '				<ul class="ul_msg" value = "kem">			'
																+ '						<li><strong>'+customer.sender.name+'</strong></li>			'
																+ '						<li>'+customer.textMessage+'				'
																+ '					</li>			'
																+ '					</ul>		'
																+ '						<div class="clearfix"></div>				'
																+ '				<ul class="ul_msg2">				'
																+ '			<li><a href="#"><i class="fa fa-pencil"></i> </a></li>			'
																+ '				<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>						</ul>				</div>				</div>';
														
														
						
													} else {
														// watch out
														var customer2 = '<div class="chatbox__body__message chatbox__body__message--right">		'
															+ '					<div class="chatbox_timing">				'
															+ '				<ul>		'
															+ '							<li><a href="#"><i class="fa fa-calendar"></i>							'
															+ '				'+customer.date+'</a></li>			'
															+ '						<li><a href="#"><i class="fa fa-clock-o"></i></a></a></li>				'
															+ '				</ul>			'
															+ '				</div>		'
															+ '					<img src="https://robohash.org/'+customer.sender.image+'" alt="Picture">	'+'				<div class="clearfix"></div>		'
															+ '					<div class="ul_section_full">				'
															+ '				<ul class="ul_msg" value = "kem">			'
															+ '						<li><strong>'+customer.sender.name+'</strong></li>			'
															+ '						<li>'+customer.textMessage+'				'
															+ '					</li>			'
															+ '					</ul>		'
															+ '						<div class="clearfix"></div>				'
															+ '				<ul class="ul_msg2">				'
															+ '			<li><a href="#"><i class="fa fa-pencil"></i> </a></li>			'
															+ '				<li><a href="#"><i class="fa fa-trash chat-trash"></i></a></li>						</ul>					</div>				</div>';

													}

													x = x + customer2;

												});
								document
										.getElementById("msg_chat_form").innerHTML = x;
								
								
								 $('#loop')
									.stop()
									.animate(
											{
												scrollTop : $('#loop')[0].scrollHeight
											});
								
								
			
								
								console.log("user put and load chat continue success");
							},
							error : function(e) {
								
								console.log("ERROR: ", e);
							}
						});

						// Reset FormData after Posting

					}
					
					
					

					function resetData() {
						$("#btn-input").val("");
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