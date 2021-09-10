package program;

import program.compilerAppGui;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rute = "C:/Users/mared/OneDrive/Escritorio/Aplicacion Notas/compilerPr/src/program/Lexer.flex";
        JFrame frame = new compilerAppGui("Analizador lexico");
        frame.setSize(500, 500);
        frame.setVisible(true);
        LexerGenerator(rute);

    }
    public static void LexerGenerator(String rute){
        File f;
        f = new File(rute);
        jflex.Main.generate(f);

    }
}
