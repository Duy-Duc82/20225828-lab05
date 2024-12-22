package hust.soict.dsai.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0; // Tổng tích lũy

    public AWTAccumulator() {
        // Thiết lập giao diện
        setLayout(new GridLayout(2, 2));

       
        add(new Label("Enter an integer: "));
        tfInput = new TextField(10);
        add(tfInput);
        tfInput.addActionListener(new TFInputListener());

        // Ô kết quả
        add(new Label("The accumulated sum is: "));
        tfOutput = new TextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);

        // Cấu hình Frame
        setTitle("AWT Accumulator");
        setSize(350, 150);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                int numberIn = Integer.parseInt(tfInput.getText());
                sum += numberIn; // Cộng dồn tổng
                tfOutput.setText(sum + ""); // Hiển thị kết quả
                tfInput.setText(""); // Xóa ô nhập
            } catch (NumberFormatException e) {
                tfInput.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }
}
