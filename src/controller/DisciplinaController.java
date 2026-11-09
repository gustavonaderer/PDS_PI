package controller;

import model.Disciplina;
import model.DisciplinaRepositorio;
import view.DisciplinaView;

/**
 * Controller do cadastro de disciplinas.
 *
 * Recebe os eventos da view, valida os dados digitados e atualiza o model.
 */
public class DisciplinaController {

	private final DisciplinaView view;
	private final DisciplinaRepositorio repositorio;

	/** Disciplina que está sendo alterada; null quando a tela está em modo de novo cadastro. */
	private Disciplina emEdicao;

	public DisciplinaController(DisciplinaView view, DisciplinaRepositorio repositorio) {
		this.view = view;
		this.repositorio = repositorio;

		view.addSalvarListener(e -> salvar());
		view.addNovoListener(e -> novo());
		view.addSelecaoTabelaListener(e -> {
			if (!e.getValueIsAdjusting()) {
				selecionar();
			}
		});

		view.atualizarTabela(repositorio.listar());
	}

	private void novo() {
		emEdicao = null;
		view.limparFormulario();
	}

	private void selecionar() {
		int linha = view.getLinhaSelecionada();
		if (linha < 0) {
			return; // nenhuma linha selecionada (ex.: tabela foi atualizada ou limpa)
		}
		emEdicao = repositorio.listar().get(linha);
		view.preencherFormulario(emEdicao);
	}

	private void salvar() {
		String codigo = view.getCodigo();
		String nome = view.getNome();
		String docente = view.getDocente();
		String periodo = view.getPeriodoLetivo();
		String horario = view.getHorario();
		String sala = view.getLocalSala();

		if (codigo.isEmpty() || nome.isEmpty() || docente.isEmpty()) {
			view.mostrarErro("Preencha os campos obrigatórios: código, nome e docente.");
			return;
		}

		int cargaHoraria;
		try {
			cargaHoraria = Integer.parseInt(view.getCargaHoraria());
		} catch (NumberFormatException ex) {
			view.mostrarErro("A carga horária deve ser um número inteiro.");
			return;
		}
		if (cargaHoraria <= 0) {
			view.mostrarErro("A carga horária deve ser maior que zero.");
			return;
		}

		if (emEdicao == null) {
			if (repositorio.existeCodigo(codigo)) {
				view.mostrarErro("Já existe uma disciplina cadastrada com o código " + codigo + ".");
				return;
			}
			repositorio.adicionar(new Disciplina(codigo, nome, docente, periodo, horario, sala, cargaHoraria));
			view.mostrarSucesso("Disciplina cadastrada com sucesso!");
		} else {
			emEdicao.setNome(nome);
			emEdicao.setDocente(docente);
			emEdicao.setPeriodoLetivo(periodo);
			emEdicao.setHorario(horario);
			emEdicao.setLocalSala(sala);
			emEdicao.setCargaHoraria(cargaHoraria);
			view.mostrarSucesso("Disciplina alterada com sucesso!");
		}

		view.atualizarTabela(repositorio.listar());
		novo();
	}
}
