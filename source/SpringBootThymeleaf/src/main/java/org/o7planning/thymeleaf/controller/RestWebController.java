package org.o7planning.thymeleaf.controller;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.o7planning.thymeleaf.model.Message;
import org.o7planning.thymeleaf.model.User;
import org.o7planning.thymeleaf.service.RemoteLiveChatServiceImp;
import org.o7planning.tutorial.mongodb.MongoUtils;
import org.o7planning.tutorial.mongodb.MyConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/admin")
public class RestWebController {

	private String currentUseriD = "not";
	private String currentAdminiD = "not";

	private List<Message> tempmessages = new ArrayList<Message>();// use for flux example
	private int AdminON = 0;// test sau
	private List<User> tempgetuser = new ArrayList<User>();// store both of user and admin
	private List<User> temproleuser = new ArrayList<User>();// store both of user and admin
	private List<Message> messages = new ArrayList<Message>();// nhet list nay vao mot bien RemoteLiveChat
	
	
	 

	private RemoteLiveChatServiceImp fluxfunction = new RemoteLiveChatServiceImp();
	// Flux<String> stringFlux = Flux.just("Hello", "foo", "bar");//test

	@GetMapping(value = "/showuser") // done
	public List<User> showUSer() {
		
		

		temproleuser = (fluxfunction.getAllUsers(tempgetuser)).collectList().block();// change from list to flux users

		
		
		return temproleuser;

	}

	@PostMapping(value = "/adduser") // done
	public User postAddUserId(@RequestBody User user) {
		// name and id have insert on ajax js
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// Date date = new Date();
		// System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		Date todaysDate = new Date();

		DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = df2.format(todaysDate);
		user.setRole("user");
		user.setDate(strDate);
		user.setImage(user.getiD());
		// add current user, instance for one user, can develop more active
		tempgetuser.add(user);
		
		
		
		return user;

		// Create Response Object

	}

	@PostMapping(value = "/addadmin") // done
	public User postAddAdminId(@RequestBody User user) {
		
		
		
		// name and id have insert on ajax js

		Date todaysDate = new Date();

		DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = df2.format(todaysDate);

		user.setRole("admin");
		user.setDate(strDate);
		user.setImage(user.getiD());
		// take index to set the image and then set back to list
		tempgetuser.add(user);
	
		currentAdminiD = user.getiD();
		
		
		
		
		
		

		return user;

		// Create Response Object

	}

	@PostMapping(value = "/takeid") // done
	public User takeid(@RequestBody User user) {
		
		
		// add current user, instance for one admin, can develop more active

		currentUseriD = user.getiD();
		System.out.println("Here the id we have"+currentUseriD);
		//return a list with message
		return user;

	}

