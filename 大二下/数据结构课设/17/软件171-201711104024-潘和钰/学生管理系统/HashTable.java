package 选做题三;
//采用链地址法处理冲突
public class HashTable<E> {
public LinkList[] table;
public 	HashTable(int size) {
	this.table=new LinkList[size];
	for(int i=0;i<table.length;i++) {
		table[i]=new LinkList();
	}
}
public int hash(int key) {
	return key%table.length;//所选哈希函数
}
public void insert(E element)throws Exception {
	int key=element.hashCode();//关键字为学号
	int i=hash(key);
	table[i].insert(0, element);
}

public void display() {
	for(int i=0;i<table.length;i++) {
		System.out.print("table["+ i +"]=");
		table[i].display();
	}
}
public Object search(E element)throws Exception{
	int key=element.hashCode();
	int i = hash(key);
	int index = table[i].indexOf(element); // 返回元素在单链表中的位置
	if (index >= 0) {
		return table[i].get(index); // 返回在单链表中找到的结点
	}
	else {
		return null;
	}
}

public boolean contain(E element) throws Exception { // 以查找结果判断哈希表是否包含指定对象，若包含返回true，否则返回false
	return this.search(element) != null;
}

}
