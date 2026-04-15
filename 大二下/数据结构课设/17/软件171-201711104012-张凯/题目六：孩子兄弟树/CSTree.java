package Tree;
public class CSTree {
	private CSTreeNode root;
	public CSTree() {
		this(null);
	}

	public CSTree(CSTreeNode root) {
		this.root = root;
	}
	public void preRootTraverse(CSTreeNode T) {
		if (T != null) {
			System.out.print(T.data); 
			preRootTraverse(T.firstchild);
			preRootTraverse(T.nextsibling);
		}
	}
	public void postRootTraverse(CSTreeNode T) {
		if (T != null) {
			postRootTraverse(T.firstchild);
			System.out.print(T.data); 
			postRootTraverse(T.nextsibling);
		}
	}
	
	public void levelTraverse() {
		CSTreeNode T = root;
		if (T != null) {
			LinkQueue L = new LinkQueue();// 构造队列
			L.offer(T);// 根结点入队列
			while (!L.isEmpty())
				for (T = (CSTreeNode) L.poll(); T != null; T = T.nextsibling) {// 访问结点及其所有兄弟结点
					System.out.print(T.data); // 访问结点
					if (T.firstchild != null)// 第一个孩子结点非空入队列
						L.offer(T.firstchild);
				}
		}
	}

	public CSTreeNode getRoot() {
		return root;
	}

	public void setRoot(CSTreeNode root) {
		this.root = root;
	}
	public int treeDepth(CSTreeNode T) {
		if(T!=null) {
			int h1=treeDepth(T.firstchild);
			int h2=treeDepth(T.nextsibling);
			return (h1+1>h2?h1+1:h2);
		}
		return 0;
		}
	
	public int countNode(CSTreeNode T) {
		int count=0;
		
		if(T!=null) {
			count++;
			count+=countNode(T.firstchild);
			count+=countNode(T.nextsibling);
		}
		return count;
	}
	public int childNode(CSTreeNode T) {
		int count=0;
		if(T!=null) {
			if(T.firstchild!=null)
			count++;
			count+=childNode(T.firstchild);
			count+=childNode(T.nextsibling);
			return count;
		}
		return 0;
	}
}
