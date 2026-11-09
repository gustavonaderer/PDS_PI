import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.DisciplinaController;
import model.DisciplinaRepositorio;
import view.DisciplinaView;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// se não conseguir, usa o visual padrão do Swing
		}

		SwingUtilities.invokeLater(() -> {
			DisciplinaRepositorio repositorio = new DisciplinaRepositorio();
			DisciplinaView view = new DisciplinaView();
			new DisciplinaController(view, repositorio);
			view.setVisible(true);
		});
	}

}
