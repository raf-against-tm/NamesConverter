import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafvall on 16/09/2015.
 */
public class Main {

    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Look and Feel Error: " + e.getMessage() + JOptionPane.ERROR_MESSAGE);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    // Build Main UI
                    MainUI window = new MainUI();

                    // Shows UI
                    window.getAppUI().setVisible(true);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
