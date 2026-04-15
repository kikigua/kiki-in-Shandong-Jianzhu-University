package priorityqueue;

//使用java类库的最小优先级队列
import java.util.PriorityQueue;

public class HuffmanTree implements Comparable<HuffmanTree> {
	Node root;// 根结点
	float weight;// 树的权重

	static class Node {
		char data;// 叶子结点放待编码的符号，用于霍夫曼编码
		Node left;
		Node right;

		Node(char data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private HuffmanTree() {// 空树
	}

	private HuffmanTree(char symbol, float weight) {// 只有根结点的树，霍夫曼树的叶子结点
		this.weight = weight;
		root = new Node(symbol, null, null);
	}

	public HuffmanTree(char[] symbol, float[] weights) { // 使用霍夫曼算法建立霍夫曼树
		if (weights.length != symbol.length || weights.length < 2)
			throw new IllegalStateException();
		PriorityQueue<HuffmanTree> pq = new PriorityQueue<>(weights.length);
		for (int i = 0; i < weights.length; i++)// 创建叶子结点对应的霍夫曼树，并入队
			pq.offer(new HuffmanTree(symbol[i], weights[i]));
		while (pq.size() != 1) {// 取2棵最小的树，合并后得到1棵新树，再放入队列
			pq.offer(merge(pq.poll(), pq.poll()));
		}
		HuffmanTree result = pq.poll();
		this.root = result.root;
		this.weight = result.weight;
	}

	public int compareTo(HuffmanTree rhd) { // 为了使用最小优先级队列,需要比较大小
		return Float.compare(this.weight, rhd.weight);
	}

	private HuffmanTree merge(HuffmanTree lhd, HuffmanTree rhd) { // 合并2棵哈夫曼树
		HuffmanTree result = new HuffmanTree();
		result.root = new Node('\0', lhd.root, rhd.root);// 中间结点，\0可以替换成任意的符号
		result.weight = lhd.weight + rhd.weight;
		return result;
	}
}
