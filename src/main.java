/**
 * Created by raving on 28/06/17.
 */
public class main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                gui.createAndShowGUI();
            }
        });
    }
}
