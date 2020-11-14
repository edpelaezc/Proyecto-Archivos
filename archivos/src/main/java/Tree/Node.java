/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author edanP
 */
public class Node<Correo> implements Comparable<Node<Correo>> {

    private Correo element;
    private Integer no_registro; // actua como la dirección física de los nodos
    private Node<Correo> parent;
    private Node<Correo> left;
    private Node<Correo> right;

    public Node(Correo t, Node<Correo> above, Node<Correo> leftChild, Node<Correo> rightChild, int registro) {
        this.element = t;
        this.parent = above;
        this.left = leftChild;
        this.right = rightChild;
        this.no_registro = registro;
    }

    public Correo getElement() {
        return element;
    }

    public Node<Correo> getParent() {
        return parent;
    }

    public Node<Correo> getLeft() {
        return left;
    }

    public Node<Correo> getRight() {
        return right;
    }

    public void setElement(Correo t) {
        element = t;
    }

    public void setParent(Node<Correo> parentTreeNode) {
        parent = parentTreeNode;
    }

    public void setLeft(Node<Correo> leftChild) {
        left = leftChild;
    }

    public void setRight(Node<Correo> rightChild) {
        right = rightChild;
    }

    public Integer getNo_registro() {
        return no_registro;
    }

    public void setNo_registro(Integer no_registro) {
        this.no_registro = no_registro;
    }

    @Override
    public int compareTo(Node<Correo> o) {
        return this.no_registro.compareTo(o.no_registro);
    }

    @Override
    public String toString() {
        if (this.getLeft() == null && this.getRight() == null) {
            return this.getNo_registro().toString()+ "|" + "-" + "|" + "-" + "|" + this.element.toString();
        } else if (this.getLeft() != null && this.getRight() == null) {
            return this.getNo_registro().toString()+ "|" + this.getLeft().getNo_registro().toString()+ "|" + "-"+ "|" + this.element.toString();
        } else if (this.getRight() != null && this.getLeft() == null) {
            return this.getNo_registro().toString()+ "|" + "-" + "|" + this.getRight().getNo_registro().toString() + "|" + this.element.toString();
        } else {
            return this.getNo_registro().toString() + "|" + this.getLeft().getNo_registro().toString() + "|" + this.getRight().getNo_registro().toString() + "|" + this.element.toString();
        } 
    }

}
