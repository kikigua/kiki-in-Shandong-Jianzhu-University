package priorityqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import priorityqueue.HuffmanTree.Node;

//只能处理1个符号使用了1个char场合
public class HuffmanCoding {
	private HuffmanTree ht;
	private Map<Character, Byte[]> coder;// 存放<符号,编码>
	private static byte LEFT = 0;// 左子树的标识
	private static byte RIGHT = 1;// 右子树的标识

	public HuffmanCoding(HuffmanTree ht) {
		this.ht = ht;
	}

	private void makeCoder() { // 根据霍夫曼树获取各符号的编码
		coder = new HashMap<>();
		Deque<Byte> stack = new ArrayDeque<>();
		preCoding(ht.root.left, LEFT, stack);
		preCoding(ht.root.right, RIGHT, stack);
	}

	// 求出根结点到各叶子结点的路径，获取叶子结点存放的符号的编码
	private void preCoding(Node t, byte b, Deque<Byte> stack) {
		// 为了toArray的次序和从根到叶子结点的路径的次序一致，使用Deque的右端栈
		stack.addLast(b);
		if (t.left == null && t.right == null) {// 叶子结点
			Byte[] code = new Byte[stack.size()];
			stack.toArray(code);
			coder.put(t.data, code);
		} else {
			preCoding(t.left, LEFT, stack);
			preCoding(t.right, RIGHT, stack);
		}
		stack.removeLast();// 回溯
	}

	public Byte[] enCoding(String str) {
		if (coder == null)// 第1次使用这棵哈夫曼树编码
			makeCoder();
		int len = str.length();
		List<Byte[]> list = new ArrayList<>(len); // 存放各字符的编码
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
		Node node = ht.root;
		for (int i = 0; i < code.length; i++) {
			if (code[i].byteValue() == LEFT)
				node = node.left;
			else
				node = node.right;
			if (node.left == null && node.right == null) {// 叶子结点
				result.append(node.data);// 叶子结点中存的符号
				node = ht.root;// 译出一个字符后，再从根开始
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		float[] weights = { 9f, 7f, 5f, 4f, 2f };
		char[] symbol = { 'A', 'B', 'C', 'D', 'E' };
		HuffmanCoding hc = new HuffmanCoding(new HuffmanTree(symbol, weights));
		String str = "ABECD";
		Byte[] codes = hc.enCoding(str);
		for (int i = 0; i < codes.length; i++)
			System.out.print(codes[i]);
		System.out.println();
		System.out.println(hc.deCoding(codes));
	}
//	public static void main(String[] args) {
//		float[] weights = { 0.1f, 0.05f, 0.20f, 0.1f, 0.2f, 0.1f, 0.05f, 0.2f };
//		char[] symbol = { 'h', 'o', 'l', 'w', 'r', 'd', 'e', ' ' };
//		HuffmanCoding hc = new HuffmanCoding(new HuffmanTree(symbol, weights));
//		String str = "hello world";
//		Byte[] codes = hc.enCoding(str);
//		for (int i = 0; i < codes.length; i++)
//			System.out.print(codes[i]);
//		System.out.println();
//		System.out.println(hc.deCoding(codes));
//	}
}
