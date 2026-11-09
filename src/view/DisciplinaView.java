package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Disciplina;

/**
 * Tela de cadastro de disciplinas (View do MVC).
 *
 * Esta classe só cuida da parte visual: ela mostra os componentes e repassa
 * os eventos para o controller. Nenhuma regra de negócio fica aqui.
 */
public class DisciplinaView extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUNAS = { "Código", "Nome", "Docente", "Período",
			"Horário", "Sala", "Carga horária", "Limite de faltas" };

	private final JTextField txtCodigo = new JTextField(20);
	private final JTextField txtNome = new JTextField(20);
	private final JTextField txtDocente = new JTextField(20);
	private final JTextField txtPeriodoLetivo = new JTextField(20);
	private final JTextField txtHorario = new JTextField(20);
	private final JTextField txtLocalSala = new JTextField(20);
	private final JTextField txtCargaHoraria = new JTextField(20);

	private final JButton btnSalvar = new JButton("Cadastrar");
	private final JButton btnNovo = new JButton("Limpar / Novo");

	private final DefaultTableModel modeloTabela = new DefaultTableModel(COLUNAS, 0) {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private final JTable tabela = new JTable(modeloTabela);

	public DisciplinaView() {
		super("Cadastro de Disciplinas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		add(criarPainelFormulario(), BorderLayout.NORTH);
		add(criarPainelTabela(), BorderLayout.CENTER);

		getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setMinimumSize(new Dimension(820, 560));
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel criarPainelFormulario() {
		JPanel painel = new JPanel(new GridBagLayout());
		painel.setBorder(BorderFactory.createTitledBorder("Dados da disciplina"));

		adicionarCampo(painel, 0, "Código *", txtCodigo);
		adicionarCampo(painel, 1, "Nome *", txtNome);
		adicionarCampo(painel, 2, "Docente *", txtDocente);
		adicionarCampo(painel, 3, "Período letivo (ex.: 2026/2)", txtPeriodoLetivo);
		adicionarCampo(painel, 4, "Horário (ex.: Seg 07:30-09:10)", txtHorario);
		adicionarCampo(painel, 5, "Local / Sala", txtLocalSala);
		adicionarCampo(painel, 6, "Carga horária (aulas) *", txtCargaHoraria);

		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botoes.add(btnNovo);
		botoes.add(btnSalvar);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		painel.add(botoes, gbc);

		return painel;
	}

	private void adicionarCampo(JPanel painel, int linha, String rotulo, JTextField campo) {
		GridBagConstraints gbcRotulo = new GridBagConstraints();
		gbcRotulo.gridx = 0;
		gbcRotulo.gridy = linha;
		gbcRotulo.anchor = GridBagConstraints.WEST;
		gbcRotulo.insets = new Insets(4, 6, 4, 6);
		painel.add(new JLabel(rotulo), gbcRotulo);

		GridBagConstraints gbcCampo = new GridBagConstraints();
		gbcCampo.gridx = 1;
		gbcCampo.gridy = linha;
		gbcCampo.weightx = 1.0;
		gbcCampo.fill = GridBagConstraints.HORIZONTAL;
		gbcCampo.insets = new Insets(4, 6, 4, 6);
		painel.add(campo, gbcCampo);
	}

	private JScrollPane criarPainelTabela() {
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.getTableHeader().setReorderingAllowed(false);

		int[] larguras = { 70, 180, 180, 70, 130, 80, 110, 110 };
		for (int i = 0; i < larguras.length; i++) {
			tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
		}

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBorder(BorderFactory.createTitledBorder("Disciplinas cadastradas (selecione uma para alterar)"));
		scroll.setPreferredSize(new Dimension(780, 220));
		return scroll;
	}

	// ---------- Leitura dos campos ----------

	public String getCodigo() {
		return txtCodigo.getText().trim();
	}

	public String getNome() {
		return txtNome.getText().trim();
	}

	public String getDocente() {
		return txtDocente.getText().trim();
	}

	public String getPeriodoLetivo() {
		return txtPeriodoLetivo.getText().trim();
	}

	public String getHorario() {
		return txtHorario.getText().trim();
	}

	public String getLocalSala() {
		return txtLocalSala.getText().trim();
	}

	public String getCargaHoraria() {
		return txtCargaHoraria.getText().trim();
	}

	public int getLinhaSelecionada() {
		return tabela.getSelectedRow();
	}

	// ---------- Atualização da tela ----------

	/** Coloca a tela em modo de alteração, com os dados da disciplina escolhida. */
	public void preencherFormulario(Disciplina d) {
		txtCodigo.setText(d.getCodigo());
		txtNome.setText(d.getNome());
		txtDocente.setText(d.getDocente());
		txtPeriodoLetivo.setText(d.getPeriodoLetivo());
		txtHorario.setText(d.getHorario());
		txtLocalSala.setText(d.getLocalSala());
		txtCargaHoraria.setText(String.valueOf(d.getCargaHoraria()));

		txtCodigo.setEnabled(false); // o código identifica a disciplina e não muda
		btnSalvar.setText("Salvar alterações");
	}

	/** Volta a tela para o modo de novo cadastro. */
	public void limparFormulario() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtDocente.setText("");
		txtPeriodoLetivo.setText("");
		txtHorario.setText("");
		txtLocalSala.setText("");
		txtCargaHoraria.setText("");

		txtCodigo.setEnabled(true);
		btnSalvar.setText("Cadastrar");
		tabela.clearSelection();
		txtCodigo.requestFocusInWindow();
	}

	public void atualizarTabela(List<Disciplina> disciplinas) {
		modeloTabela.setRowCount(0);
		for (Disciplina d : disciplinas) {
			modeloTabela.addRow(new Object[] { d.getCodigo(), d.getNome(), d.getDocente(),
					d.getPeriodoLetivo(), d.getHorario(), d.getLocalSala(),
					d.getCargaHoraria(), d.getLimiteFaltas() });
		}
	}

	public void mostrarErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
	}

	public void mostrarSucesso(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}

	// ---------- Ligação com o controller ----------

	public void addSalvarListener(ActionListener listener) {
		btnSalvar.addActionListener(listener);
	}

	public void addNovoListener(ActionListener listener) {
		btnNovo.addActionListener(listener);
	}

	public void addSelecaoTabelaListener(ListSelectionListener listener) {
		tabela.getSelectionModel().addListSelectionListener(listener);
	}
}
