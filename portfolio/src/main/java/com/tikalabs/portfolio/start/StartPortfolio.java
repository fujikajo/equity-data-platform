package com.tikalabs.portfolio.start;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StartPortfolio {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		AppViewer window = new AppViewer(shell);
		window.setBlockOnOpen(true); //

		// Fenster modal öffnen
		window.open(); // Öffnet das Fenster
		// Display.getCurrent().dispose(); // Display-Ressourcen freigeben
	}
}
