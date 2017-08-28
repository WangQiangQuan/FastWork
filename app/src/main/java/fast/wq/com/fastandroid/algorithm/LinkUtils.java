package fast.wq.com.fastandroid.algorithm;

import java.util.HashSet;
import java.util.Stack;

/**
 * 堆栈工具
 * 单链表
 */
public class LinkUtils {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //1删除单链表中的指定节点
    public static void deleteNode(Node head, Node deleteNode) {
        if (head == null) {
            return;
        }
        //删除尾节点，顺序查找尾部节点的前一个节
        if (deleteNode.next == null) {
            while (head.next != deleteNode) {
                head = head.next;
            }
            head.next = null;
        }
        //删除头结点
        if (head == deleteNode) {
            head = null;
        } else {
            //中间节点 ,其实是删除了 deleteNode下一个节点。因为编程方便
            Node deleteNextNode = deleteNode.next;
            deleteNode.data = deleteNextNode.data;
            deleteNode.next = deleteNextNode.next;
        }

    }

    /**
     * 2单链表 删除指定数值的节点的方法一：利用栈
     */
    public Node removeValueUseStack(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.data != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }

    public Node removeValue(Node head, int num) {
        if (head == null) {
            return null;
        }
        //找到第一个头结点
        while (head != null) {
            if (head.data != num) {
                break;
            }
            head = head.next;
        }

        Node pre, cur;
        pre = head;
        cur = head;
        while (cur != null) {
            if (cur.data == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    /**
     * 4单链表 删除指定数值重复的
     */
    public Node deleteRepeat(Node head) {
        if (head == null) {
            return null;
        }
        HashSet<Integer> mHashSet = new HashSet<>();

        mHashSet.add(head.data);
        Node pre, cur;
        pre = head;
        cur = head;
        while (cur != null) {
            if (mHashSet.contains(cur.data)) {
                pre.next = cur.next;
            } else {
                mHashSet.add(cur.data);
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
