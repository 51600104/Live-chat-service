package org.o7planning.thymeleaf.service;


import java.util.List;

import org.o7planning.thymeleaf.model.Message;
import org.o7planning.thymeleaf.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LiveChatService {

	Flux<Message> getAllMessageById(String idsender, String idreciver, List<Message> messages);
	List<Message> getAllMessageById2(String idsender, String idreciver, List<Message> messages);

	Mono<User> findById(String id, List<User> users);

	Flux<User> removeUser(Flux<User> users, String idremove);

	List<Message> setAdminForMessage(List<Message> messages, User admin);
	
	Flux<User> getAllUsers(List<User> users);
	Flux<User> getAllAdmins(List<User> admins);
	
	List<Message> showChat(Flux<Message> message);
	
	List<User> test(List<User> test);
	
}
