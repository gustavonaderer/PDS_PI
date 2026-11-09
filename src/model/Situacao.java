package model;

/**
 * Situação acadêmica de uma disciplina (campo "situacao" da tabela disciplinas no DER).
 */
public enum Situacao {
	EM_CURSO("Em curso"),
	APROVADO("Aprovado"),
	REPROVADO_POR_NOTA("Reprovado por nota"),
	REPROVADO_POR_FALTA("Reprovado por falta");

	private final String descricao;

	Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
