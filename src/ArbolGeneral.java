


public class ArbolGeneral {

    private NodoGeneral raiz;

    // Constructor vacío
    public ArbolGeneral() {
        raiz = null;
    }

    // Constructor con nodo raíz
    public ArbolGeneral(NodoGeneral nodo) {
        this.raiz = nodo;
    }

    // Método para verificar si el árbol está vacío
    public boolean esVacio() {
        return raiz == null;
    }

    // Método para vaciar el árbol
    public void vaciar() {
        raiz = null;
    }

    // Método para obtener la raíz del árbol
    public NodoGeneral raiz() throws Exception {
        if (esVacio()) {
            throw new Exception("Error en la raiz ya que el árbol está vacío");
        }
        return raiz;
    }

    // Método para obtener el primer hijo del árbol
    public ArbolGeneral hijo1() throws Exception {
        if (esVacio()) {
            throw new Exception("Error en el hijo1 ya que el árbol está vacío");
        }
        return new ArbolGeneral(raiz.hijo1);
    }

    // Método para obtener el hijo2 derecho del árbol
    public ArbolGeneral hijo2() throws Exception {
        if (esVacio()) {
            throw new Exception("Error en el hijo2 ya que el árbol está vacío");
        }
        return new ArbolGeneral(raiz.hijo2);
    }

    // Método para obtener el padre del árbol
    public ArbolGeneral padre() throws Exception {
        if (raiz == null) {
            throw new Exception("Error en el padre ya que el árbol está vacío");
        }
        return new ArbolGeneral(raiz.padre);
    }


    // Método para añadir un hijo al árbol
    public void addHijo(NodoGeneral hijo) throws Exception {
        if (hijo == null) {
            throw new Exception("Error al añadir hijo ya que es nulo");
        }
        if (raiz == null) {
            throw new Exception("Error al añadir hijo ya que el árbol está vacío");
        }

        if (raiz.hijo1 == null) {
            raiz.hijo1 = hijo;
            hijo.padre = raiz;
        } else {
            NodoGeneral aux = raiz.hijo1;
            while (aux.hijo2 != null) {
                aux = aux.hijo2;
            }
            aux.hijo2 = hijo;
            hijo.padre = raiz;
        }
    }

    // Método para deleteArbol un subárbol
    public void deleteArbol(ArbolGeneral subarbol) throws Exception {
        if (subarbol == null) {
            throw new Exception("Error al borrar el subárbol ya que es nulo");
        }

        if (subarbol.raiz == raiz) {
            raiz = null;
        } else {
            ArbolGeneral padre = subarbol.padre();
            if (padre.esVacio()) {
                throw new Exception("Error al borrar el subárbol ya que el padre es nulo");
            } else {
                NodoGeneral aux = padre.raiz.hijo1;
                if (aux == subarbol.raiz) {
                    padre.raiz.hijo1 = aux.hijo2;
                } else {
                    while (aux.hijo2 != subarbol.raiz) {
                        aux = aux.hijo2;
                    }
                    aux.hijo2 = aux.hijo2.hijo2;
                }
            }
        }
    }

    // Método para imprimir el árbol
    public void pintaArbol(int espacios) {
        if (!esVacio()) {
            for (int i = 0; i < espacios; i++) {
                System.out.print("  ");
            }
            System.out.print("|");
            System.out.println(raiz.dato + " ");

            try {
                for (ArbolGeneral aux = hijo1(); !aux.esVacio(); aux = aux.hijo2()) {
                    aux.pintaArbol(espacios + 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método para calcular el grado del árbol
    public int calculGrado(NodoGeneral nodo) throws Exception {
        if (nodo == null) {
            throw new Exception("Error al calcular el grado ya que el nodo es nulo");
        } else {
            int grado = 0;
            NodoGeneral aux = nodo.hijo1;
            while (aux != null) {
                grado++;
                aux = aux.hijo2;
            }
            return grado;
        }
    }
    // Método principal
    public static void main(String[] args) throws Exception {
        // Creación de nodos y árboles
        NodoGeneral n = new NodoGeneral(1);
        ArbolGeneral a = new ArbolGeneral(n);
        n = new NodoGeneral(4);
        ArbolGeneral b = new ArbolGeneral(n);
        n = new NodoGeneral(7);
        ArbolGeneral c = new ArbolGeneral(n);
        n = new NodoGeneral(-2);
        ArbolGeneral d = new ArbolGeneral(n);
        ArbolGeneral e = new ArbolGeneral(new NodoGeneral(3));
        ArbolGeneral f = new ArbolGeneral(new NodoGeneral(33));
        ArbolGeneral g = new ArbolGeneral(new NodoGeneral(2));
        ArbolGeneral h = new ArbolGeneral(new NodoGeneral(-1));
        ArbolGeneral i = new ArbolGeneral(new NodoGeneral(66));

        try {
            // Añadir hijos y realizar operaciones en los árboles
            c.addHijo(new NodoGeneral(-9));
            a.addHijo(c.raiz());
            a.addHijo(b.raiz());
            b.addHijo(d.raiz());
            c.addHijo(new NodoGeneral(9));
            e.addHijo(a.raiz());
            b.addHijo(f.raiz());
            b.addHijo(g.raiz());
            b.addHijo(h.raiz());
            b.addHijo(i.raiz());

            // Imprimir el árbol antes de la eliminación
            System.out.println("Arbol:");
            e.pintaArbol(0);

            // Eliminar un subárbol
            e.deleteArbol(c);
            System.out.println("Arbol después de borrar el subárbol 'c':");
            e.pintaArbol(0);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        // Calcular el grado del árbol
        int gradoDelArbol = e.calculGrado(e.raiz());
        System.out.println("El grado del árbol es: " + gradoDelArbol);
    }
}
