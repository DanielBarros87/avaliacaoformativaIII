package app;

import model.Livro;
import service.BibliotecaService;
import service.LivroNaoEncontradoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaService service = new BibliotecaService();
        Scanner scanner = new Scanner(System.in);

        // Teste obrigatório 1: Cadastrar pelo menos três livros
        service.cadastrarLivro(new Livro(101, "Estrutura de Dados", "Szwarcfiter"));
        service.cadastrarLivro(new Livro(102, "Clean Code", "Robert C. Martin"));
        service.cadastrarLivro(new Livro(103, "Java Efetivo", "Joshua Bloch"));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Sistema de Gerenciamento de Biblioteca ===");
            System.out.println("1. Cadastrar novo livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Buscar livro por código");
            System.out.println("4. Emprestar livro");
            System.out.println("5. Devolver livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o código do livro: ");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o autor do livro: ");
                        String autor = scanner.nextLine();
                        
                        service.cadastrarLivro(new Livro(codigo, titulo, autor));
                        System.out.println("Livro cadastrado com sucesso!");
                        break;

                    case 2:
                        // Teste obrigatório 2: Listar os livros cadastrados
                        System.out.println("\n--- Acervo ---");
                        for (Livro l : service.listarLivros()) {
                            System.out.println(l.toString());
                        }
                        break;

                    case 3:
                        // Teste obrigatório 3 e 7: Buscar livro existente e inexistente (Exceção)
                        System.out.print("Digite o código para busca: ");
                        int codBusca = scanner.nextInt();
                        Livro livroEncontrado = service.buscarLivro(codBusca);
                        System.out.println("Encontrado: " + livroEncontrado.toString());
                        break;

                    case 4:
                        // Teste obrigatório 4 e 5: Emprestar livro e tentar emprestar livro já emprestado
                        System.out.print("Digite o código do livro a emprestar: ");
                        int codEmprestimo = scanner.nextInt();
                        service.emprestarLivro(codEmprestimo);
                        System.out.println("Empréstimo realizado com sucesso!");
                        break;

                    case 5:
                        // Teste obrigatório 6: Devolver um livro
                        System.out.print("Digite o código do livro a devolver: ");
                        int codDevolucao = scanner.nextInt();
                        service.devolverLivro(codDevolucao);
                        System.out.println("Devolução realizada com sucesso!");
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (LivroNaoEncontradoException | IllegalStateException e) {
                
                System.out.println("! Erro de Operação: " + e.getMessage());
            } catch (InputMismatchException e) {
            
                System.out.println("! Erro de Entrada: Por favor, digite apenas números.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}