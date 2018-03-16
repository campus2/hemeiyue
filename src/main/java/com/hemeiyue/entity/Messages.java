package com.hemeiyue.entity;

/**
 * 
 * 
 * @author cedo
 * 
 * @date 2018-03-14
 */
public class Messages {
    private Integer id;

    private String title;

    private String content;

    private Admin sender;

    private Integer statusInSender;

    private Users recipient;

    private Integer statusInRecipient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Admin getSender() {
		return sender;
	}

	public void setSender(Admin sender) {
		this.sender = sender;
	}

	public Integer getStatusInSender() {
		return statusInSender;
	}

	public void setStatusInSender(Integer statusInSender) {
		this.statusInSender = statusInSender;
	}

	public Users getRecipient() {
		return recipient;
	}

	public void setRecipient(Users recipient) {
		this.recipient = recipient;
	}

	public Integer getStatusInRecipient() {
		return statusInRecipient;
	}

	public void setStatusInRecipient(Integer statusInRecipient) {
		this.statusInRecipient = statusInRecipient;
	}

    
}