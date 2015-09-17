import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by Rafvall on 27/8/2015.
 */
public class MainUI {

    // Constants
    final int NOTCHECKED = 0;

    // - Insert Options
    final int BEGINNING  = 1;
    final int END = 2;
    final int FROM = 3;

    // - Count Options
    final int POINT = 1;
    final int HYPHEN = 2;
    final int SPACE = 3;

    // Containers
    private JFrame appUI;
    private JPanel mainUI;

    // Tabs
    private JTabbedPane mainTabs;
    private JPanel deleteTab;
    private JPanel insertTab;
    private JPanel countTab;

    // Text fields
    private JTextField deletePath;
    private JTextField deleteText;
    private JProgressBar deleteProgress;

    private JTextField insertPath;
    private JTextField insertText;
    private JProgressBar insertProgress;
    private JSpinner insertColumn;

    private JTextField countPath;
    private JProgressBar countProgress;

    // Labels
    private JLabel deletePathLabel;
    private JLabel deleteTextLabel;
    private JLabel insertPathLabel;
    private JLabel insertTextLabel;
    private JLabel countPathLabel;
    private JLabel countTypesLabel;

    // Buttons
    private JButton deleteSelectButton;
    private JButton deleteActionButton;

    private JButton insertSelectButton;
    private JButton insertActionButton;

    private JButton countSelectButton;
    private JButton countActionButton;

    // Radio Buttons
    private JRadioButton insertBeginning;
    private JRadioButton insertEnd;
    private JRadioButton insertFrom;

    private JRadioButton countType1;
    private JRadioButton countType2;
    private JRadioButton countType3;

    // Useful vars
    private int insertOption = NOTCHECKED;
    private int countOption = NOTCHECKED;


