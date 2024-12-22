package hust.soict.dsai.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingAccumulator extends JFrame {
    private JTextField tfInput;
    private JTextField tfOutput;
    private int sum = 0; // Tổng tích lũy ban đầu là 0

    public SwingAccumulator() {
       
        setTitle("Swing Accumulator");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));

        // Tạo giao diện nhập liệu
        add(new JLabel("Enter an integer: "));
        tfInput = new JTextField(10);
        add(tfInput);

        add(new JLabel("The accumulated sum is: "));
        tfOutput = new JTextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);

        // Gắn ActionListener
        tfInput.addActionListener(new TFInputListener());

        setVisible(true);
    }

    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int number = Integer.parseInt(tfInput.getText());
                sum += number; // Cộng dồn tổng
                tfOutput.setText(sum + ""); // Cập nhật kết quả
                tfInput.setText(""); // Xóa ô nhập liệu
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer!", 
                                              "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingAccumulator());
    }
}
