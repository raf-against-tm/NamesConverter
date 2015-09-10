import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by Rafvall on 27/8/2015.
 */
public class MainUI {

    private JFrame mainUI;

    private JToolBar mainMenu;
    private JMenuBar menuBar;
    private JTabbedPane mainTabs;

    // Tabs
    private JPanel deleteTab;
    private JPanel insertTab;
    private JPanel countTab;

    // Text fields
    private JTextField deletePath;
    private JTextField deleteText;
    private JProgressBar deleteProgress;

    // Labels
    private JLabel deletePathLabel;
    private JLabel deleteTextLabel;

    // Buttons
    private JButton deleteSelectButton;
    private JButton deleteActionButton;

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
                JOptionPane.showMessageDialog(mainUI, "Eggs are not supposed to be green.");
            }
        });

        mHelp.add(itemAbout);

        // Tabs
        //mainTabs = new JTabbedPane(JTabbedPane.TOP);

        GroupLayout groupLayout = new GroupLayout(mainUI.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(mainTabs, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                .addGap(5))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainTabs, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                                .addContainerGap())
        );

        // - Delete Tab
        //deleteTab = new JPanel();
        //mainTabs.addTab("Delete", null, deleteTab, null);

        //deletePath = new JTextField();
        deletePath.setEditable(false);
        deletePath.setColumns(10);

        //deleteText = new JTextField();
        deleteText.setColumns(10);

        //deletePathLabel = new JLabel("Files Path: ");
        //deleteTextLabel = new JLabel("Text to be deleted: ");

        //deleteSelectButton = new JButton("Select");
        deleteSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI.selectPath(deletePath);
            }
        });

        //deleteActionButton = new JButton("Delete");
        deleteActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //deleteProgress = new JProgressBar(0, 100);
                deleteProgress.setValue(0);
                deleteProgress.setStringPainted(true);

                // Delete process
                if (!"".equals(deletePath.getText()) && !"".equals(deleteText.getText())) {

                    File path = new File(deletePath.getText());

                    // TODO: Allow regular expressions characters

                    try {

                        int num = 0;
                        int size = path.listFiles().length;
                        int progress = new Double(100 / size).intValue();

                        for (File f : path.listFiles()) {
                            if (f.getName().contains(deleteText.getText())) {

                                f.renameTo(new File(deletePath.getText() + File.separator + f.getName().replace(deleteText.getText(), "")));

                                num += progress;

                                System.out.println(num+"% Percent Completed");

                                deleteProgress.setValue(num);

                            }
                        }


                        deleteProgress.setValue(100);

                        System.out.println("Successfully completed!!");

                    }catch (NullPointerException np){

                        //TODO: It Shows a warning message (folder is empty)

                        System.out.println("NullPointer Exception: Selected folder is empty");

                    }

                }else{

                    //TODO: It Shows a warning message (fields are empty)
                    System.out.println("Fields are empty");

                }

            }

        });

        GroupLayout glDeleteTab = new GroupLayout(deleteTab);
        glDeleteTab.setHorizontalGroup(
                glDeleteTab.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addGroup(glDeleteTab.createSequentialGroup()
                               .addGap(26)
                               .addGroup(glDeleteTab.createParallelGroup(GroupLayout.Alignment.LEADING)
                                       .addComponent(deleteTextLabel)
                                       .addGroup(glDeleteTab.createSequentialGroup()
                                               .addGroup(glDeleteTab.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                       .addComponent(deleteProgress, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                       .addComponent(deletePathLabel, GroupLayout.Alignment.LEADING)
                                                       .addComponent(deletePath, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                                       .addComponent(deleteText, GroupLayout.Alignment.LEADING))
                                               .addGap(18)
                                               .addGroup(glDeleteTab.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                       .addComponent(deleteSelectButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                       .addComponent(deleteActionButton, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))))
                               .addContainerGap(19, Short.MAX_VALUE))
        );

        glDeleteTab.setVerticalGroup(
                glDeleteTab.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(glDeleteTab.createSequentialGroup()
                                .addGap(32)
                                .addComponent(deletePathLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(glDeleteTab.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deletePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteSelectButton))
                                .addGap(18)
                                .addComponent(deleteTextLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(glDeleteTab.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteActionButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addComponent(deleteProgress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        deleteTab.setLayout(glDeleteTab);

        // - Insert Tab
        //insertTab = new JPanel();
        //mainTabs.addTab("Insert", null, insertTab, null);

        // - Count Tab
        //countTab = new JPanel();
        //mainTabs.addTab("Count", null, countTab, null);


        mainUI.getContentPane().setLayout(groupLayout);

    }

    /**
     * It performs select path button action
     */
    private static void selectPath(JTextField textField){

        // TODO: It must remember the last selected path

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Select Path");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION){
            System.out.println("getCurrentDirectory(): "
                    + fileChooser.getCurrentDirectory());
            System.out.println("getSelectedFile(): "
                    + fileChooser.getSelectedFile());

            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }else{
            System.out.println("No Selection");
        }

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
