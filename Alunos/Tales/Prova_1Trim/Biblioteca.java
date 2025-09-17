import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Biblioteca {
private ArrayList<Livro> livros = new ArrayList<>();
private ArrayList<Cliente> clientes = new ArrayList<>();


private int proxIdLivro = 1;
private int proxIdCliente = 1;


public Cliente cadastrarCliente(String nome) {
Cliente c = new Cliente(proxIdCliente++, nome);
clientes.add(c);
return c;
}


public LivroComum cadastrarLivroComum(String titulo, String autor) {
LivroComum l = new LivroComum(proxIdLivro++, titulo, autor);
livros.add(l);
return l;
}


public LivroRaro cadastrarLivroRaro(String titulo, String autor, String motivo) {
LivroRaro l = new LivroRaro(proxIdLivro++, titulo, autor, motivo);
livros.add(l);
return l;
}


// ===== Buscas (primeiro resultado) =====
public Livro buscarPorTitulo(String parteTitulo) {
String q = parteTitulo.toLowerCase();
for (Livro l : livros) {
if (l.getTitulo().toLowerCase().contains(q)) return l;
}
return null;
}


public Livro buscarPorAutor(String parteAutor) {
String q = parteAutor.toLowerCase();
for (Livro l : livros) {
if (l.getAutor().toLowerCase().contains(q)) return l;
}
return null;
}


public Cliente buscarClientePorId(int id) {
for (Cliente c : clientes) if (c.getId() == id) return c;
return null;
}


// ===== Disponibilidade =====

public boolean disponivelParaEmprestimoPorTitulo(String titulo) {
Livro l = buscarPorTitulo(titulo);
if (l == null) return false;
return l.podeEmprestar() && !l.isEmprestado();
}


// ===== Empréstimo & Devolução =====
public boolean realizarEmprestimo(int clienteId, String titulo) {
Cliente cli = buscarClientePorId(clienteId);
if (cli == null) { System.out.println("Cliente não encontrado."); return false; }
Livro l = buscarPorTitulo(titulo);
if (l == null) { System.out.println("Livro não encontrado."); return false; }
if (!l.podeEmprestar()) { System.out.println("Livro raro: NÃO pode ser emprestado."); return false; }
if (l.isEmprestado()) { System.out.println("Este título já está emprestado."); return false; }


l.setEmprestado(true);
l.setDataEmprestimo(LocalDate.now());
System.out.println("Empréstimo concluído para " + cli.getNome() + ": '" + l.getTitulo() + "'.");
System.out.println("Devolva em até 7 dias para não pagar multa.");
return true;
}


public double realizarDevolucao(String titulo) {
Livro l = buscarPorTitulo(titulo);
if (l == null) { System.out.println("Livro não encontrado."); return 0.0; }
if (!l.isEmprestado()) { System.out.println("Este livro não está emprestado."); return 0.0; }


long dias = ChronoUnit.DAYS.between(l.getDataEmprestimo(), LocalDate.now());
long atraso = dias > 7 ? (dias - 7) : 0;
double multa = atraso * 3.50; // R$3,50 por dia após 7 dias


l.setEmprestado(false);
l.setDataEmprestimo(null);
return multa;
}


// ===== Listagens simples =====
public void listarLivros() { for (Livro l : livros) System.out.println(l); }
public void listarClientes() { for (Cliente c : clientes) System.out.println(c); }
}