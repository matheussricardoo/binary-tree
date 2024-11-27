package projarvorebinaria;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ProjArvoreBinaria {

    public static void main(String[] args) throws Exception {
        BinaryTree<Palavra> arvoreDePalavras = new BinaryTree<>();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1- Carregar o texto\n"
                    + "2- Contador de palavras\n"
                    + "3- Busca por palavra\n"
                    + "4- Exibição do texto\n"
                    + "5- Mostrar palavra mais frequente\n"
                    + "6- Mostrar palavras que aparecem uma vez\n"
                    + "7- Análise estatística do texto\n"
                    + "8- Exportar relatório\n"
                    + "9- Encerrar");
            
            int input = 0;
         
            while (true) {
                if (sc.hasNextInt()) {
                    input = sc.nextInt();
                    if (input >= 1 && input <= 9) {
                        break; 
                    } else {
                        System.out.println("Opção inválida. Digite um número entre 1 e 9.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    sc.next(); 
                }
            }
            
            switch (input) {
                case 1:
                    carregarTexto(arvoreDePalavras);
                    break;
                case 2:
                    contarPalavras(arvoreDePalavras);
                    break;
                case 3:
                    buscarPalavra(arvoreDePalavras, sc);
                    break;
                case 4:
                    exibirTexto(arvoreDePalavras);
                    break;
                case 5:
                    palavraMaisFrequente(arvoreDePalavras);
                    break;
                case 6:
                    palavrasUnicas(arvoreDePalavras);
                    break;
                case 7:
                    analisarEstatisticas(arvoreDePalavras);
                    break;
                case 8:
                    exportarRelatorio(arvoreDePalavras);
                    break;
                case 9:
                    System.out.println("Programa encerrado.");
                    sc.close();
                    return;
            }
        }
    }
    
    private static void carregarTexto(BinaryTree<Palavra> arvore) {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("\\3º Semestre\\Estrutura de Dados\\Parte 1 Proj MR\\ProjArvoreBinaria\\src\\projarvorebinaria\\333.txt"), 
                    "UTF-8"
                )
            );
            String linha;
            
            while ((linha = reader.readLine()) != null) {
                // Remove pontuações e converte para minúsculo
                linha = linha.replaceAll("[^a-zA-ZáàâãéèêíïóôõöúüçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÜÇÑ\\s]", "").toLowerCase();
                String[] palavras = linha.split("\\s+");
                
                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        Palavra novaPalavra = new Palavra(palavra);
                        arvore.inserir(novaPalavra);
                    }
                }
            }
            
            reader.close();
            System.out.println("TEXTO CARREGADO");
            
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
    
    private static void contarPalavras(BinaryTree<Palavra> arvore) {
        int totalPalavras = arvore.contaNo(arvore.root);
        System.out.println("Total de palavras diferentes no texto: " + totalPalavras);
    }
    
    private static void buscarPalavra(BinaryTree<Palavra> arvore, Scanner sc) {
        System.out.println("Digite a palavra que deseja buscar:");
        sc.nextLine(); 
        String palavraBusca = sc.nextLine().toLowerCase();
        
        Node<Palavra> resultado = arvore.busca(new Palavra(palavraBusca));
        
        if (resultado != null) {
            System.out.println("Palavra encontrada! Ocorrências: " + 
                             resultado.elemento.getOcorrencias());
        } else {
            System.out.println("Palavra não encontrada no texto.");
        }
    }
    
    private static void exibirTexto(BinaryTree<Palavra> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Árvore vazia. Carregue um texto primeiro.");
            return;
        }
        
        System.out.println("\nPalavras em ordem alfabética:");
        arvore.visitaInOrdem(arvore.root);
        System.out.println();
    }
    
    private static void palavraMaisFrequente(BinaryTree<Palavra> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Árvore vazia. Carregue um texto primeiro.");
            return;
        }
        
        Node<Palavra> atual = arvore.root;
        Node<Palavra> maisFrequente = encontraMaisFrequente(atual);
        
        if (maisFrequente != null) {
            System.out.println("Palavra mais frequente: " + 
                             maisFrequente.elemento.getPalavra() + 
                             " (" + maisFrequente.elemento.getOcorrencias() + " vezes)");
        }
    }
    
    private static Node<Palavra> encontraMaisFrequente(Node<Palavra> no) {
        if (no == null) return null;
        
        Node<Palavra> maisFrequente = no;
        
        Node<Palavra> esquerda = encontraMaisFrequente(no.left);
        if (esquerda != null && esquerda.elemento.getOcorrencias() > maisFrequente.elemento.getOcorrencias()) {
            maisFrequente = esquerda;
        }
        
        Node<Palavra> direita = encontraMaisFrequente(no.right);
        if (direita != null && direita.elemento.getOcorrencias() > maisFrequente.elemento.getOcorrencias()) {
            maisFrequente = direita;
        }
        
        return maisFrequente;
    }
    
    private static void palavrasUnicas(BinaryTree<Palavra> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Árvore vazia. Carregue um texto primeiro.");
            return;
        }
        
        System.out.println("\nPalavras que aparecem apenas uma vez:");
        mostrarPalavrasUnicas(arvore.root);
        System.out.println();
    }
    
    private static void mostrarPalavrasUnicas(Node<Palavra> no) {
        if (no == null) return;
        
        mostrarPalavrasUnicas(no.left);
        
        if (no.elemento.getOcorrencias() == 1) {
            System.out.print(no.elemento.getPalavra() + ", ");
        }
        
        mostrarPalavrasUnicas(no.right);
    }
    
    private static void analisarEstatisticas(BinaryTree<Palavra> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Árvore vazia. Carregue um texto primeiro.");
            return;
        }
        
        TextoEstatisticas stats = new TextoEstatisticas();
        stats.calcularEstatisticas(arvore.root);
        System.out.println(stats.toString());
    }
    
    private static void exportarRelatorio(BinaryTree<Palavra> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Árvore vazia. Carregue um texto primeiro.");
            return;
        }
        
        try {
            FileWriter writer = new FileWriter("relatorio_texto.txt", java.nio.charset.StandardCharsets.UTF_8);
            TextoEstatisticas stats = new TextoEstatisticas();
            stats.calcularEstatisticas(arvore.root);
            
            writer.write(stats.toString());
            writer.write("\n=== Todas as palavras em ordem alfabética ===\n");
            
            escreverPalavrasEmOrdem(arvore.root, writer);
            
            writer.close();
            System.out.println("Relatório exportado com sucesso para 'relatorio_texto.txt'");
        } catch (IOException e) {
            System.out.println("Erro ao exportar relatório: " + e.getMessage());
        }
    }
    
    private static void escreverPalavrasEmOrdem(Node<Palavra> no, FileWriter writer) throws IOException {
        if (no == null) return;
        
        escreverPalavrasEmOrdem(no.left, writer);
        writer.write(no.elemento.toString() + "\n");
        escreverPalavrasEmOrdem(no.right, writer);
    }
}


//Nome: Matheus Ricardo de Oliveira Silva
//RA: 10418754