    /**
     * Build UI
     */
    public MainUI() {

        // Main UI Container
        appUI = new JFrame();
        appUI.setTitle("Names Converter 1.2");
        appUI.setBounds(100, 100, 640, 350);
        appUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appUI.add(mainUI);

        // Build menu bar
        JMenuBar menuBar = new JMenuBar();
        appUI.setJMenuBar(menuBar);

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

        // -- Option Items
        JMenuItem itemAbout = new JMenuItem("About...");
        itemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(appUI, "Eggs are not supposed to be green.");
            }
        });

        mHelp.add(itemAbout);

        // Tabs
        // - Delete Tab
        deleteSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI.selectPath(deletePath);
            }
        });

        deleteActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                deleteProgress.setValue(0);
                deleteProgress.setStringPainted(true);

                // Delete process
                if (!"".equals(deletePath.getText()) && !"".equals(deleteText.getText())) {

                    File path = new File(deletePath.getText());

                    // TODO: Allow regular expressions characters

                    int num = 0;
                    int size = path.listFiles().length;
                    int progress = new Double(100 / size).intValue();

                    for (File f : path.listFiles()) {
                        if (f.getName().contains(deleteText.getText())) {

                            f.renameTo(new File(deletePath.getText() + File.separator + f.getName().replace(deleteText.getText(), "")));

                            num += progress;

                            System.out.println(num + "% Percent Completed");

                            deleteProgress.setValue(num);

                        }
                    }


                    deleteProgress.setValue(100);

                    System.out.println("Successfully completed!!");


                } else {

                    System.out.println("Fields are empty");
                    JOptionPane.showMessageDialog(appUI, "Fields cannot be blank");

                }

            }

        });

        // - Insert Tab
        insertBeginning.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                insertEnd.setSelected(false);
                insertFrom.setSelected(false);
                insertOption = BEGINNING;
                insertColumn.setEnabled(false);
            }
        });

        insertEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertBeginning.setSelected(false);
                insertFrom.setSelected(false);
                insertOption = END;
                insertColumn.setEnabled(false);
            }
        });

        insertFrom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertBeginning.setSelected(false);
                insertEnd.setSelected(false);
                insertOption = FROM;
                insertColumn.setEnabled(true);
            }
        });

        insertSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI.selectPath(insertPath);
            }
        });

        insertActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                insertProgress.setValue(0);
                insertProgress.setStringPainted(true);

                if (!"".equals(insertPath.getText()) && !"".equals(insertText.getText())) {

                    File directory = new File(insertPath.getText());

                    int num = 0;
                    int size = directory.listFiles().length;
                    int progress = new Double(100 / size).intValue();

                    switch (insertOption) {

                        case NOTCHECKED:
                            System.out.println("No option selected");
                            JOptionPane.showMessageDialog(appUI, "Please select an option");

                            break;

                        case BEGINNING:

                            for (File f : directory.listFiles()) {

                                String name = f.getName();
                                f.renameTo(new File(insertPath.getText() + File.separator + f.getName().replace(name, insertText.getText() + name)));
                                num += progress;
                                insertProgress.setValue(num);

                            }

                            insertProgress.setValue(100);
                            System.out.println("Successfully completed!!");

                            break;

                        case END:

                            for (File f : directory.listFiles()) {

                                String name = f.getName().substring(0, f.getName().lastIndexOf("."));
                                f.renameTo(new File(insertPath.getText() + File.separator + f.getName().replace(name, name + insertText.getText())));
                                num += progress;
                                insertProgress.setValue(num);

                            }

                            insertProgress.setValue(100);
                            System.out.println("Successfully completed!!");

                            break;

                        case FROM:

                            for (File f : directory.listFiles()) {

                                if ((Integer) insertColumn.getValue() > f.getName().length()) {

                                    insertColumn.setValue(f.getName().length());

                                }

                                String name1 = f.getName().substring(0, (Integer) insertColumn.getValue());
                                String name2 = f.getName().substring((Integer) insertColumn.getValue());
                                String completeName = f.getName();

                                f.renameTo(new File(insertPath.getText() + File.separator + f.getName().replace(completeName, name1 + insertText.getText() + name2)));
                                num = +progress;
                                insertProgress.setValue(num);

                            }

                            insertProgress.setValue(100);
                            System.out.println("Successfully completed!!");

                            break;
                    }

                } else {

                    System.out.println("Fields are empty");
                    JOptionPane.showMessageDialog(appUI, "Fields cannot be blank");

                }

            }
        });

        // - Count Tab
        countType1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                countType2.setSelected(false);
                countType3.setSelected(false);
                countOption = POINT; // NN. (01. 02. 03. ...)
                System.out.println("Option POINT selected");

            }
        });

        countType2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countType1.setSelected(false);
                countType3.setSelected(false);
                countOption = HYPHEN; // NN- (01- 02- 03- ...)
                System.out.println("Option HYPHEN selected");

            }
        });

        countType3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countType1.setSelected(false);
                countType2.setSelected(false);
                countOption = SPACE; // NN (01 02 03 ...)
                System.out.println("Option SPACE selected");

            }
        });

        countSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainUI.selectPath(countPath);
            }
        });

        countActionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                countProgress.setValue(0);
                countProgress.setStringPainted(true);

                if (!"".equals(countPath.getText())) {

                    File directory = new File(countPath.getText());

                    int num = 0;
                    int size = directory.listFiles().length;
                    int progress = new Double(100 / size).intValue();

                    int count = 1;
                    String countText;
                    String separator = "";

                    switch (countOption) {

                        case NOTCHECKED:
                            System.out.println("No option selected");
                            JOptionPane.showMessageDialog(appUI, "Please select an option");

                            break;

                        case POINT:
                            System.out.println("Option POINT used");
                            separator = ".";

                            break;

                        case HYPHEN:
                            System.out.println("Option HYPHEN used");
                            separator = "-";

                            break;

                        case SPACE:
                            System.out.println("Option SPACE used");
                            separator = " ";

                            break;

                    }

                    // If there is an option selected
                    if(countOption != NOTCHECKED) {
                        for (File f : directory.listFiles()) {

                            if (count < 9) {
                                countText = "0" + count + separator;
                            } else {
                                countText = count + separator;
                            }

                            String name = f.getName();
                            f.renameTo(new File(countPath.getText() + File.separator + f.getName().replace(name, countText + name)));
                            num += progress;
                            countProgress.setValue(num);

                            count++;

                        }

                        countProgress.setValue(100);
                        System.out.println("Successfully completed!! " + (count - 1) + " Files.");

                    }

                } else {

                    System.out.println("Fields are empty");
                    JOptionPane.showMessageDialog(appUI, "Fields cannot be blank");

                }

            }
        });

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
     * It returns App UI
     * @return JFrame
     */
    public JFrame getAppUI(){
        return appUI;
    }

}
