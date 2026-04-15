package com.example.MyListView;

/*
 * ListView 中的菜品相关信息
 */
public class MenuListItem 
{
	private String id;
	private String name;
	private String pic;
	private String uid;
	private String material;
	private String like;
	private String collection;
	private String pinglun;
	private String uploadTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getPinglun() {
		return pinglun;
	}
	public void setPinglun(String pinglun) {
		this.pinglun = pinglun;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		int indexOf=uploadTime.lastIndexOf('.');
		this.uploadTime = uploadTime.substring(0, indexOf);
	}
	
	
}