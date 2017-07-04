package com.mifmif.gefmmat.testbed.student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mifmif.gefmmat.core.AgentExperience;
import com.mifmif.gefmmat.core.Task;

public class MainViewer extends JPanel {

	private JTextField txtHonest = new JTextField("1");
	private JTextField txtCamoflage = new JTextField("1");
	private JTextField txtConstantDishonest = new JTextField("1");
	private JTextField txtRandom = new JTextField("1");
	private JTextField txtWhiteWashing = new JTextField("1");
	private JTextField txtTaskGenerator = new JTextField("4");
	private JTextField txtNbrTask = new JTextField("500");

	private JLabel labelHonest = new JLabel("Honest Agents");
	private JLabel labelCamoflage = new JLabel("Camoflage Agents");
	private JLabel labelConstantDishonest = new JLabel("Constant Dishonest Agents");
	private JLabel labelRandom = new JLabel("Honest Agents");
	private JLabel labelWhiteWashing = new JLabel("White washing Agents");
	private JLabel labelTaskGenerator = new JLabel("Task Generator Agents");
	private JLabel labelNbrTasks = new JLabel("Numbers of Tasks");

	private int honestStudentNumber = 5;
	private int camouflageStudentNumber = 5;
	private int randomStudentNumber = 5;
	private int constantDishonestStudentNumber = 5;
	private int whitewashingStudentNumber = 5;
	private int taskGeneratorStudentNumber = 1;
	private int nbrTasks = 500;
	protected boolean viewInteractionPanelInitiated;

	// Create a form with the fields
	public MainViewer() {
		super(new BorderLayout());
		// Panel for the labels
		JPanel labelPanel = new JPanel(new GridLayout(8, 1)); // 2 rows 1 column
		add(labelPanel, BorderLayout.WEST);

		// Panel for the fields
		JPanel fieldPanel = new JPanel(new GridLayout(8, 1)); // 2 rows 1 column
		add(fieldPanel, BorderLayout.CENTER);
		final JPanel chartCommandPanel = new JPanel(new GridLayout(3, 3)); // 2
																			// rows
																			// 1
																			// column
		add(chartCommandPanel, BorderLayout.EAST);
		chartCommandPanel.setVisible(false);
		// Combobox
		// JLabel labelCombo = new JLabel("Bank Code");

		// Options in the combobox

		labelPanel.setSize(640, 480);

		labelPanel.add(labelHonest);
		labelPanel.add(labelCamoflage);
		labelPanel.add(labelConstantDishonest);
		labelPanel.add(labelRandom);
		labelPanel.add(labelWhiteWashing);
		labelPanel.add(labelTaskGenerator);
		labelPanel.add(labelNbrTasks);
		// Add labels
		// labelPanel.add(labelCombo);
		// labelPanel.add(labelTextField);

		fieldPanel.add(txtHonest);
		fieldPanel.add(txtCamoflage);
		fieldPanel.add(txtConstantDishonest);
		fieldPanel.add(txtRandom);
		fieldPanel.add(txtWhiteWashing);
		fieldPanel.add(txtTaskGenerator);
		fieldPanel.add(txtNbrTask);
		fieldPanel.setSize(640, 480);

		// Add fields
		// fieldPanel.add(comboBox);
		// fieldPanel.add(textField);

		// Frame for our test
		JFrame f = new JFrame("Computational Trust Model Testbed");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(this, BorderLayout.NORTH);
		f.setSize(640, 480);
		f.validate();
		// Button submit
		JButton submit = new JButton("Start a Test Case");
		submit.addActionListener(new ActionListener() {
			// @Override
			public void actionPerformed(ActionEvent e) {
				honestStudentNumber = Integer.valueOf(txtHonest.getText());
				camouflageStudentNumber = Integer.valueOf(txtCamoflage.getText());
				constantDishonestStudentNumber = Integer.valueOf(txtConstantDishonest.getText());
				randomStudentNumber = Integer.valueOf(txtRandom.getText());
				whitewashingStudentNumber = Integer.valueOf(txtWhiteWashing.getText());
				taskGeneratorStudentNumber = Integer.valueOf(txtTaskGenerator.getText());
				nbrTasks = Integer.valueOf(txtNbrTask.getText());
				MainContainer container = new MainContainer();
				container.setHonestStudentNumber(honestStudentNumber);
				container.setCamouflageStudentNumber(camouflageStudentNumber);
				container.setConstantDishonestStudentNumber(constantDishonestStudentNumber);
				container.setRandomStudentNumber(randomStudentNumber);
				container.setWhitewashingStudentNumber(whitewashingStudentNumber);
				container.setTaskGeneratorStudentNumber(taskGeneratorStudentNumber);
				container.setNumberOfTasks(nbrTasks);
				container.startTestCase();
				if (!viewInteractionPanelInitiated) {
					prepareInteractionPanel(chartCommandPanel);
				}
			}

		});

		// Panel with the button
		JPanel p = new JPanel();
		p.add(submit);
		f.getContentPane().add(p, BorderLayout.SOUTH);
		Dimension d = new Dimension(640, 400);
		f.setPreferredSize(d);
		f.pack();
		// Show the frame
		f.pack();
		f.setVisible(true);

	}

