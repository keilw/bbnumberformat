package sample;

import javame.text.DecimalFormat;
import net.rim.device.api.system.KeypadListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class NumberFormatApp extends UiApplication {

	public NumberFormatApp() {
		pushScreen(new Scr());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberFormatApp app = new NumberFormatApp();
		app.enterEventDispatcher();
	}
}

class Scr extends MainScreen implements KeypadListener {
	public Scr() {
		String[] patterns = new String[] { "#,#00.00#", "0.0;(0.0)",
				"0.###E0" };
		DecimalFormat format = (DecimalFormat) DecimalFormat
				.getNumberInstance();
		double value = -12.321;
		for (int i = 0; i < patterns.length; i++) {
			String pattern = patterns[i];
			format.applyPattern(pattern);
			String text = "Pattern: " + pattern + " Sample: "+format.format(value)+"\n";
			add(new LabelField(text));
		}
	}
}
