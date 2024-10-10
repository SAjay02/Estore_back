package com.Student.Estore.commonresponse;

public class Response {
	
	private int status;
	private Object data;
	private Object error;
	private String description;
	private String suggestion;
	
	public Response() {
		super();
		this.status = status;
		this.data = data;
		this.error = error;
		this.description = description;
		this.suggestion = suggestion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	
}
