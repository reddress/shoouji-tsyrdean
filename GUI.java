import javax.swing.*;

// javac -encoding "UTF-8" MyFrame.java

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SJ");
        JLabel label = null;
        for (int i = 0; i < 50000; i++) {
            label = new JLabel(Cedict.entry(i), JLabel.CENTER);
        }
        
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
