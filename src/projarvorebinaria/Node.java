package projarvorebinaria;

public class  Node <E> {

    E elemento; // elemento armazenado no nó
    Node <E> left, right, parent; // apontadores do nó

    Node(E elemento) {
        this.elemento = elemento;
        left = right = parent = null;
    
    }
    
    public void exibeNo(){
        System.out.print(elemento + " - ");
    }
}

//Nome: Matheus Ricardo de Oliveira Silva
//RA: 10418754