package org.o7planning.thymeleaf.service;

import java.util.ArrayList;
import java.util.List;

import org.o7planning.thymeleaf.model.Message;
import org.o7planning.thymeleaf.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RemoteLiveChatServiceImp implements LiveChatService {

	@Override
	public Flux<Message> getAllMessageById(String idsender, String idreciver, List<Message> messages) {
		// TODO Auto-generated method stub
		Flux<Message> messagess = Flux.fromIterable(messages)
				.filter(fillmessage -> ((((fillmessage.getSender().getiD()).equals(idsender))
						&& ((fillmessage.getReceiver().getiD()).equals(idreciver)))
						|| (((fillmessage.getSender().getiD()).equals(idreciver))
								&& ((fillmessage.getReceiver().getiD()).equals(idsender)))));
		
		return messagess;
	}

	@Override
	public Flux<User> removeUser(Flux<User> users, String idremove) {
		// TODO Auto-generated method stub
		int i;
		List<User> temp = new ArrayList<User>();

		temp = users.collectList().block();

		for (i = 0; i < temp.size(); i++) {

			if (temp.get(i).getiD().equals(idremove)) {
				temp.remove(i);
			} else {
				users = Flux.just(temp.get(i));
			}

		}

		return users;
	}

	@Override
	public Mono<User> findById(String id, List<User> users) {
		// convert to flux and run filter with stream method
		Flux<User> fluxFromList = Flux.fromIterable(users);
		// TODO Auto-generated method stub
		return fluxFromList.filter(e -> e.getiD().equals(id)).next();
	}

	@Override
	public List<Message> setAdminForMessage(List<Message> messages, User admin) {
		// TODO Auto-generated method stub
		int i;
		for (i = 0; i < messages.size(); i++) {

			if ((messages.get(i).getSender().getRole().equals("user"))
					&& (messages.get(i).getReceiver().getRole().equals("temp"))) {
				messages.get(i).setReceiver(admin);
			}

		}

		return messages;
	}

	@Override
	public Flux<User> getAllUsers(List<User> users) {

		Flux<User> sequence = Flux.fromIterable(users).filter(user -> user.getRole().equals("user"));
		return sequence;
	}

	@Override
	public List<Message> showChat(Flux<Message> message) {
		// TODO Auto-generated method stub

		List<Message> listchat = message.collectList().block();
		return listchat;

	}

	@Override
	public Flux<User> getAllAdmins(List<User> admins) {
		// TODO Auto-generated method stub
		return Flux.fromIterable(admins).filter(user -> user.getRole().equals("admin"));
	}

	@Override
	public List<User> test(List<User> users) {

		User user = new User("Kim", "1", "user");// test
		User user2 = new User("Kim2", "2", "user");// test

		users.add(user);// test
		users.add(user2);// test
		// TODO Auto-generated method stub
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getRole());
		}

		return null;
	}

	@Override
	public List<Message> getAllMessageById2(String idsender, String idreciver, List<Message> messages) {
		// TODO Auto-generated method stub
		List<Message> temp = new ArrayList<Message>();
		for(int i = 0; i< messages.size(); i++) {
			if((((messages.get(i).getSender().getiD()).equals(idsender))
					&& ((messages.get(i).getReceiver().getiD()).equals(idreciver)))
					|| (((messages.get(i).getSender().getiD()).equals(idreciver))
							&& ((messages.get(i).getReceiver().getiD()).equals(idsender)))){
								
							temp.add(messages.get(i));	
								
							}
		}
		return temp;
	}

}
