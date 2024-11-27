package projarvorebinaria;

import java.util.ArrayList;
import java.util.List;

public class TextoEstatisticas {
    private int totalPalavras;
    private int palavrasUnicas;
    private double mediaOcorrencias;
    private List<Palavra> palavrasFrequentes;
    
    public TextoEstatisticas() {
        palavrasFrequentes = new ArrayList<>();
    }
    
    public void calcularEstatisticas(Node<Palavra> raiz) {
        totalPalavras = calcularTotalPalavras(raiz);
        palavrasUnicas = contarPalavrasUnicas(raiz);
        mediaOcorrencias = (double) totalPalavras / palavrasUnicas;
        encontrarPalavrasFrequentes(raiz);
    }
    
    private int calcularTotalPalavras(Node<Palavra> no) {
        if (no == null) return 0;
        return no.elemento.getOcorrencias() + 
               calcularTotalPalavras(no.left) + 
               calcularTotalPalavras(no.right);
    }
    
    private int contarPalavrasUnicas(Node<Palavra> no) {
        if (no == null) return 0;
        return 1 + contarPalavrasUnicas(no.left) + 
                   contarPalavrasUnicas(no.right);
    }
    
    private void encontrarPalavrasFrequentes(Node<Palavra> no) {
        if (no == null) return;
        
        if (no.elemento.getOcorrencias() > 5) {
            palavrasFrequentes.add(no.elemento);
        }
        
        encontrarPalavrasFrequentes(no.left);
        encontrarPalavrasFrequentes(no.right);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Estatísticas do Texto ===\n");
        sb.append("Total de palavras (incluindo repetições): ").append(totalPalavras).append("\n");
        sb.append("Palavras únicas: ").append(palavrasUnicas).append("\n");
        sb.append("Média de ocorrências por palavra: ").append(String.format("%.2f", mediaOcorrencias)).append("\n");
        sb.append("Palavras mais frequentes (>5 ocorrências):\n");
        
        for (Palavra p : palavrasFrequentes) {
            sb.append("- ").append(p.toString()).append("\n");
        }
        
        return sb.toString();
    }
} 