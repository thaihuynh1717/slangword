import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App extends JFrame {
    private JPanel appPanel;
    private JButton btnSearchSlang;

    public App() {
        btnSearchSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String currentPath = new java.io.File(".").getCanonicalPath();
                    System.out.println(currentPath);
                    File file = new File(currentPath + "/src/slang.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = new String();
                    while ((line = reader.readLine()) != null) {
                        String[] result = line.split("`");
//                        System.out.println(result);

                        if (result.length == 2) {
                            String slangWord = result[0];
                            ArrayList<String> definition = new ArrayList<String>();
                            Arrays.stream(result[1].split("\\|")).forEach(word -> {
                                if (Arrays.asList(result).indexOf(word) != 0) {
                                    definition.add(word);

                                }
                            });
                            System.out.println(slangWord + "-->" + definition);
                        }
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.setContentPane(app.appPanel);
        app.setTitle("Slang word application");
        app.setSize(800, 450);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
