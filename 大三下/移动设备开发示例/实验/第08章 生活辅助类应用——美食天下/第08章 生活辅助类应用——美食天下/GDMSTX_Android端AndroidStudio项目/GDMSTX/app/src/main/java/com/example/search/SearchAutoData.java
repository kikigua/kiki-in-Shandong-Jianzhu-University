package com.example.search;

public class SearchAutoData {

	private String type ="";
	private String content = "";
	
	public String getContent() {
		return content;
	}
	public SearchAutoData setContent(String content) {
		this.content = content;
		return this;
	}
	public String getType() {
		return type;
	}
	public SearchAutoData setType(String type) {
		this.type = type;
		return this;
	}
}
