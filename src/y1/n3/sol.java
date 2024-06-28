package y1.n3;

import java.io.*;
import java.util.*;

public class sol {
    public static void main(String[] args) throws IOException {

        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
             PrintStream out = new PrintStream(new File("output.txt"))) {
            String[] first = fin.readLine().split(" ");
            int N = Integer.parseInt(first[0]); // количество вершин в дереве
            int Q = Integer.parseInt(first[1]); // количество изменений

            Map<Integer, Node> map = new HashMap<>();
            map.put(1, new Node(1)); // создание корневой вершины
            createChildren(map, N, map.get(1)); // создание потомков вершины

            for (String s : fin.readLine().split(" ")) {
                int i = Integer.parseInt(s);
                moveNode(i, map);
            }
            printNode(out, findRoot(map));
        }
    }

    static Node findRoot(Map<Integer, Node> map) {
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            if (entry.getValue().getParent() == null) {
                return entry.getValue();
            }
        }
        return null;
    }

    static void printNode(PrintStream out, Node node) {
        if (node.getLeftChild() != null) {
            printNode(out, node.getLeftChild());
        }
        out.print(node.value + " ");
        if (node.getRightChild() != null) {
            printNode(out, node.getRightChild());
        }
    }

    static void moveNode(int i, Map<Integer, Node> map) {
        Node v = map.get(i);
        if (v.getParent() == null) {
            return;
        }
        Node p = v.getParent();
        Node pp = p.getParent();
        boolean vIsLeftChild = v.equals(p.getLeftChild());
        boolean pIsLeftChild = pp != null && p.equals(pp.getLeftChild());

        v.setParent(pp);
        p.setParent(v);
        if (pp != null) { // если p не был корнем и pp существует
            if (pIsLeftChild) { // если p — левый ребенок вершины pp, то v становится левым ребенком pp, иначе — правым
                pp.setLeftChild(v);
            } else {
                pp.setRightChild(v);
            }
        }

//        если v — левый ребенок вершины p
//        vr остаётся правым ребенком v;
//        vl становится левым ребенком p;
//        pr остаётся правым ребенком p.
        if (vIsLeftChild) {
            Node vl = v.getLeftChild();
            v.setLeftChild(p);
            p.setLeftChild(vl);
            if (vl != null) {
                vl.setParent(p);
            }
        } else {
//            аналогично, если v — правый ребенок вершины p, то:
//            p становится правым ребенком v;
//            vl остаётся левым ребенком v;
//            vr становится правым ребенком p;
//            pl остаётся левым ребенком p.
            Node vr = v.getRightChild();
            v.setRightChild(p);
            p.setRightChild(vr);
            if (vr != null) {
                vr.setParent(p);
            }
        }

    }

    static void createChildren(Map<Integer, Node> tree, int N, Node parent) {
        Node leftChild = null;
        Node rigtChild = null;
        if (2 * parent.value <= N) {
            leftChild = new Node(2 * parent.value, parent);
            tree.put(leftChild.getValue(), leftChild);
            createChildren(tree, N, leftChild);
        }
        if (2 * parent.value + 1 <= N) {
            rigtChild = new Node(2 * parent.value + 1, parent);
            tree.put(rigtChild.getValue(), rigtChild);
            createChildren(tree, N, rigtChild);
        }
        parent.setLeftChild(leftChild);
        parent.setRightChild(rigtChild);
    }

    static class Node {
        int value;
        Node parent;
        Node leftChild;

        Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        public void printNode() {
            System.out.println("Значение узла:" + value);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}