	private void prepareInteractionPanel(final JPanel chartCommandPanel) {
		viewInteractionPanelInitiated = true;
		String[] listTrustMetricsNames = getTrustMetricNames();
		final JComboBox comboBox = new JComboBox(listTrustMetricsNames);
		JButton btnViewInteraction = new JButton("View Interaction's Chart");
		final JButton btnPauseResume = new JButton("Pause");
		chartCommandPanel.add(comboBox);
		chartCommandPanel.add(btnViewInteraction);
//		chartCommandPanel.add(btnPauseResume);
		chartCommandPanel.setVisible(true);
		btnViewInteraction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String trustMetricName = ((String) comboBox.getSelectedItem());
				viewInteractionChart(trustMetricName);

			}
		});

		btnPauseResume.addActionListener(new ActionListener() {

			private boolean isPaused;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPaused) {
					isPaused = true;
					btnPauseResume.setText("Resume");

					for (Student student : StudentStatusRegister.getStudents()) {
						if (student.hasTaskGeneratorBahaviour()) {
							student.getTaskGenerator().pause();
						}
					}

				} else {
					isPaused = false;

					for (Student student : StudentStatusRegister.getStudents()) {
						if (student.hasTaskGeneratorBahaviour()) {
							student.getTaskGenerator().resume();
						}
					}
					btnPauseResume.setText("Pause");

				}

			}
		});
	}

	private void viewInteractionChart(String trustMetricName) {
		List<Student> students = StudentStatusRegister.getStudents();

		Student taskGeneratorsStudent = null;
		System.out.println("Available students : ");
		for (Student student : StudentStatusRegister.getStudents()) {
			if (student.hasTaskGeneratorBahaviour() && student.getAID().getName().contains(trustMetricName)) {
				taskGeneratorsStudent = (student);
				break;
			}
		}

		List<AgentExperience> agentExperiences = taskGeneratorsStudent.getAgentExperiences();
		int numberOfTasks = taskGeneratorsStudent.getNumberOfTasks();
		Map<String, Map<Double, Double>> result = new HashMap<String, Map<Double, Double>>();
		for (AgentExperience exp : agentExperiences) {
			String agentName = exp.getTrusteeAgent().getName();
			String agentType = agentName.split("_")[0];
			Map<Double, Double> mapAgent = result.get(agentType);
			if (mapAgent == null) {
				mapAgent = new TreeMap<>();
				result.put(agentType, mapAgent);
			}
			List<Task> processedTasks = exp.getProcessedTasks();

			double counter = 0;
			int lastTaskOrder = 1;
			int oldValue = 0;
			double pointerTasks = 1;
			for (Task t : processedTasks) {
				double taskOrder = t.getTaskOrder();
				while (pointerTasks < taskOrder) {
					mapAgent.put(pointerTasks, counter);
					pointerTasks++;
				}
				counter++;
				mapAgent.put(taskOrder, counter);
				lastTaskOrder = t.getTaskOrder();
			}

			for (double i = pointerTasks + 1; i <= numberOfTasks; ++i) {
				mapAgent.put(i, counter);
			}
		}
		ChartViewer chartViewer = new ChartViewer();

		chartViewer.start(trustMetricName, result);
	}

	public String[] getTrustMetricNames() {
		java.util.Set<String> metricsNames = new TreeSet<String>();

		for (Student student : StudentStatusRegister.getStudents()) {
			metricsNames.add(student.getTrustMetric().getTrustMetricName());
		}

		return metricsNames.toArray(new String[0]);

	}

	public static void main(String[] args) {
		final MainViewer form = new MainViewer();

	}

	private static void createIban(String selectedItem, String text) {
		// Do stuff with your data
		System.out.println("Im in createIban with the values: " + selectedItem + " and " + text);
	}
}
