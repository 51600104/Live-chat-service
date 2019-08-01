package org.o7planning.thymeleaf.model;

import java.util.*;

public class Message {
	private String textMessage;
	private String date;
	private User receiver;
	private User sender;
	private int count;
	private String owner;//identify the sender

	public Message() {

	}

	public Message(User sender, User receiver, String textMessage) {
		this.sender = sender;
		this.receiver = receiver;
		this.textMessage = textMessage;
	}

	// function commu

	public String getTextMessage() {
		return this.textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
