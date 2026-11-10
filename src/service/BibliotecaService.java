package service;

import model.Livro;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<Livro> acervo;

    public BibliotecaService() {
        this.acervo = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        acervo.add(livro);
    }

    public List<Livro> listarLivros() {
        return acervo;
    }

    public Livro buscarLivro(int codigo) throws LivroNaoEncontradoException {
        for (Livro livro : acervo) {
            if (livro.getCodigo() == codigo) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("Erro: Livro com código " + codigo + " não encontrado no acervo.");
    }

    public void emprestarLivro(int codigo) throws LivroNaoEncontradoException {
        Livro livro = buscarLivro(codigo);
        livro.emprestar();
    }

    public void devolverLivro(int codigo) throws LivroNaoEncontradoException {
        Livro livro = buscarLivro(codigo);
        livro.devolver();
    }
}