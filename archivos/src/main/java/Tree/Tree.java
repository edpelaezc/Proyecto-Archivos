/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Access.HandleFiles;
import Data.Data;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private int active = 0;
    private int inactive = 0;
    private int numberOfNodes = 1;
    public ArrayList<Node<Correo>> order = new ArrayList<Node<Correo>>();
    File correos = new File("C:\\MEIA\\correos.txt");
    File desc_correos = new File("C:\\MEIA\\desc_correos.txt");
    HandleFiles handler = new HandleFiles();

    public Tree() {
        root = null;
    }

    public void add(Correo element) {
        if (this.root == null) {
            root = new Node<Correo>(element, null, null, null, numberOfNodes++);
            writeTree();
        } else {
            addElement(this.root, element);
            writeTree();
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

    /**
     * GET en el árbol.
     *
     * @param input
     * @return null si no existe, de lo contrario retorna un objeto "Correo".
     */
    public Correo search(String emisor, String receptor, String fecha) {
        ArrayList data = handler.ReadFile(correos);
        Correo input = null;

        for (int i = 0; i < data.size(); i++) {
            Correo aux = createCorreo(data.get(i).toString());
            if (aux.getEmisor().equals(emisor) && aux.getReceptor().equals(receptor) && aux.getFecha().equals(fecha)) {
                input = aux;
            }
        }
        return elementExists(this.root, input);
    }

    public ArrayList<Correo> searchBy(String field) {
        ArrayList data = handler.ReadFile(correos);
        ArrayList<Correo> response = new ArrayList();

        for (int i = 0; i < data.size(); i++) {   
            Correo aux = createCorreo(data.get(i).toString());
            if (data.get(i).toString().contains(field) && aux.getEmisor().equals(Data.Instance().user.getUsuario()) && Data.Instance().actual == 1 && aux.getEstatus().equals("1")) { // bandeja de salida
                response.add(aux);
            } else if (data.get(i).toString().contains(field) && aux.getReceptor().equals(Data.Instance().user.getUsuario()) && Data.Instance().actual == 2 && aux.getEstatus().equals("1")) { // bandeja de entrada
                response.add(aux);
            }
        }
        return response;
    }

    public Correo elementExists(Node<Correo> root, Correo element) {
        if (root == null) {
            return null;
        } else if (element.compareTo(root.getElement()) == 0) {
            return root.getElement();
        } else {
            if (element.compareTo(root.getElement()) < 0) {
                return elementExists(root.getLeft(), element);
            } else {
                return elementExists(root.getRight(), element);
            }
        }
    }

    public void logicalDelete(Correo input) {
        // elemento al que se le cambiará el estado.
        Correo temp = remove(this.root, input);

        // leer archivo 
        ArrayList tree = handler.ReadFile(correos);

        preOrder(this.root);
        PrintWriter writer;
        try {
            this.correos.createNewFile();
            // vaciar archivo            
            writer = new PrintWriter(correos);
            writer.print("");

            // ordenar por medio de la direccion fisica
            Collections.sort(order);

            int size = tree.size();
            for (int i = 0; i < size; i++) {
                String aux = tree.get(i).toString();

                // si contiene la clave
                if (aux.contains(temp.getEmisor()) && aux.contains(temp.getReceptor()) && aux.contains(temp.getFecha())) {
                    // eliminación lógica del correo 
                    temp.setEstatus("0");

                    // conseguir el numero de registro del elemento eliminado 
                    String[] fields = aux.split("\\|");
                    writer.println(fields[0] + "|" + "-" + "|" + "-" + "|" + temp.toString());

                    if (i < order.size()) {
                        writer.println(order.get(i).toString());
                    }
                } else {
                    if (i < order.size()) {
                        writer.println(order.get(i).toString());
                    }
                }
            }
            writer.close();
            updateDesc();
        } catch (IOException ex) {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }

        order = new ArrayList<Node<Correo>>();
    }

    private Correo remove(Node<Correo> root, Correo element) {
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
            updateDesc();

        } catch (IOException ex) {
            Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
        }

        order = new ArrayList<Node<Correo>>();
    }

    private Correo createCorreo(String line) {
        String[] fields = line.split("\\|");
        return new Correo(
                fields[3],
                fields[4],
                fields[5],
                fields[6],
                fields[7],
                fields[8],
                fields[9]
        );
    }

    public void count() {
        ArrayList tree = handler.ReadFile(correos);

        for (int i = 0; i < tree.size(); i++) {
            Correo aux = createCorreo(tree.get(i).toString());

            if ("1".equals(aux.getEstatus())) {
                this.active++;
            } else {
                this.inactive++;
            }
        }
    }

    public ArrayList<Correo> query(String usuario, int bandeja) {
        ArrayList data = handler.ReadFile(correos);
        ArrayList<Correo> response = new ArrayList();

        for (int i = 0; i < data.size(); i++) {
            Correo aux = createCorreo(data.get(i).toString());
            if (aux.getEmisor().equals(usuario) && bandeja == 1 && aux.getEstatus().equals("1")) { // bandeja de salida
                response.add(aux);
            } else if (aux.getReceptor().equals(usuario) && bandeja == 2 && aux.getEstatus().equals("1")) { // bandeja de entrada
                response.add(aux);
            }
        }
        return response;
    }

    public void updateDesc() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String fecha = dtf.format(now);

        preOrder(this.root);
        int size = order.size();
        this.active = 0;
        this.inactive = 0;
        count();

        try {

            if (desc_correos.createNewFile()) {
                PrintWriter descWriter = new PrintWriter(desc_correos);
                descWriter.print("nombre_simbolico: correos\n"
                        + "fecha_creacion: " + fecha + "\n"
                        + "usuario_creacion: " + order.get(size - 1).getElement().getEmisor() + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + order.get(size - 1).getElement().getEmisor() + "\n"
                        + "#_registros:" + (this.active + this.inactive) + "\n"
                        + "registros_activos: " + this.active + "\n"
                        + "registros_inactivos: " + this.inactive + "\n");
                descWriter.close();
            } else {
                ArrayList desc = handler.ReadFile(desc_correos);
                PrintWriter descWriter = new PrintWriter(desc_correos);
                descWriter.print("nombre_simbolico: correos\n"
                        + desc.get(1).toString() + "\n"
                        + desc.get(2).toString() + "\n"
                        + "fecha_modificacion: " + fecha + "\n"
                        + "usuario_modificacion: " + Data.Instance().user.getUsuario() + "\n"
                        + "#_registros:" + (this.active + this.inactive) + "\n"
                        + "registros_activos: " + this.active + "\n"
                        + "registros_inactivos: " + this.inactive + "\n");
                descWriter.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
