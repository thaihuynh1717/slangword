import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App extends JFrame {
    private JPanel appPanel;
    private JButton btnSearchSlang;
    private JButton btnSearchDefinition;
    private JButton btnShowHistory;
    private JButton btnAddOne;
    private JButton btnEditOne;
    private JButton a6XóaMộtSlangButton;
    private JButton a7KhôiPhụcDanhButton;
    private JButton a8ChọnNgẫuNhiênButton;
    private JButton a9ĐốVuiSlangButton;
    private JButton a10ĐốVuiSlangButton;
    private ArrayList<String> slangWord = new ArrayList<String>();
    private ArrayList<ArrayList<String>> definition = new ArrayList<ArrayList<String>>();

    public App() {
        btnSearchSlang.setSize(10, 100);
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
                        if (result.length == 2) {
                            slangWord.add(result[0]);
                            ArrayList<String> temp = new ArrayList<>();
                            Arrays.stream(result[1].split("\\|")).forEach(word -> {
                                if (Arrays.asList(result).indexOf(word) != 0) {
                                    temp.add(word);
                                }
                            });
                            definition.add(temp);
                            System.out.println(slangWord.get(slangWord.size() - 1) + "-->" + definition.get(definition.size() - 1));
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
