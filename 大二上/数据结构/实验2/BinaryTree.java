package tree_01;

import stack_01.LinkedStack;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> implements Cloneable {
    private Node<T> root;

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public BinaryTree() {// 建立空树

    }

    // 使用前序和中序遍历的结果，构造一颗数据为Character的二叉树，用于构造测试用的二叉树
    @SuppressWarnings("unchecked")
    public BinaryTree(String preString, String inString) {    // preStart 左子树在preString的开始位置，inStart 右子树在inString的开始位置
        if (preString.length() == 0) // 如果不特殊处理，makeBitree将崩溃
            root = null;
        else
            root = (Node<T>) makeBitree(preString, inString, 0, 0, preString.length());
    }

    // 在左子树、右子树上加上根结点，得到新的二叉树，注意：leftBiTree和rightBiTree不能为null
    // 用于手工逐个的建立想要的二叉树
    public BinaryTree(T data, BinaryTree<T> leftBiTree, BinaryTree<T> rightBiTree) {
        root = new Node<>(data, leftBiTree.root, rightBiTree.root);
        leftBiTree.root = rightBiTree.root = null;// 夺取了leftBiTree和rightBiTree这2棵二叉树的结点
    }

    // 常见的System.out.print的先序、中序和后序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<T> t) {
        if (t == null)
            return;
        // 输出结点t的数据
        System.out.println(t.data);
        preOrder(t.left);
        preOrder(t.right);
    }

    // 下面的写法效率高一些，因为空树不执行preOrder，对n个结点的二叉树，减少了n+1次对空树调用preOrder
    // public void preOrder() {
    // if (root == null)
    // return;
    // preOrder(root);
    // }
    //
    // private void preOrder(Node<T> t) {
    // // 输出结点t的数据
    // System.out.println(t.data);
    // if (t.left != null)
    // preOrder(t.left);
    // if (t.right != null)
    // preOrder(t.right);
    // }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<T> t) {
        if (t == null)
            return;
        inOrder(t.left);
        // 输出结点t的数据
        System.out.println(t.data);
        inOrder(t.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<T> t) {
        if (t == null)
            return;
        postOrder(t.left);
        postOrder(t.right);
        // 输出结点t的数据
        System.out.println(t.data);
    }

    // 使用队列
    public void levelOrder() {
        if (root == null)
            return;
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> t = queue.poll();
            // 输出结点t的数据
            System.out.println(t.data);
            if (t.left != null)
                queue.offer(t.left);
            if (t.right != null)
                queue.offer(t.right);
        }
    }

    // 带处理方法的先序、中序和后序遍历
    public void preOrder(Consumer<T> fun) {
        preOrder(root, fun);
    }

    private void preOrder(Node<T> t, Consumer<T> fun) {
        if (t == null)
            return;
        // 处理结点t的数据
        fun.accept(t.data);
        preOrder(t.left, fun);
        preOrder(t.right, fun);
    }

    public void inOrder(Consumer<T> fun) {
        inOrder(root, fun);
    }

    private void inOrder(Node<T> t, Consumer<T> fun) {
        if (t == null)
            return;
        inOrder(t.left, fun);
        // 处理结点t的数据
        fun.accept(t.data);
        inOrder(t.right, fun);
    }

    public void postOrder(Consumer<T> fun) {
        postOrder(root, fun);
    }

    private void postOrder(Node<T> t, Consumer<T> fun) {
        if (t == null)
            return;
        postOrder(t.left, fun);
        postOrder(t.right, fun);
        // 处理结点t的数据
        fun.accept(t.data);
    }

    public void levelOrder(Consumer<T> fun) {
        if (root == null)
            return;
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> t = queue.poll();
            // 处理结点t的数据
            fun.accept(t.data);
            if (t.left != null)
                queue.offer(t.left);
            if (t.right != null)
                queue.offer(t.right);
        }
    }

    public void preOrderNonRecursively() {
        if (root == null)
            return;
        Deque<Node<T>> stackNode = new ArrayDeque<>();
        Node<T> p = root;
        for (;;) {// 对变量p代表的每棵二叉树做相同的操作
            while (p != null) {// 向左不断的分解二叉树，将以后要访问的各子二叉树逐次入栈
                System.out.println(p.data);
                if (p.right != null)// 栈的数据不能为null
                    stackNode.push(p.right);
                p = p.left;
            }
            // 到此，p代表的二叉树分解到空二叉树，处理完毕
            if (stackNode.isEmpty())
                return;
            p = stackNode.pop();// 从栈中取出下1棵待处理的二叉树
        }
    }

    public void inOrderNonRecursively() {
        if (root == null)
            return;
        Deque<Node<T>> stackNode = new ArrayDeque<>();
        Node<T> p = root;
        for (;;) {// 对变量p代表的每棵二叉树做相同的操作
            while (p != null) {// 向左不断的分解二叉树，将以后要访问的各子二叉树逐次入栈
                stackNode.push(p);
                p = p.left;
            }
            // 到此，p代表的二叉树分解到空二叉树，处理完毕
            if (stackNode.isEmpty())
                return;
            p = stackNode.pop(); // 从栈中取出下1棵待处理的二叉树
            System.out.println(p.data); // 访问它的根结点
            p = p.right;// 继续处理它的右子树
        }
    }

    public void postOrderNonRecursively() {
        if (root == null)
            return;
        Deque<Node<T>> stack = new LinkedList<>();// 尝试类库链式实现的栈
        Node<T> p = root;
        for (;;) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            // 二叉树已经分解到空二叉树，处理栈顶代表的二叉树
            while ((p = stack.peek().right) == null) {// 下1棵待处理的二叉树的右子树为空
                do {
                    p = stack.pop(); // p取出栈顶代表的树，它的右子树为空，或右子树不为空但已经处理完毕，看2个while语句的条件
                    System.out.println(p.data);
                    if (stack.isEmpty())
                        return;
                } while (stack.peek().right == p);// 栈顶代表的二叉树的右子树已经处理完毕
                // 如果刚处理完毕的二叉树是栈顶代表的二叉树的左子树，循环
            }
            // 继续处理栈顶代表的二叉树的右子树，循环
        }
    }

    // 模拟1个结点需要进入3次的遍历过程
    // 易于理解，但入、出栈的次数多，时间和空间效率低，不具有实用价值，但可以学习如使用2个栈完成任务，使用peek避免不必要的入栈和出栈
    // 将 System.out.println(p.data);放到 case "1"，case "2"就分别是前序和中序遍历
    public void postOrderNonRecursively1() {
        if (root == null)
            return;
        Deque<Node<T>> stackNode = new ArrayDeque<>();
        Deque<String> stackFlag = new ArrayDeque<>();
        stackNode.push(root);
        stackFlag.push("1");
        while (!stackNode.isEmpty()) {
            Node<T> p = stackNode.peek();
            String flag = stackFlag.pop();
            switch (flag) {
                case "1":
                    stackFlag.push("2");
                    if (p.left != null) {
                        stackNode.push(p.left);
                        stackFlag.push("1");
                    }
                    break;
                case "2":
                    stackFlag.push("3");
                    if (p.right != null) {
                        stackNode.push(p.right);
                        stackFlag.push("1");
                    }
                    break;
                case "3":
                    System.out.println(p.data);
                    stackNode.pop();
                    break;
            }
        }
    }

    // 求节点个数
    public int countNode() {
        return countNode(root);
    }

    private int countNode(Node<T> t) {
        if (t == null)
            return 0;
        return countNode(t.left) + countNode(t.right) + 1;
    }

    // 求叶子节点个数
    public int countLeafNode() {
        return countLeafNode(root);
    }

    private int countLeafNode(Node<T> t) {
        if (t == null)
            return 0;
        if (t.left == null && t.right == null)
            return 1;
        return countLeafNode(t.left) + countLeafNode(t.right);
    }

    // 求二叉树的深度
    public int depth() {
        return depth(root);
    }

    private int depth(Node<T> t) {
        if (t == null)
            return 0;
        int left = depth(t.left);
        int right = depth(t.right);
        return left > right ? left + 1 : right + 1;
    }

    // 二叉树中查找
    public Node<T> search(T x) {
        return search(root, x);
    }

    private Node<T> search(Node<T> t, T x) {
        if (t == null)
            return null;
        if (t.data.equals(x))
            return t;
        Node<T> result = search(t.left, x);
        if (result != null)
            return result;
        return search(t.right, x);
    }

    // 判断二棵二叉树是不是相等
    public boolean isEqual(BinaryTree<T> rhd) {
        if (this == rhd)
            return true;
        return isEqual(this.root, rhd.root);
    }

    private boolean isEqual(Node<T> t1, Node<T> t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (!t1.data.equals(t2.data))
            return false;
        return isEqual(t1.left, t2.left) && isEqual(t1.right, t2.right);
    }
