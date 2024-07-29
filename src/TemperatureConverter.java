import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TemperatureConverter extends JFrame {
    JTextField tempInput, tempOutput;
    JLabel inputLabel, outputLabel;
    JComboBox<String> comboBox1, comboBox2;

    public TemperatureConverter() {
        // Set up the frame
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null);

        // Set custom fonts and colors
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color backgroundColor = new Color(245, 245, 245);
        Color buttonColor = new Color(100, 149, 237);
        Color buttonTextColor = Color.WHITE;

        // Create labels
        inputLabel = new JLabel("Input Temperature:");
        inputLabel.setBounds(20, 20, 150, 25);
        inputLabel.setFont(labelFont);
        add(inputLabel);

        outputLabel = new JLabel("Output Temperature:");
        outputLabel.setBounds(20, 100, 150, 25);
        outputLabel.setFont(labelFont);
        add(outputLabel);

        // Create text fields
        tempInput = new JTextField();
        tempInput.setBounds(160, 20, 200, 25);
        tempInput.setFont(fieldFont);
        add(tempInput);

        tempOutput = new JTextField();
        tempOutput.setBounds(160, 100, 200, 25);
        tempOutput.setFont(fieldFont);
        tempOutput.setEditable(false); // Make output field read-only
        add(tempOutput);

        // Create combo boxes for temperature scales
        String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};
        comboBox1 = new JComboBox<>(scales);
        comboBox1.setBounds(20, 60, 150, 25);
        comboBox1.setFont(fieldFont);
        add(comboBox1);

        comboBox2 = new JComboBox<>(scales);
        comboBox2.setBounds(200, 60, 150, 25);
        comboBox2.setFont(fieldFont);
        add(comboBox2);

        // Create the convert button
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 140, 100, 30);
        convertButton.setFont(labelFont);
        convertButton.setBackground(buttonColor);
        convertButton.setForeground(buttonTextColor);
        convertButton.setOpaque(true);
        convertButton.setBorderPainted(false);
        add(convertButton);

        // Add action listener to the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConvertTemperature();
            }
        });

        // Set the background color
        getContentPane().setBackground(backgroundColor);
    }

    // Method to convert temperature
    public void ConvertTemperature() {
        try {
            double inputTemp = Double.parseDouble(tempInput.getText());
            String fromScale = (String) comboBox1.getSelectedItem();
            String toScale = (String) comboBox2.getSelectedItem();
            double outputTemp = 0;

            // Conversion logic
            if (fromScale.equals("Celsius")) {
                if (toScale.equals("Fahrenheit")) {
                    outputTemp = (inputTemp * 9/5) + 32;
                } else if (toScale.equals("Kelvin")) {
                    outputTemp = inputTemp + 273.15;
                } else {
                    outputTemp = inputTemp; // Celsius to Celsius
                }
            } else if (fromScale.equals("Fahrenheit")) {
                if (toScale.equals("Celsius")) {
                    outputTemp = (inputTemp - 32) * 5/9;
                } else if (toScale.equals("Kelvin")) {
                    outputTemp = (inputTemp - 32) * 5/9 + 273.15;
                } else {
                    outputTemp = inputTemp; // Fahrenheit to Fahrenheit
                }
            } else if (fromScale.equals("Kelvin")) {
                if (toScale.equals("Celsius")) {
                    outputTemp = inputTemp - 273.15;
                } else if (toScale.equals("Fahrenheit")) {
                    outputTemp = (inputTemp - 273.15) * 9/5 + 32;
                } else {
                    outputTemp = inputTemp; // Kelvin to Kelvin
                }
            }

            // Set the output temperature in the output field
            tempOutput.setText(String.format("%.2f", outputTemp));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}