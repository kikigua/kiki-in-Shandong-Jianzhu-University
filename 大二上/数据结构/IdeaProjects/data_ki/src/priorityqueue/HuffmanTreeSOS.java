package priorityqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//只能处理1个符号使用了1个char场合
public class HuffmanTreeSOS implements Comparable<HuffmanTreeSOS> {
	private Node root;// 根结点
	private float weight;// 树中叶子结点的权重之和
	private Map<Character, Byte[]> coder;// 存放<符号,编码>，可以考虑使用BitSet替换Byte[]
	private Deque<Byte> stack;// 存放从根到叶子结点遍历的路径
	private static byte LEFT = 0;// 左子树
	private static byte RIGHT = 1;// 右子树

	private static class Node {
		char data;// 叶子结点放待编码的符号
		Node left;
		Node right;

		Node(char data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public HuffmanTreeSOS(char[] symbol, float[] weights) { // 使用哈夫曼算法建立哈夫曼树
		if (weights.length != symbol.length || weights.length < 2)
			throw new IllegalStateException();
		PriorityQueue<HuffmanTreeSOS> pq = new PriorityQueue<>(weights.length); // 使用java类库的优先队列，它是小顶堆
		for (int i = 0; i < weights.length; i++)// 创建叶子结点对应的哈夫曼树，并入队
			pq.offer(new HuffmanTreeSOS(symbol[i], weights[i]));
		while (pq.size() != 1) {// 取2棵最小的树，合并后得到1棵新树，再放入队列
			pq.offer(merge(pq.poll(), pq.poll()));
		}
		HuffmanTreeSOS result = pq.poll();
		this.root = result.root;
		this.weight = result.weight;
	}

	public int compareTo(HuffmanTreeSOS rhd) { // 为了使用优先队列,需要比较大小，值小的获胜
		return Float.compare(this.weight, rhd.weight);
	}

	private HuffmanTreeSOS() {// 空树
	}

	private HuffmanTreeSOS(char symbol, float weight) {// 只有根结点的树，作为HUffmanTree的叶子结点
		this.weight = weight;
		root = new Node(symbol, null, null);
	}

	private HuffmanTreeSOS merge(HuffmanTreeSOS lhd, HuffmanTreeSOS rhd) { // 合并2棵哈夫曼树
		HuffmanTreeSOS result = new HuffmanTreeSOS();
		result.root = new Node('\0', lhd.root, rhd.root);// 中间结点，\0可以替换成任意的符号
		result.weight = lhd.weight + rhd.weight;
		return result;
	}

	private void makeCoder() { // 根据建立好的哈夫曼树，得到各符号的编码
		coder = new HashMap<>();
		stack = new ArrayDeque<>();
		preCoding(root.left, LEFT);
		preCoding(root.right, RIGHT);
		stack = null;// 尽快释放stack，或者不把stack作为字段，而是作为局部变量
	}

	private void preCoding(Node t, byte b) { // 求出根结点到各叶子结点的路径，得到叶子结点存放的符号的编码
		stack.addLast(b); // 为了toArray的次序和从根到叶子结点的路径的次序一致，使用Deque的右端栈
		if (t.left == null && t.right == null) {// 叶子结点
			Byte[] code = new Byte[stack.size()];
			stack.toArray(code);
			coder.put(t.data, code);
		} else {
			preCoding(t.left, LEFT);
			preCoding(t.right, RIGHT);
		}
		stack.removeLast();// 回溯
	}

	public Byte[] enCoding(String str) {
		if (coder == null)// 第1次使用这棵哈夫曼树编码
			makeCoder();
		int len = str.length();
		List<Byte[]> list = new ArrayList<>(len); // 存放每个字符的编码
		int count = 0;// 编码的总长度
		for (int i = 0; i < len; i++) {
			Byte[] b = coder.get(str.charAt(i));// 查表，获取字符的编码
			count += b.length;
			list.add(b);
		}
		Byte[] result = new Byte[count]; // str的编码
		int pos = 0;
		for (int i = 0; i < list.size(); i++) {
			Byte[] b = list.get(i);
			System.arraycopy(b, 0, result, pos, b.length);
			pos += b.length;
		}
		return result;
	}

	public String deCoding(Byte[] code) { // 没有处理一些特殊情况
		StringBuilder result = new StringBuilder();
		Node node = root;
		for (int i = 0; i < code.length; i++) {
			if (code[i].byteValue() == LEFT)
				node = node.left;
			else
				node = node.right;
			if (node.left == null && node.right == null) {// 叶子结点
				result.append(node.data);// 叶子结点中存的符号
				node = root;// 译出一个字符后，再从根开始
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		float[] weights = { 0.1f, 0.05f, 0.20f, 0.1f, 0.2f, 0.1f, 0.05f, 0.2f };
		char[] symbol = { 'h', 'o', 'l', 'w', 'r', 'd', 'e', ' ' };
		HuffmanTreeSOS ht = new HuffmanTreeSOS(symbol, weights);
		String str = "hello world";
		Byte[] codes = ht.enCoding(str);
		for (int i = 0; i < codes.length; i++)
			System.out.print(codes[i]);
		System.out.println();
		System.out.println(ht.deCoding(codes));
	}
}
