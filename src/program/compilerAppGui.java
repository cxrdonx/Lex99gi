package program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class compilerAppGui extends JFrame {
    private JPanel mainPanel;
    private JButton accpetButton;
    private JTextPane countTxt;
    private JTextArea codigoTxt;
    private JTextPane resultTxt;

    public compilerAppGui(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        accpetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File("file.txt");
                 //get the text to analizer
                int spaceCount = 0;
                String input = codigoTxt.getText();
                char[] chain = input.toCharArray();
                PrintWriter write;
                for (int i = 0; i < chain.length; i++) {
                    if (Character.isSpaceChar(chain[i])) {
                        spaceCount++;
                    }
                }
                countTxt.setText("Espacios en blanco: "+spaceCount);

                char letter;
                ArrayList<String> prueba = new ArrayList<>();

                char[] repeatedLetter = new char[chain.length];
                int[] repetitions = new int[chain.length];
                repeatedLetter = input.toCharArray();
                for (int i = 0; i < chain.length; i++) {
                    letter = chain[i];
                    repeatedLetter[i] = letter;
                    for (int j = i; j < chain.length; j++) {
                        if (chain[j] == letter) {
                            repetitions[i]++;
                            chain[j] = ' ';
                        }
                    }
                    if (repeatedLetter[i] != ' ') {
                        System.out.println (repeatedLetter[i] + " = " + repetitions[i]);
                    }
                }

                try {
                    write = new PrintWriter(f);
                    write.print(codigoTxt.getText());
                    write.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(compilerAppGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Reader reader = new BufferedReader(new FileReader("file.txt"));
                    Lexer lexer = new Lexer(reader);
                    int counter = 0;
                    String result = "" ;
                    while (true){
                        Tokens tokens = lexer.yylex();
                        if(tokens == null){
                            result += "END";
                            resultTxt.setText(result);
                            return;

                        }
                        switch (tokens){
                            case Reservadas: result+= lexer.lexeme+"<<"+tokens+">>\n";
                                break;
                            case Igual: result+= lexer.lexeme+"<<"+tokens+">>\n";
                                break;
                            case Suma: result+= lexer.lexeme+"<<"+tokens+">>\n";
                                break;
                            case Resta: result+= lexer.lexeme+"<<"+tokens+">>\n";
                                break;
                            case Constante: result+= lexer.lexeme+ "<<"+tokens+">>\n";
                                break;
                            case Numero: result+= lexer.lexeme+"<<"+tokens+">>\n";
                                break;
                            case ERROR: result += "no definido\n";
                            break;

                        }
                    }
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });

    }

}
