package hw.learn.advance.algorithms.tree;

import org.junit.Test;


/**
 * 利用Comparable实现二叉排序树
 * @author Administrator
 *
 */
public class BinarySortTree_Comparable {
	class Node { ///创建内部类
		/**
		 * Comparable接口强行对实现它的每个类的对象进行整体排序,此排序被称为该类的自然排序。 实现此接口的对象列表（和数组）可以通过
		 * Collections.sort() （和 Arrays.sort()）进行自动排序
		 * */
		private Comparable data;
		private Node left;
		private Node right;

		public void addNode(Node newNode) {// 在本类的中要用到本类对象
			if (newNode.data.compareTo(this.data) < 0) {// 如果要加进来的这个节点值小于当前节点值
				if (this.left == null) {// 且当前节点的左节点为空
					this.left = newNode;
				} else {
					this.left.addNode(newNode);
				}
			}
			if (newNode.data.compareTo(this.data) >= 0) {// “=”的情况加在右边
				if (this.right == null) {
					this.right = newNode;
				} else {
					this.right.addNode(newNode);
				}
			}
		}

		public void printNode() {
			if (this.left != null) {
				this.left.printNode();
			}
			System.out.println(this.data + "\t");
			if (this.right != null) {
				this.right.printNode();
			}
		}
	}

	private Node root;// /定义根节点

	public void add(Comparable data) {
		Node newNode = new Node();
		newNode.data = data;
		if (root == null) {
			root = newNode;
		} else {
			root.addNode(newNode);
		}
	}

	public void print() {
		this.root.printNode();
	}
	@Test
	public void testDemo() {
		BinarySortTree_Comparable bt = new BinarySortTree_Comparable();
		bt.add(8);
		bt.add(3);
		bt.add(3);
		bt.add(10);
		bt.add(9);
		bt.add(1);
		bt.add(5);
		bt.add(5);
		System.out.println("排序后的结果：");
		bt.print();
	}
}
