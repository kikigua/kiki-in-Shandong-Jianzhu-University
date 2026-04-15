package Tree;
/*使用孩子-兄弟表示法作为树的存储结构，求树中结点的个数、求树的深度、求结点的孩子个数、
 * 实现树的先根、后根和层次遍历。
 */
public class TestCSTree {
	public static void main(String[] args) {
		CSTreeNode D=new CSTreeNode('D');
		CSTreeNode E=new CSTreeNode('E');
		CSTreeNode C=new CSTreeNode('C',D,E);
		CSTreeNode B=new CSTreeNode('B',null,C);
		CSTreeNode A=new CSTreeNode('A',B,null);
		CSTree tree=new CSTree(A);
		System.out.print("求结点的个数");
		int a,b,c,d;
		a=tree.countNode(A);
		System.out.println("结点个数"+a);
		b=tree.childNode(A);
		System.out.println("孩子结点树"+b);
		c=tree.treeDepth(A);
		System.out.println("树的深度"+c);
		System.out.println("先根遍历");
		tree.preRootTraverse(A);
		System.out.println();
		System.out.println("后根遍历");
		tree.postRootTraverse(A);
		System.out.println();
		System.out.println("层次遍历");
		tree.levelTraverse();
}
}
