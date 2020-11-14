/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edanP
 */
public class Tree {

    public Node<Correo> root;
    private int numberOfNodes = 1;
    public ArrayList<Node<Correo>> order = new ArrayList<Node<Correo>>();
    File correos = new File("C:\\MEIA\\correos.txt");

    public Tree() {
        root = null;
    }

    public int weight() {
        return numberOfNodes;
    }

    public void add(Correo element) {
        if (this.root == null) {
            root = new Node<Correo>(element, null, null, null, numberOfNodes++);
            writeTree();
            order = new ArrayList<Node<Correo>>();
        } else {
            addElement(this.root, element);
            writeTree();
            order = new ArrayList<Node<Correo>>();
        }
    }

    private void addElement(Node<Correo> root, Correo element) {
        if (this.root == null) {
            this.root = new Node<Correo>(element, null, null, null, numberOfNodes++);
        } else {
            if (element.compareTo(root.getElement()) < 0)//x es menor que y
            {
                if (root.getLeft() == null) {
                    root.setLeft(new Node<Correo>(element, root, null, null, numberOfNodes++));
                } else {
                    addElement(root.getLeft(), element);
                }
            } else if (element.compareTo(root.getElement()) > 0)//x es mayor que y 
            {
                if (root.getRight() == null) {
                    root.setRight(new Node<Correo>(element, root, null, null, numberOfNodes++));
                } else {
                    addElement(root.getRight(), element);
                }
            } else//x es igual que y
            {
                System.out.println("NO SE PERMITEN DUPLICADOS");
            }
        }
    }

    public boolean elementExists(Node<Correo> root, Correo element) {
        if (root == null) {
            return false;
        } else if (element.compareTo(root.getElement()) == 0) {
            return true;
        } else {
            if (element.compareTo(root.getElement()) < 0) {
                return elementExists(root.getLeft(), element);
            } else {
                return elementExists(root.getRight(), element);
            }
        }
    }

    public int numberOfChildren(Node<Correo> root) {
        int count = 0;
        if (root.getLeft() != null) {
            count++;
        }
        if (root.getRight() != null) {
            count++;
        }
        return count;
    }

    public Correo remove(Node<Correo> root, Correo element) {
        if (root == null) {
            return null;
        } else if (element.compareTo(root.getElement()) == 0) {
            if (numberOfChildren(root) == 0)//borrar nodo sin hijos
            {
                Correo aux = root.getElement();
                Node<Correo> parent = root.getParent();
                if (root == this.root) {
                    this.root = null;
                } else {
                    if (parent.getLeft() == root) {
                        parent.setLeft(null);
                        root = null;
                    } else {
                        parent.setRight(null);
                        root = null;
                    }
                }

                return aux;
            }//borrar nodo sin hijos
            else if (numberOfChildren(root) == 1)//borrar nodo con 1 hijo
            {
                Correo aux = root.getElement();
                if (root == this.root) //si es la raiz
                {
                    if (root.getLeft() != null) {
                        this.root = root.getLeft();
                    } else {
                        this.root = root.getRight();
                    }
                    this.root.setParent(null);
                } else {
                    Node<Correo> parent = root.getParent();
                    if (parent.getLeft() == root) {
                        if (root.getLeft() != null) {
                            Node<Correo> son = root.getLeft();
                            son.setParent(parent);
                            parent.setLeft(son);
                        } else {
                            Node<Correo> son = root.getRight();
                            son.setParent(parent);
                            parent.setLeft(son);
                        }
                    } else {
                        if (root.getLeft() != null) {
                            Node<Correo> son = root.getLeft();
                            son.setParent(parent);
                            parent.setRight(son);
                        } else {
                            Node<Correo> son = root.getRight();
                            son.setParent(parent);
                            parent.setRight(son);
                        }
                    }
                }

                return aux;
            }//borrar nodo con 1 hijo
            else {//El que sustituirá será el más derecho de los izquierdos
                Node<Correo> next = root.getLeft();
                Correo aux = root.getElement();

                while (next.getRight() != null) {
                    next = next.getRight();
                }
                root.setElement(next.getElement());

                if (next != root.getLeft()) {
                    Node<Correo> father = next.getParent();
                    father.setRight(null);
                } else {
                    if (next.getLeft() != null) {
                        root.setLeft(next.getLeft());
                        next.getLeft().setParent(root);
                    } else {
                        root.setLeft(null);
                    }
                }

                return aux;
            }
        } else {
            if (element.compareTo(root.getElement()) < 0) {
                return remove(root.getLeft(), element);
            } else {
                return remove(root.getRight(), element);
            }
        }
    }

    public void preOrder(Node<Correo> root) {
        if (root != null) {
            System.out.println(root.getElement());
            order.add(root);
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void writeTree() {
        preOrder(this.root);
        PrintWriter writer;
        try {
            this.correos.delete();
            this.correos.createNewFile();
            // vaciar archivo            
            writer = new PrintWriter(correos);
            writer.print("");            

            // ordenar por medio de la direccion fisica
            Collections.sort(order);

            int size = order.size();            
            for (int i = 0; i < size; i++) {
                writer.println(order.get(i).toString());
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    public void postOrder(Node<Correo> root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            order.addLast(root);
        }
    }

    public void inOrder(Node<Correo> root) {
        if (root != null) {
            inOrder(root.getLeft());
            order.addLast(root);
            inOrder(root.getRight());
        }
    }*/
}
