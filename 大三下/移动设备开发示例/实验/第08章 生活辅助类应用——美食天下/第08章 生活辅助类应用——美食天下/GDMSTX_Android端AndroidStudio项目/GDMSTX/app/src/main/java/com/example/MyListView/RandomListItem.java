package com.example.MyListView;
/*
 * ListView 中的随拍相关信息
 */
public class RandomListItem
{
	private String id;
	private String img;
	private String head;
	private String cname;
	private String introduce;
	private String like;
	private String collection;
	private String pinglun;
	private String uploadTime;
	public RandomListItem(String id,String image,String head,String cname,
			String introduce,String like,String collection,String pinglun,String uploadTime)
	{
		this.id=id;			//id
		this.img=image;
		this.head=head;
		this.cname=cname;	//名称
		this.introduce=introduce;	//简介
		this.like=like;		//喜欢
		this.collection=collection; //收藏
		this.pinglun=pinglun;	///评论数
		this.uploadTime=uploadTime;
	}
	
	public RandomListItem() {
		
	}

	public String getUploadTime() {
		return uploadTime;
	}	
	public void setId(String id)
	{
		this.id=id;
	}
	public String getId()
	{
		return this.id;
	}
	public void setName(String cname)
	{
		this.cname=cname;
	}
	public String getName()
	{
		return cname;
	}
	public String getIndtroduce()
	{
		return this.introduce;
	}	
	public void setLike(String like)
	{
		this.like=like;
	}
	public String getlike()
	{
		return like;
	}	
	public void setCollection(String collection)
	{
		this.collection=collection;
	}
	public String getCollection()
	{
		return this.collection;
	}
	public void setPinglun(String pinglun)
	{
		this.pinglun=pinglun;
	}
	public String getPinglun()
	{
		
		return this.pinglun;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getLike() {
		return like;
	}

	public void setUploadTime(String uploadTime) {
		int indexOf=uploadTime.lastIndexOf('.');
		this.uploadTime = uploadTime.substring(0, indexOf);
	}
}