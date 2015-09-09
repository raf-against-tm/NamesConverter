import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Rafvall on 27/8/2015.
 */
public class MainUI {

    private JFrame mainUI;

    private JTabbedPane mainTabs;
    private JPanel delete;
    private JPanel insert;
    private JToolBar mainMenu;
    private JPanel count;
    private JTextField deletePath;
    private JTextField deleteText;
    private JProgressBar deleteProgress;
    private JButton deleteSelect;
    private JButton deleteAction;

    private JMenuBar menuBar;

    /**
     * Build UI
     */
    public MainUI() {

        // Main Window
        mainUI= new JFrame();
        mainUI.setTitle("Names Converter 1.0");
        mainUI.setBounds(100, 100, 618, 389);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        menuBar = new JMenuBar();
        mainUI.setJMenuBar(menuBar);

        // - Menu Option: File
        JMenu mFile = new JMenu("File");
        menuBar.add(mFile);

        // -- Option Items
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        mFile.add(itemExit);

        // - Menu Option: Help
        JMenu mHelp = new JMenu("Help");
        menuBar.add(mHelp);

        // Option Items
        JMenuItem itemAbout = new JMenuItem("About...");
        itemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI.about();
            }
        });

        mHelp.add(itemAbout);

    }

    /**
     * About app info
     */
    private static void about(){


    }

    /**
     * Launch app
     * @param args
     */
    public static void main(String[] args) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el Look and Feel: " + e.getMessage() + JOptionPane.ERROR_MESSAGE);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainUI window = new MainUI();
                    window.mainUI.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
