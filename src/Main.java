import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Ecowash");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Register registerForm = new Register();
                frame.setContentPane(registerForm.getMainPanel());

                frame.pack();
                frame.setSize(1000, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}