	@PostMapping(value = "/sendmessageadmin") // done
	public List<Message> sendmessageadmin(@RequestBody Message message) {
		
		
		Date todaysDate = new Date();

		DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = df2.format(todaysDate);
		
		if (currentUseriD.equals("not")) {

			System.out.println("I'm error here");

		} else {

			
			

			message.setSender((fluxfunction.findById(currentAdminiD , tempgetuser)).block());// set Sender
			message.setReceiver((fluxfunction.findById(currentUseriD, tempgetuser)).block());// ser Reciver
			message.setDate(strDate);
			int k = message.getCount();
			message.setCount(k + 1);
			messages.add(message);
			
		}
		
		
		//database
		MongoClient mongoClient = null;
		try {
			mongoClient = MongoUtils.getMongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	     // Kết nối tới Database
	     // (Không nhất thiết DB này phải tồn tại sẵn
	     // nó sẽ được tự động tạo ra nếu chưa tồn tại).
	     DB db = mongoClient.getDB("LIVECHATDATABASE");
	 
	     // Lấy ra Collection với tên Department.
	     // Không nhất thiết Collection này phải tồn tại trong DB.
	     DBCollection dept = db.getCollection("Messages");
	     
	     
	     // Insert Data 1
	     BasicDBObject doc1 = new BasicDBObject();
	     doc1.append("textMessage", message.getTextMessage());
	     doc1.append("date", strDate);
	     doc1.append("receiver_id", currentUseriD);
	     doc1.append("receiver_role", "user");
	     doc1.append("sender_id", currentAdminiD);
	     doc1.append("sender_role", "admin");
	     dept.insert(doc1);

		
	 return messages;

	}
	
	
	@GetMapping(value = "/loadchatadmin")//done
	public List<Message> loadchatadminn() {
	
		 return (fluxfunction.getAllMessageById(currentAdminiD, currentUseriD, messages)).collectList().block();
	}
	
	
	
	@PostMapping(value = "/sendmessageclient") 
	public List<Message> sendmessageclient(@RequestBody Message message) {
		
		Date todaysDate = new Date();

		DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String strDate = df2.format(todaysDate);
		
		System.out.println("We have cleint messainge"+message.getTextMessage());
		
		if (currentAdminiD.equals("not")) {

			System.out.println("I'm error here");

		} else {

			

			message.setSender((fluxfunction.findById(message.getOwner() , tempgetuser)).block());// set Sender
			message.setReceiver((fluxfunction.findById(currentAdminiD, tempgetuser)).block());// ser Reciver
			message.setDate(strDate);
			int k = message.getCount();
			message.setCount(k + 1);
			messages.add(message);
			
		}
		
		
		//database
		//database
		MongoClient mongoClient = null;
		try {
			mongoClient = MongoUtils.getMongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	     // Kết nối tới Database
	     // (Không nhất thiết DB này phải tồn tại sẵn
	     // nó sẽ được tự động tạo ra nếu chưa tồn tại).
	     DB db = mongoClient.getDB("LIVECHATDATABASE");
	 
	     // Lấy ra Collection với tên Department.
	     // Không nhất thiết Collection này phải tồn tại trong DB.
	     DBCollection dept = db.getCollection("Messages");
	     
	     
	     // Insert Data 1
	     BasicDBObject doc1 = new BasicDBObject();
	     doc1.append("textMessage", message.getTextMessage());
	     doc1.append("date", strDate);
	     doc1.append("receiver_id", currentAdminiD);
	     doc1.append("receiver_role", "admin");
	     doc1.append("sender_id", message.getOwner());
	     doc1.append("sender_role", "user");
	     dept.insert(doc1);
		
		

		
	 return (fluxfunction.getAllMessageById(message.getOwner(), currentAdminiD, messages)).collectList().block();

	}
	
	
	@PostMapping(value = "/loadchatclient")
	public List<Message> loadchatclient(@RequestBody Message message) {
		
		System.out.println("we have the loadchatclient id "+message.getOwner());
	
		 return (fluxfunction.getAllMessageById(message.getOwner(), currentAdminiD, messages)).collectList().block();
	}

	
	
	
	/*
	 * List<Customer> cust = new ArrayList<Customer>();
	 * 
	 * @GetMapping(value = "/all") public Response getResource() { Response response
	 * = new Response("Done", cust); return response; }
	 * 
	 * @PostMapping(value = "/save") public Response postCustomer(@RequestBody
	 * Customer customer) { cust.add(customer);
	 * 
	 * // Create Response Object Response response = new Response("Done", customer);
	 * return response; }
	 * 
	 * /* Flux<Employee> employees = Flux.just( new Employee(1, "Andrew", "Roberts",
	 * 31, MALE), new Employee(2, "Lisa", "Brooks", 25, FEMALE), new Employee(3,
	 * "Perry", "Jones", 45, MALE), new Employee(4, "Marc", "Webster", 50, MALE),
	 * new Employee(5, "Vivian", "Sidney", 42, FEMALE), new Employee(6, "Ramon",
	 * "Brown", 21, MALE), new Employee(7, "Beverly", "Williams", 36, FEMALE), new
	 * Employee(8, "Rosie", "Stevanson", 35, FEMALE)
	 * ).delayElements(Duration.ofSeconds(2)); return new
	 * RemoteEmployeeServiceImpl(employees); } }
	 */

}