/**
 * author: Timothy
 */
package com.example.usatodayxmlparser;

public class UsaTodayFeed {
	String title;
	String url;
	String description;
	
	public UsaTodayFeed(){
		
	}
	
	public UsaTodayFeed(String title, String url, String description){
		this.title = title;
		this.url = url;
		this.description = description;
	}
	
	//set methods
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	//get methods
	public String getTitle(){
		return title; 
	}
	
	public String getUrl(){
		return url;
	}
	public String getDescription(){
		return description;
	}
	@Override
    public String toString() {
        return title + "\n" + url;
    }
}
