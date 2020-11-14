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
public class Node<Correo> {

    private Correo element;
    private int no_registro;
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

}
