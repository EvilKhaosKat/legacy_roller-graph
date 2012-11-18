package views.optionsframe;

public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AddTabFrame atf = new AddTabFrame();
		atf.pack();
		atf.setVisible(true);
		MainFrame mf = new MainFrame("test");
		mf.pack();
		mf.setVisible(true);
	}

}
