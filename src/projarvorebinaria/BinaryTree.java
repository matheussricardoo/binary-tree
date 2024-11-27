package projarvorebinaria;

public class BinaryTree<E> {
    Node<E> root;

    public BinaryTree() {
        root = null;
    }

    // Método para verificar se a árvore está vazia
    public boolean isEmpty() {
        return root == null;
    }

    // Método para adicionar o nó raiz
    public void addRoot(Node<E> e) throws Exception {
        if (root != null) {
            throw new Exception("Árvore já tem raiz");
        }
        root = e;
    }

    // Método para inserir elementos na árvore
    public void inserir(E elemento) {
        root = inserirRecursivo(root, elemento);
    }

    private Node<E> inserirRecursivo(Node<E> atual, E elemento) {
        if (atual == null) {
            return new Node<>(elemento);
        }

        // Comparação dos elementos (palavras)
        int comparacao = compareElementos(elemento, atual.elemento);

        if (comparacao < 0) {
            atual.left = inserirRecursivo(atual.left, elemento);
        } else if (comparacao > 0) {
            atual.right = inserirRecursivo(atual.right, elemento);
        } else {
            // Se a palavra já existe, incrementa a contagem
            if (atual.elemento instanceof Palavra) {
                ((Palavra) atual.elemento).incrementarOcorrencias();
            }
            System.out.println("Palavra já existe, ocorrências incrementadas: " + elemento);
        }

        return atual;
    }

    // Método para comparar os elementos (comparando as palavras)
    private int compareElementos(E e1, E e2) {
        if (e1 instanceof Palavra && e2 instanceof Palavra) {
            return ((Palavra) e1).getPalavra().compareTo(((Palavra) e2).getPalavra());
        }
        return e1.toString().compareTo(e2.toString()); 
    }

    // Método para buscar uma palavra na árvore
    public Node<E> busca(E elemento) {
        return buscaRecursiva(root, elemento);
    }

    private Node<E> buscaRecursiva(Node<E> atual, E elemento) {
        if (atual == null) {
            return null; // Palavra não encontrada
        }

        int comparacao = compareElementos(elemento, atual.elemento);

        if (comparacao < 0) {
            return buscaRecursiva(atual.left, elemento); // Buscar à esquerda
        } else if (comparacao > 0) {
            return buscaRecursiva(atual.right, elemento); // Buscar à direita
        } else {
            return atual; // Palavra encontrada
        }
    }

    // Exibe os elementos da árvore em ordem (ERD)
    public void visitaInOrdem(Node<E> e) {
        if (e == null) {
            return;
        }
        visitaInOrdem(e.left);
        e.exibeNo(); // Exibe o nó
        visitaInOrdem(e.right);
    }

    // Função para contar o número total de nós
    public int contaNo(Node<E> e) {
        if (e == null) {
            return 0;
        }
        return 1 + contaNo(e.left) + contaNo(e.right);
    }
}

//Nome: Matheus Ricardo de Oliveira Silva
//RA: 10418754