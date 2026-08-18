package bugHunter;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BugHunterGUI extends JFrame {

    private BugManager bugManager;

    private JTextField bugNameField;
    private JTextArea diagnosisArea;

    private JTextField newBugNameField;
    private JTextArea newBugDiagnosisArea;

    private JButton fixSaveButton;

    private JPanel reportPanel;
    private JPanel databasePanel;
    private JPanel bugButtonsPanel;
    private JTextArea databaseArea;
    private JLabel bugCountLabel;

    private boolean editing;

    public BugHunterGUI() {

        bugManager = new BugManager();

        initializeGUI();

    }

    private void initializeGUI() {

        setTitle("Bug Hunter (Virtual Errors Dictionary)");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(createControlPanel(), BorderLayout.WEST);

        add(createCenterPanel(), BorderLayout.CENTER);

        add(createBottomPanel(), BorderLayout.SOUTH);

        setSize(900, 700);

        setLocationRelativeTo(null);

        setVisible(true);

    }
    
    private JPanel createControlPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton diagnoseButton = new JButton("Diagnose");

        fixSaveButton = new JButton("Fix / Save");

        JButton reportButton = new JButton("Report New Bug");

        JButton databaseButton = new JButton("Bug Database");

        JButton clearButton = new JButton("Clear");

        JButton developerButton = new JButton("Developer Info");
        
        diagnoseButton.addActionListener(e -> onDiagnose());

        fixSaveButton.addActionListener(e -> onFixSave());

        reportButton.addActionListener(e -> onReportNewBug());

        databaseButton.addActionListener(e -> onShowDatabase());

        clearButton.addActionListener(e -> onClear());

        developerButton.addActionListener(e -> onDeveloperInfo());

        panel.add(diagnoseButton);
        panel.add(fixSaveButton);
        panel.add(reportButton);
        panel.add(databaseButton);
        panel.add(clearButton);
        panel.add(developerButton);

        return panel;

    }
    
    private JPanel createCenterPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout(5, 5));

        JPanel bugNamePanel = new JPanel();

        bugNameField = new JTextField(20);

        bugNameField.setEditable(false);

        bugNamePanel.add(new JLabel("Bug Name:"));
        bugNamePanel.add(bugNameField);

        panel.add(bugNamePanel, BorderLayout.NORTH);

        bugButtonsPanel = createBugButtonsPanel();

        panel.add(bugButtonsPanel, BorderLayout.CENTER);

        JPanel diagnosisPanel = new JPanel(new BorderLayout());

        diagnosisArea = new JTextArea(8, 30);

        diagnosisArea.setEditable(false);

        diagnosisPanel.add(diagnosisArea, BorderLayout.CENTER);

        panel.add(diagnosisPanel, BorderLayout.SOUTH);

        return panel;

    }
    
    private JPanel createBugButtonsPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 3, 5, 5));

        for (Bug bug : bugManager.getAllBugs()) {

            JButton button = new JButton(bug.getName());

            button.addActionListener(e -> {

                bugNameField.setText(bug.getName());

                diagnosisArea.setText("");

            });

            panel.add(button);

        }

        return panel;

    }
    
    private JPanel createBottomPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        reportPanel = createReportPanel();

        reportPanel.setVisible(false);

        panel.add(reportPanel, BorderLayout.NORTH);

        databasePanel = createDatabasePanel();

        databasePanel.setVisible(false);

        panel.add(databasePanel, BorderLayout.CENTER);

        return panel;

    }
    
    private JPanel createReportPanel() {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        JPanel bugNamePanel = new JPanel();

        bugNamePanel.add(new JLabel("Bug Name"));

        newBugNameField = new JTextField(25);

        bugNamePanel.add(newBugNameField);

        JButton submitButton = new JButton("Submit Bug");

        submitButton.addActionListener(e -> onSubmitBug());

        topPanel.add(bugNamePanel, BorderLayout.CENTER);

        topPanel.add(submitButton, BorderLayout.EAST);


        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));

        JPanel diagnosisPanel = new JPanel();

        diagnosisPanel.add(new JLabel("Diagnosis / Fix"));

        newBugDiagnosisArea = new JTextArea(3, 25);

        diagnosisPanel.add(newBugDiagnosisArea);

        JButton discardButton = new JButton("Discard");

        discardButton.addActionListener(e -> onDiscardBug());

        bottomPanel.add(diagnosisPanel, BorderLayout.CENTER);

        bottomPanel.add(discardButton, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);

        panel.add(bottomPanel, BorderLayout.CENTER);

        return panel;

    }
    
    private JPanel createDatabasePanel() {

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new BorderLayout());

        bugCountLabel = new JLabel(
                "We have logged " + bugManager.getBugCount() + " bugs so far!"
        );

        JButton collapseButton = new JButton("Collapse View");

        collapseButton.addActionListener(e -> onCollapseDatabase());

        topPanel.add(bugCountLabel, BorderLayout.WEST);

        topPanel.add(collapseButton, BorderLayout.EAST);

        databaseArea = new JTextArea();

        databaseArea.setEditable(false);

        panel.add(topPanel, BorderLayout.NORTH);

        panel.add(databaseArea, BorderLayout.CENTER);

        updateDatabaseView();

        return panel;

    }
    
    private void updateDatabaseView() {

        String text = "";

        for (Bug bug : bugManager.getAllBugs()) {

            text += bug.getName() + "\n";

            text += bug.getDiagnosis() + "\n\n";

        }

        databaseArea.setText(text);

        bugCountLabel.setText(
                "We have logged " + bugManager.getBugCount() + " bugs so far!"
        );

    }
    
    private void onDiagnose() {

        try {

            String name = bugNameField.getText();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a bug first."
                );

                return;
            }

            Bug bug = bugManager.findByName(name);

            if (bug != null) {

                diagnosisArea.setText(bug.getDiagnosis());

            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while diagnosing the bug."
            );

        }

    }
    
    private void onFixSave() {

        try {

            if (bugNameField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a bug first."
                );

                return;
            }

            if (!editing) {

                diagnosisArea.setEditable(true);

                fixSaveButton.setText("Save");

                editing = true;

            } else {

                if (diagnosisArea.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Diagnosis cannot be empty."
                    );

                    return;
                }

                bugManager.updateDiagnosis(
                        bugNameField.getText(),
                        diagnosisArea.getText()
                );

                diagnosisArea.setEditable(false);

                fixSaveButton.setText("Fix / Save");

                editing = false;
            }

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while saving the diagnosis."
            );

        }

    }
    
    private void onClear() {

        bugNameField.setText("");

        diagnosisArea.setText("");

        diagnosisArea.setEditable(false);

        fixSaveButton.setText("Fix / Save");

        editing = false;

    }
    
    private void onReportNewBug() {

        reportPanel.setVisible(!reportPanel.isVisible());

        databasePanel.setVisible(false);

    }
    
    private void onSubmitBug() {

        try {

            String name = newBugNameField.getText();

            String diagnosis = newBugDiagnosisArea.getText();

            if (name.isEmpty() || diagnosis.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields."
                );

                return;
            }

            if (bugManager.findByName(name) != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "This bug already exists."
                );

                return;
            }

            Bug bug = new Bug(name, diagnosis);

            bugManager.addBug(bug);

            updateDatabaseView();

            JButton button = new JButton(name);

            button.addActionListener(e -> {

                bugNameField.setText(name);

                diagnosisArea.setText("");

            });

            bugButtonsPanel.add(button);

            bugButtonsPanel.revalidate();

            bugButtonsPanel.repaint();

            newBugNameField.setText("");

            newBugDiagnosisArea.setText("");

            reportPanel.setVisible(false);

        } catch (RuntimeException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while adding the bug."
            );

        }

    }
    
    private void onDiscardBug() {

        newBugNameField.setText("");

        newBugDiagnosisArea.setText("");

        reportPanel.setVisible(false);

    }
    
    private void onDeveloperInfo() {

        JOptionPane.showMessageDialog(
                this,
                "Name: Mohammed Jabr\n"
                + "Student ID: 12427886\n"
                + "Major: Computer Engineering",
                "Developer Information",
                JOptionPane.INFORMATION_MESSAGE
        );

    }
    
    private void onShowDatabase() {
    	updateDatabaseView();
    	
        databasePanel.setVisible(true);

        reportPanel.setVisible(false);

    }
    
    private void onCollapseDatabase() {

        databasePanel.setVisible(false);

    }

}