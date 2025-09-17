import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Biblioteca bib = new Biblioteca();

        bib.cadastrarCliente("Ana");
        bib.cadastrarCliente("Bruno");
        bib.cadastrarCliente("Maria");
        bib.cadastrarCliente("Mario");
        bib.cadastrarLivroComum("Diario de um banana", "Jeff Kinney");
        bib.cadastrarLivroComum("Harry Potter", "Sei la");
        bib.cadastrarLivroRaro("O Servo dos Ossos", "Não tenho ideia", "Único exemplar");
        bib.cadastrarLivroRaro("Calculo", "James Stewart", "Único exemplar");

        
        while (true) { 
      

            System.out.println("============================");
            System.out.println(" Biblioteca — Menu Principal ");
            System.out.println("============================");
            System.out.println("1) Cadastrar cliente");
            System.out.println("2) Cadastrar livro (comum ou raro)");
            System.out.println("3) Buscar livro por TÍTULO (primeiro)");
            System.out.println("4) Buscar livro por AUTOR (primeiro)");
            System.out.println("5) Verificar disponibilidade por título");
            System.out.println("6) Realizar EMPRÉSTIMO");
            System.out.println("7) Realizar DEVOLUÇÃO");
            System.out.println("8) Listar (livros/clientes)");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String op = in.nextLine().trim();

            switch (op) {
                case "0": {
                    System.out.println("Saindo...");
                    in.close();      // fecha só ao sair
                    return;          // encerra o programa
                }
                case "1": {
                    System.out.print("Nome: ");
                    String nome = in.nextLine();
                    System.out.println("Cliente: " + bib.cadastrarCliente(nome));
                    break;
                }
                case "2": {
                    System.out.print("Tipo (C)omum ou (R)aro: ");
                    String tipo = in.nextLine().trim().toUpperCase();
                    System.out.print("Título: ");
                    String t = in.nextLine();
                    System.out.print("Autor: ");
                    String a = in.nextLine();
                    if (tipo.equals("C")) {
                        System.out.println("Livro: " + bib.cadastrarLivroComum(t, a));
                    } else if (tipo.equals("R")) {
                        System.out.print("Motivo da raridade: ");
                        String m = in.nextLine();
                        System.out.println("Livro: " + bib.cadastrarLivroRaro(t, a, m));
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;
                }
                case "3": {
                    System.out.print("Parte do título: ");
                    String t = in.nextLine();
                    Livro l = bib.buscarPorTitulo(t);
                    System.out.println(l == null ? "Nenhum encontrado." : ("Encontrado: " + l));
                    break;
                }
                case "4": {
                    System.out.print("Parte do autor: ");
                    String a = in.nextLine();
                    Livro l = bib.buscarPorAutor(a);
                    System.out.println(l == null ? "Nenhum encontrado." : ("Encontrado: " + l));
                    break;
                }
                case "5": {
                    System.out.print("Título: ");
                    String t = in.nextLine();
                    boolean ok = bib.disponivelParaEmprestimoPorTitulo(t);
                    System.out.println(ok ? "Disponível." : "Indisponível.");
                    break;
                }
                case "6": {
                    System.out.print("ID do cliente: ");
                    int id = Integer.parseInt(in.nextLine()); // evita problema do nextInt()
                    System.out.print("Título do livro: ");
                    String t = in.nextLine();
                    bib.realizarEmprestimo(id, t);
                    break;
                }
                case "7": {
                    System.out.print("Título do livro: ");
                    String t = in.nextLine();
                    double multa = bib.realizarDevolucao(t);
                    if (multa > 0) System.out.printf("Devolvido com multa: R$ %.2f%n", multa);
                    else System.out.println("Devolvido sem multa.");
                    break;
                }
                case "8": {
                    System.out.println("1) Listar livros 2) Listar clientes");
                    String qual = in.nextLine().trim();
                    if (qual.equals("1")) bib.listarLivros();
                    else if (qual.equals("2")) bib.listarClientes();
                    else System.out.println("Opção inválida.");
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println(); 
        } 
    }
}
