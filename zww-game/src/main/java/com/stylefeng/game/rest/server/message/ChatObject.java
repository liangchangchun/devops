package com.stylefeng.game.rest.server.message;

public class ChatObject {
    private Integer userId;
    private String message;

    public ChatObject() {
    }

    public ChatObject(Integer userId, String message) {
        super();
        this.userId = userId;
        this.message = message;
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}