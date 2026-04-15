package Tree;

/**
 * 
 * 二叉链式(孩子 - 兄弟)存储结构下的树结点
 * 
 */
public class CSTreeNode {
	public Object data;// 结点的数据元素

	public CSTreeNode firstchild, nextsibling; // 孩子兄弟结点

	public CSTreeNode() {// 构造一个空结点
		this(null);
	}

	public CSTreeNode(Object data) {// 构造一棵孩子及兄弟为空的结点
		this(data, null, null);
	}

	public CSTreeNode(Object data, CSTreeNode firstchild, CSTreeNode nextsibling) {// 构造一棵数据元素和孩子及兄弟都不为空的结点
		this.data = data;
		this.firstchild = firstchild;
		this.nextsibling = nextsibling;
	}

	
}
