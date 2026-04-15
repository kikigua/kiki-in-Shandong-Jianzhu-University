package priorityqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//可以处理1个符号需要2个char的场合
public class HuffmanTree1 implements Comparable<HuffmanTree1> {
	private Node root;// 根结点
	private float weight;// 树中叶子结点的权重之和
	private Map<String, Byte[]> coder;// 存放<符号,编码>
	private Deque<Byte> stack;// 存放从根到叶子结点遍历的路径

	private static byte LEFT = 0;// 左子树
	private static byte RIGHT = 1;// 右子树

	private static class Node {
		String data;// 这里不是char了
		Node left;
		Node right;

		Node(String data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public HuffmanTree1(String[] symbol, float[] weights) { // 使用哈夫曼算法建立哈夫曼树
		if (weights.length != symbol.length || weights.length < 2)
			throw new IllegalStateException();
		PriorityQueue<HuffmanTree1> pq = new PriorityQueue<>(weights.length);
		for (int i = 0; i < weights.length; i++)// 创建叶子结点对应的哈夫曼树
			pq.offer(new HuffmanTree1(symbol[i], weights[i]));
		while (pq.size() != 1) {// 合并2棵最小的树
			pq.offer(merge(pq.poll(), pq.poll()));
		}
		HuffmanTree1 result = pq.poll();
		this.root = result.root;
		this.weight = result.weight;
	}

	public int compareTo(HuffmanTree1 rhd) {
		return Float.compare(this.weight, rhd.weight);
	}

	private HuffmanTree1() {

	}

	private HuffmanTree1(String symbol, float weight) {// 只有根结点的树，作为HUffmanTree的叶子结点
		this.weight = weight;
		root = new Node(symbol, null, null);
	}

	private HuffmanTree1 merge(HuffmanTree1 lhd, HuffmanTree1 rhd) {
		HuffmanTree1 result = new HuffmanTree1();
		result.root = new Node("\0", lhd.root, rhd.root);// 中间结点，\0可以替换成任意的符号
		result.weight = lhd.weight + rhd.weight;
		return result;
	}

	private void makeCoder() {
		coder = new HashMap<>();
		stack = new ArrayDeque<>();
		preCoding(root.left, LEFT);
		preCoding(root.right, RIGHT);
		stack = null;// 尽快释放stack
	}

	private void preCoding(Node t, byte b) {
		stack.addLast(b);// 使用Deque的右端栈
		if (t.left == null && t.right == null) {// 叶子结点,处理完返回
			Byte[] code = new Byte[stack.size()];
			stack.toArray(code);
			coder.put(t.data, code);
		} else {
			preCoding(t.left, LEFT);
			preCoding(t.right, RIGHT);
		}
		stack.removeLast();
	}

	public Byte[] enCoding(String str) {// 该方法变了
		if (coder == null)// 第1次编码
			makeCoder();
		List<Byte[]> list = new ArrayList<>();
		int count = 0;// 编码的总长度
		int i = 0;
		while (i < str.length()) {
			Byte[] b;
			// 1个符号需要2个char，高位的char是HighSurrogate
			if (Character.isHighSurrogate(str.charAt(i))) {
				b = coder.get(str.substring(i, i + 2));
				i += 2;
			} else {
				b = coder.get(str.substring(i, i + 1));
				++i;
			}
			count += b.length;
			list.add(b);
		}
		// 各个字符的总编码
		Byte[] result = new Byte[count];
		int pos = 0;
		for (int j = 0; j < list.size(); j++) {
			Byte[] b = list.get(j);
			System.arraycopy(b, 0, result, pos, b.length);
			pos += b.length;
		}
		return result;
	}

	public String deCoding(Byte[] code) { // 没有处理一些特殊情况
		StringBuilder result = new StringBuilder();
		Node node = root;
		for (int i = 0; i < code.length; ++i) {
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
		float[] weights = { 0.01f, 0.01f, 0.01f, 0.20f, 0.3f, 0.2f, 0.1f, 0.2f };
		String[] symbol = { "𠀁", "𠀂", "𠀃", "汉", "字", "1", "2", "3" };
		HuffmanTree1 ht = new HuffmanTree1(symbol, weights);
		String str = "𠀁𠀂𠀃汉字𠀁123𠀃";
		Byte[] codes = ht.enCoding(str);
		for (int i = 0; i < codes.length; i++)
			System.out.print(codes[i]);
		System.out.println();
		System.out.println(ht.deCoding(codes));
	}
}
