package ks1;

public class KeyType implements Comparable<KeyType>{
	public int key;//关键字
	public KeyType[] bub;
	public KeyType(){
		
	}
    public KeyType(int key){
    this.key=key;	
    }
	public String toString() {//覆盖toString()方法
		return key+" ";
	}
	public int compareTo(KeyType another){//覆盖compare接口中比较关键字大小的方法
		int thisVal=this.key;
		int anotherVal=another.key;
		return (thisVal<anotherVal?-1:(thisVal==anotherVal?0:1));
	}
}
