package model;

/**
 * Representa uma disciplina (unidade curricular), conforme a tabela "disciplinas" do DER.
 */
public class Disciplina {

	/** RN01: frequência mínima de 75% para aprovação. */
	public static final double FREQUENCIA_MINIMA = 0.75;

	/** RN02: média mínima de 6,0 para aprovação. */
	public static final double MEDIA_MINIMA = 6.0;

	private String codigo;
	private String nome;
	private String docente;
	private String periodoLetivo;
	private String horario;
	private String localSala;
	private int cargaHoraria;
	private int totalAulasLecionadas;
	private int totalFaltas;
	private int limiteFaltas;
	private double mediaParcial;
	private Situacao situacao;

	public Disciplina(String codigo, String nome, String docente, String periodoLetivo,
			String horario, String localSala, int cargaHoraria) {
		this.codigo = codigo;
		this.nome = nome;
		this.docente = docente;
		this.periodoLetivo = periodoLetivo;
		this.horario = horario;
		this.localSala = localSala;
		this.cargaHoraria = cargaHoraria;
		this.limiteFaltas = calcularLimiteFaltas(cargaHoraria);
		this.totalAulasLecionadas = 0;
		this.totalFaltas = 0;
		this.mediaParcial = 0.0;
		this.situacao = Situacao.EM_CURSO;
	}

	/**
	 * Calcula o limite de faltas a partir da carga horária, aplicando a RN01
	 * (o aluno precisa de no mínimo 75% de frequência, ou seja, pode faltar até 25%).
	 */
	public static int calcularLimiteFaltas(int cargaHoraria) {
		return (int) Math.floor(cargaHoraria * (1 - FREQUENCIA_MINIMA));
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}

	public String getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(String periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocalSala() {
		return localSala;
	}

	public void setLocalSala(String localSala) {
		this.localSala = localSala;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	/** Ao alterar a carga horária, o limite de faltas é recalculado (RN01). */
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
		this.limiteFaltas = calcularLimiteFaltas(cargaHoraria);
	}

	public int getTotalAulasLecionadas() {
		return totalAulasLecionadas;
	}

	public void setTotalAulasLecionadas(int totalAulasLecionadas) {
		this.totalAulasLecionadas = totalAulasLecionadas;
	}

	public int getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(int totalFaltas) {
		this.totalFaltas = totalFaltas;
	}

	public int getLimiteFaltas() {
		return limiteFaltas;
	}

	public double getMediaParcial() {
		return mediaParcial;
	}

	public void setMediaParcial(double mediaParcial) {
		this.mediaParcial = mediaParcial;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return codigo + " - " + nome;
	}
}
