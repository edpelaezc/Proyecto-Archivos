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
    private Node<Correo> next;

    /**
     * Objeto nodo utilizado para orden del árbol
     *
     * @param newElement
     * @param nextNode
     */
    public Node(Correo newElement, Node<Correo> nextNode) {
        element = newElement;
        next = nextNode;
    }

    public Correo getElement() {
        return element;
    }

    public Node<Correo> getNext() {
        return next;
    }

    public void setNext(Node<Correo> next) {
        this.next = next;
    }

}
