package hust.soict.dsai.swing;
public class AWTAccumulator extends Frame {
    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    // Constructor to setup the GUI components and event handlers
    public AWTAccumulator() {
        setLayout(new GridLayout(2, 2));
        add(new Label("Enter a number: "));
        tfInput = new TextField(10);
        add(tfInput);
        tfInput.addActionListener(new TFInputListener());
        add(new Label("The Accumulated Sum is: "));
        tfOutput = new TextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);
        setTitle("AWT Accumulator");
        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }

    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sum += Integer.parseInt(tfInput.getText());
            tfOutput.setText(sum + "");
        }
    }
}