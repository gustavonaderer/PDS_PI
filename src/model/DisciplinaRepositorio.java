package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guarda as disciplinas em memória.
 *
 * Como o resto do sistema só conversa com esta classe, no futuro ela pode ser
 * trocada por uma versão que grava em arquivo ou banco de dados sem mudar
 * a view nem o controller.
 */
public class DisciplinaRepositorio {

	private final List<Disciplina> disciplinas = new ArrayList<>();

	public void adicionar(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public boolean existeCodigo(String codigo) {
		return buscarPorCodigo(codigo) != null;
	}

	public Disciplina buscarPorCodigo(String codigo) {
		for (Disciplina d : disciplinas) {
			if (d.getCodigo().equalsIgnoreCase(codigo)) {
				return d;
			}
		}
		return null;
	}

	/** Retorna uma visão somente leitura da lista, na ordem de cadastro. */
	public List<Disciplina> listar() {
		return Collections.unmodifiableList(disciplinas);
	}
}
