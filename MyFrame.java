import javax.swing.*;

// javac -encoding "UTF-8" MyFrame.java

public class MyFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SJ");
        JLabel label = new JLabel(Cedict.entry(100), JLabel.CENTER);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