//！！！
    public void toList(List<T> list) {//新加方法一
        toList(root, list);//此处的tolist方法是调用的方法二
    }

    private void toList(Node<T> t,List<T> list) {//新加方法二
        if(t!=null) {
            list.add(t.data);
            toList(t.left,list);
            toList(t.right,list);
        }
    }
    //先根遍历操作实现的递归算法
    public void preRootTraverse(Node T){
        if (T!=null){
            System.out.println(T.data);//访问根结点
            preRootTraverse(T.left);//先根遍历左子树
            preRootTraverse(T.right);//先根遍历右子树
        }
    }
    //先根遍历操作实现的非递归算法
    public void preRootTraverse(){
        Node T=root;
        if (T!=null){
            LinkedStack S=new LinkedStack();//构造栈
            S.push(T);                      //根结点入栈
            while (!S.isEmpty()){
                T =(Node) S.pop();          //移除栈顶结点，并返回其值
                System.out.println(T.data);//访问结点
                while(T!=null){
                    if (T.left!=null){      //访问左孩子
                        System.out.println(T.left.data);//访问结点
                    }
                    if (T.right!=null){     //右孩子非空入栈
                        S.push(T.right);
                    }
                    T=T.right;
                }
            }
        }
    }
    //中根遍历操作实现的递归算法
    public void inRootTraverse(Node T){
        if (T!=null){
            inRootTraverse(T.left);
            System.out.println(T.data);
            inRootTraverse(T.right);
        }
    }
    //中根遍历操作实现的非递归算法
    public void inRootTraverse(){
        Node T=root;
        if (T!=null){
            LinkedStack S=new LinkedStack();       //构造链栈
            S.push(T);                             //根结点入栈
            while(!S.isEmpty()){
                if (S.peek()!=null){               //将栈顶结点的左孩子结点相继入栈
                    S.push(((Node)S.peek()).left);
                }
                S.pop();                            //空结点退栈
                if (!S.isEmpty()){
                    T=(Node) S.pop();               //移除栈顶结点，并返回其值
                    System.out.println(T.data);     //访问结点
                    S.push(T.right);                //结点的右孩子入栈
                }
            }
        }
    }
    //后根遍历操作实现的递归算法
    public void postRootTraverse(Node T){
        if (T!=null){
            postRootTraverse(T.left);
            postRootTraverse(T.right);
            System.out.println(T.data);
        }
    }
    //后根遍历操作实现的非递归算法
    public void postRootTraverse(){
        Node T=root;
        if (T!=null){
                 LinkedStack S=new LinkedStack();
                 S.push(T);
                 Boolean flag;
                 Node p=null;
                 while(!S.isEmpty()){
                     while(S.peek()!=null){
                         S.push(((Node)S.peek()).left);
                     }
                     S.pop();
                     while (!S.isEmpty()){
                         T = (Node) S.peek();
                         if (T.right==null||T.left==p){
                             System.out.println(T.data);
                             S.pop();
                             p=T;
                             flag = true;
                         }
                         else{
                             S.push(T.right);
                             flag=false;
                         }
                         if (!flag){
                             break;
                         }
                     }
                 }
        }
    }

    // 将先序遍历的结果存入数组
    public Object[] toArray() {
        int n = countNode();// 因为没有size变量，只能笨办法
        Object[] elements = new Object[n];
        toArray(root, elements, 0);
        return elements;
    }

    // pos：将以t为根的二叉树的第1个结点放入数组array的位置
    // 方法的返回值：以t为根的树的结点数
    private int toArray(Node<T> t, Object[] array, int pos) {
        if (t == null)
            return 0;
        array[pos++] = t.data;
        int countLeft = toArray(t.left, array, pos);
        int countRight = toArray(t.right, array, pos + countLeft);
        return countLeft + countRight + 1;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        int n = countNode();//返回数据大小数量
        if (a.length < n)//重新改变a数组的大小
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), n);//生成一个新数组，a.getClass().getComponentType()代表数组类型
        //getComponentType()可以获取数组类型对象元素class类型，返回的是内存中实实在在存在的T这个类
        int size = toArray(root, a, 0);
        if (a.length > size)
            a[size] = null;// 因为a中可能存放了其它数据，从下标0开始，遇到null，就是传入a的所有的结点（标记结束）
        return a;
    }

    // 将中序遍历的结果存入数组
    @SuppressWarnings("unchecked")
    public T[] toArrayIn(T[] a) {
        int n = countNode();//返回数据大小数量
        if (a.length < n)//重新改变a数组的大小
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), n);//生成一个新数组，a.getClass().getComponentType()代表数组类型
        //getComponentType()可以获取数组类型对象元素class类型，返回的是内存中实实在在存在的T这个类
        int size = toArrayIn(root, a, 0);
        if (a.length > size)
            a[size] = null;// 因为a中可能存放了其它数据，从下标0开始，遇到null，就是传入a的所有的结点（标记结束）
        return a;
    }
    private int toArrayIn(Node<T> t,T[] array,int pos){
        if (t==null)
            return 0;
        int countleft =toArrayIn(t.left,array,pos);
        array[pos+countleft]=t.data;
        pos++;
        int countright=toArrayIn(t.right,array,pos+countleft);
        return countleft+countright+1;

    }



    // 复制一棵二叉树，得到一棵新的二叉树，2棵二叉树没有共用结点，但是结点中的数据一样
    private Node<T> copy(Node<T> t) {// 返回复制得到的二叉树的根结点
        if (t == null)
            return null;
        Node<T> left = copy(t.left);
        Node<T> right = copy(t.right);
        return new Node<>(t.data, left, right);
    }

    @SuppressWarnings("unchecked")
    public Object clone() {
        try {
            BinaryTree<T> v = (BinaryTree<T>) super.clone();
            v.root = copy(root);
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    // 根据先序和中序遍历的结果，重构二叉树，注意，数据必须是char
    // 容易理解，但是创建了很多没有必要的对象
    public Node<Character> makeBitree(String preString, String inString) {
        Node<Character> left = null, right = null;
        if (preString.length() != 1) {
            int rootPos = inString.indexOf(preString.charAt(0));
            if (rootPos == -1)
                throw new IllegalStateException();
            int countLeft = rootPos;
            if (countLeft > 0)
                left = makeBitree(preString.substring(1, countLeft + 1), inString.substring(0, countLeft));
            int countRight = inString.length() - 1 - countLeft;
            if (countRight > 0)
                right = makeBitree(preString.substring(countLeft + 1), inString.substring(countLeft + 1));
        }
        return new Node<>(preString.charAt(0), left, right);
    }

    // preStart 左子树在preString的开始位置
    // inStart 右子树在inString的开始位置
    // count 树中结点的个数
    public Node<Character> makeBitree(String preString, String inString, int preStart, int inStart, int count) {
        Node<Character> left = null, right = null;
        if (count != 1) {
            int rootPos = inString.indexOf(preString.charAt(preStart));
            if (rootPos == -1)
                throw new IllegalStateException();
            int countLeft = rootPos - inStart;
            if (countLeft > 0)
                left = makeBitree(preString, inString, preStart + 1, inStart, countLeft);
            int countRight = count - countLeft - 1;
            if (countRight > 0)
                right = makeBitree(preString, inString, preStart + 1 + countLeft, inStart + countLeft + 1, countRight);
        }
        return new Node<>(preString.charAt(preStart), left, right);
    }

    // 树以二叉链表存储，求树的深度
    public int treeDepth() {
        return treeDepth(root);
    }

    private int treeDepth(Node<T> t) {
        if (t == null)
            return 0;
        int depth = 0;
        Node<T> child = t.left;
        while (child != null) {
            int tmp = treeDepth(child);
            if (depth < tmp)
                depth = tmp;
            child = child.right;
        }
        return depth + 1;
    }

    // 树以二叉链表存储，求树的根结点到各叶子结点的路径

    public void treeRootToLeafPath() {
        if (root == null)
            return;
        Deque<Node<T>> stack = new ArrayDeque<>();
        treeRootToLeafPath(root, stack);
    }

    public void treeRootToLeafPath(Node<T> t, Deque<Node<T>> stack) {
        if (t.left == null) {// 叶子结点，打印栈中的数据
            for (Node<T> p : stack)
                System.out.print(p.data + " ");
            System.out.println(t.data + " ");
            return;
        }
        stack.offerLast(t);// Deque默认的栈是head端，这里使用tail端的栈，这样，从head到tail的数据的次序才和输出一致
        Node<T> child = t.left;
        while (child != null) {// 先根遍历各子树
            treeRootToLeafPath(child, stack);
            child = child.right;
        }
        stack.pollLast();// 回溯，返回父结点，记得出栈
    }

    public void treeRootToLeafPathNonRecursively() {// 非递归,先根遍历的次序，还能处理对森林的各棵子树
        if (root == null)
            return;
        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> cur = root;
        for (;;) {// 处理每一棵树
            while (cur.left != null) {// 一直往左，找到cur最左边的叶子结点
                stack.offerLast(cur);
                cur = cur.left;
            }
            for (Node<T> p : stack)// 输出叶子结点
                System.out.print(p.data + " ");
            System.out.println(cur.data + " ");
            while (cur.right == null) {// 没有兄弟结点，回溯到父结点
                if (stack.isEmpty())
                    return;
                cur = stack.pollLast();
            }
            cur = cur.right;// 处理cur的兄弟结点
        }
    }

    public void treeRootToLeafPath1() {
        class Path {// 为了减少函数的参数个数(stack)，使用了内部类，未必合适，只是一种思路
            Deque<Node<T>> stack = new ArrayDeque<>();

            // 采用先根遍历的思想，将结点的值存放到栈中
            public void treeRootToLeafPath(Node<T> t) {
                if (t.left == null) {// 叶子结点，打印栈中的数据
                    for (Node<T> n : stack) // 从head到tail，使用了Deque的迭代器，没有使用toArray
                        System.out.print(n.data + " ");
                    System.out.println(t.data);
                    return;
                }
                stack.offerLast(t);// Deque默认的栈是head端，这里使用tail端作为栈，从head到tail的次序和我们输出的次序一致
                Node<T> child = t.left;
                while (child != null) {// 先根遍历各子树
                    treeRootToLeafPath(child);
                    child = child.right;
                }
                stack.pollLast();// 回溯，返回父结点，记得出栈，在tail端删除
            }
        }
        // 代码开始执行的位置
        if (root == null)
            return;
        new Path().treeRootToLeafPath(root);
    }

    //实验
    public T preRootK(int k){
        if (root==null){
            return null;
        }
        Deque<Node <T>> qe =new ArrayDeque<>();
        Node<T> p=root;
        int count=0;
        for (;;){
            while(p!=null){
                count++;
                if (count==k){
                    return p.data;
                }
                if (p.right!=null){
                    qe.push(p.right);
                }
                p=p.left;
            }
            if (qe.isEmpty()){
                return null;
            }
            p=qe.pop();
        }

    }
    //    public boolean CompleteBinaryTree()//判断是不是完全二叉树
//    {
//        if(root == null)
//            return false;
//        if(countNode() == 1)//只有一个根节点也是完全二叉树
//            return true;
//        Deque<Node<T>> deque = new ArrayDeque<>();
//        deque.offer(root);
//        while(!deque.isEmpty())
//        {
//            Node<T> p = deque.poll();//删除掉头结点并返回头结点值
////            System.out.println(p.data)
//            if(p.left == null && p.right!=null)//左节点为空。右节点不为空，则不是完全二叉树
//                {
//                    return false;
//                }
//            if(p.left != null && p.right != null)//正常层次遍历添加节点
//            {
//                deque.offer(p.left);
//                deque.offer(p.right);}
//            //这个节点只有左孩子，或者没孩子
//            if(p.left != null && p.right == null || p.left == null&& p.right == null)//此时队列后面的节点都是叶子节点
//            {
//                if(p.left != null)
//                    deque.offer(p.left);//把那个左孩子加上
//                deque.pop();//删掉那个临界节点
//                while(!deque.isEmpty())
//                {
//                    p = deque.poll();
//                    if(p.left!=null || p.right!=null)//若是有子节点则不是完全二叉树
//                        return false;
//                }
//                return true;
//            }
//        }
//        return true;
//    }

    public boolean CompleteBinaryTree()
    {
        if (root == null)
            return false;
        Deque<Node<T>> queue = new ArrayDeque<>();
//        LinkList<Node<T>> list = new LinkList<>();
        queue.offer(root);
        int depth = depth(root);//获取深度
        int flag=0;
        for(int i=0;i<Math.pow(2,depth-1)-1;i++)//当队列里只有null时判断.isEmpty会报空指针异常
        {
            Node<T> t = queue.poll();
            if(t.left!=null)
            {
                if(flag==1)
                    return false;
                queue.offer(t.left);
            }
            if(t.left==null || t.right==null&& t.left!=null)
            {
                flag=1;
            }
            if(t.right!=null)
            {
                if(flag==1)//不能再添加
                    return false;
                queue.offer(t.right);
            }
        }
        return true;
    }
    public static void main(String[] args) {

        BinaryTree<Character> btString = new BinaryTree<>("ABCDEFGHK", "BDCAEHGKF");
        // BinaryTree<Character> btString = new BinaryTree<>("ABC", "BAC");
        // BinaryTree<Character> btString = new BinaryTree<>("ABCDEF", "BCADFE");
        BinaryTree<Character> btString3 = new BinaryTree<>("ABC", "BAC");
        BinaryTree<Character> btString4 = new BinaryTree<>("ABCDEF", "BCADFE");
        BinaryTree<Character> btString2 = new BinaryTree<>("", "");//空
        BinaryTree<Character> btString5 = new BinaryTree<>("A", "A");//一个
        BinaryTree<Character> btString6 = new BinaryTree<>("AB", "BA");//两个
        BinaryTree<Character> btString7 = new BinaryTree<>("AB", "AB");//两个
        System.out.println(btString.CompleteBinaryTree());//normal false
        System.out.println(btString2.CompleteBinaryTree());//null false
        System.out.println(btString3.CompleteBinaryTree());//normal true
        System.out.println(btString4.CompleteBinaryTree());//normal false
        System.out.println(btString5.CompleteBinaryTree());//unNormal true
        System.out.println(btString6.CompleteBinaryTree());//unNormal true
        System.out.println(btString7.CompleteBinaryTree());//unNormal false

//        //中序遍历存入数组
//        Character[] b = new Character[btString.countNode()];
//        b=btString.toArrayIn(b);
//        System.out.println();
//        System.out.println(Arrays.toString(b));
//        //list前序遍历到数组测试。
//        List<Character> list = new ArrayList<>();
//        btString.toList(list);
//        System.out.println("===================");
//        System.out.print(list);//前序遍历输入到数组里、、注意还未优化。
//        System.out.println();
//        System.out.println("===================");

//        System.out.println("=========preOrder==========");
//        btString.preOrderNonRecursively();
//        System.out.println("=========inOrder==========");
//        btString.inOrderNonRecursively();
//        System.out.println("=========postOrder==========");
//        btString.postOrderNonRecursively();
//
//        System.out.println("===================");

                //实验输出先序遍历的第k个数据
        System.out.println(btString.preRootK(0));
        System.out.println(btString.preRootK(1));
        System.out.println(btString.preRootK(2));
        System.out.println(btString.preRootK(9));
//
//        BinaryTree<Circle> btBlank = new BinaryTree<>();
//        BinaryTree<Circle> btl = new BinaryTree<>(new Circle(2.0), btBlank, btBlank);
//        BinaryTree<Circle> btr = new BinaryTree<>(new Circle(3.0), btBlank, btBlank);
//        BinaryTree<Circle> bt1 = new BinaryTree<>(new Circle(1.0), btl, btr);
//
//        btl = new BinaryTree<>(new Circle(4.0), btBlank, btBlank);
//        btr = new BinaryTree<>(new Circle(5.0), btBlank, btBlank);
//        BinaryTree<Circle> bt2 = new BinaryTree<>(new Circle(6.0), btl, btr);
//        BinaryTree<Circle> bt = new BinaryTree<>(new Circle(7.0), bt1, bt2);
//
//        bt.preOrder();
//        System.out.println("----------------------------");
//        bt.preOrder(new CircleTimesByTen());
//        bt.preOrder();
//
//        System.out.println("----------------------------");
//        Circle[] cir = new Circle[20];
//        for (int i = 0; i < cir.length; i++)
//            cir[i] = new Circle(i);
//        for (Circle c : bt.toArray(cir)) {
//            System.out.println(c);
//        }
//
//        System.out.println("-------------treePath---------------");
//        BinaryTree<Character> treePath = new BinaryTree<>("ABEFCDGHIJK", "EFBCIJKHGDA");
//        treePath.postOrder();
//        treePath.treeRootToLeafPath();
//        treePath.treeRootToLeafPathNonRecursively();
    }
}

