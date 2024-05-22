public class NodoGeneral{
    public int dato;        // dato que se guarda en el nodo
    public NodoGeneral hijo1; // referencia al hijo1 de la izquierda
    public NodoGeneral hijo2;    // referencia al hijo2 de la derecha
    public NodoGeneral padre;      // referencia al padre

    // constructor
    public NodoGeneral(int info){
        dato = info;
        hijo1 = null;
        hijo2 = null;
        padre = null;
    }
}
