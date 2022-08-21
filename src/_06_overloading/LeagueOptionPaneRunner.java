package _06_overloading;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		new LeagueOptionPane().run();
		new LeagueOptionPane().showMessageDialog("Hi");
		new LeagueOptionPane().showMessageDialog("Hello", "Titles!");
		new LeagueOptionPane().showMessageDialog("Cats", "Newest", "leagueDark.png");
		
	}
}
