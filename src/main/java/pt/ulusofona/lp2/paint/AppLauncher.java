package pt.ulusofona.lp2.paint;

import pt.ulusofona.lp2.paint.provided.Screen;

import javax.swing.*;
import java.awt.*;

public class AppLauncher {

    static final int WIDTH = 800;  // altura da área pintável
    static final int HEIGHT = 600;  // largura da área pintável

    static int x, y;  // coordenadas do Ponto

    static void createAndShowGUI() {

        // inicializa a janela do Paint
        JFrame frame = new JFrame("Paint (Episódio 1)");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());

        // inicializa a área "pintável" do Paint
        Screen screen = new Screen(WIDTH, HEIGHT);
        screen.reset();
        frame.getContentPane().add(screen, BorderLayout.CENTER);

        // inicializa a barra de ferramentas
        JToolBar toolBar = criaBarraFerramentas(frame);
        JButton moveEsquerdaBtn = new JButton(getIcon("icon_left.png"));
        toolBar.add(moveEsquerdaBtn);
        JButton moveDireitaBtn = new JButton(getIcon("icon_right.png"));
        toolBar.add(moveDireitaBtn);
        JButton moveBaixoBtn = new JButton(getIcon("icon_down.png"));
        toolBar.add(moveBaixoBtn);
        JButton moveCimaBtn = new JButton(getIcon("icon_up.png"));
        toolBar.add(moveCimaBtn);

        // mostra no écran o Paint
        frame.pack();
        frame.setVisible(true);

        // obtém as coordenadas iniciais do início da linha, de forma a ficarem no meio do écran
        x = WIDTH / 2;
        y = HEIGHT / 2;

        // desenha o pixel nessa posição usando a classe auxiliar Screen
        screen.drawPixel(x, y, Color.RED);

        // define o que acontece quando se carrega no botão "moveEsquerdaBtn"
        moveEsquerdaBtn.addActionListener(event -> {
            if (x > 0) {
                x -= 1;
                screen.drawPixel(x, y, Color.RED);
            }
        });

        // define o que acontece quando se carrega no botão "moveDireitaBtn"
        moveDireitaBtn.addActionListener(event -> {
            if (x < WIDTH) {
                x += 5;
                screen.drawPixel(x, y, Color.RED);
            }
        });

        // define o que acontece quando se carrega no botão "moveBaixoBtn"
        moveBaixoBtn.addActionListener(event -> {
            if (y < HEIGHT) {
                y += 1;
                screen.drawPixel(x, y, Color.GREEN);
            }
        });

        // define o que acontece quando se carrega no botão "moveCimaBtn"
        moveCimaBtn.addActionListener(event -> {
            if (y > 0) {
                y -= 1;
                screen.drawPixel(x, y, Color.GREEN);
            }
        });
    }

    static JToolBar criaBarraFerramentas(JFrame frame) {
        JToolBar toolBar = new JToolBar();
        frame.getContentPane().add(toolBar, BorderLayout.WEST);
        toolBar.setLayout(new GridLayout(10, 1, 0, 5));
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(true);
        toolBar.setMargin(new Insets(10, 5, 10, 5));
        return toolBar;
    }

    static ImageIcon getIcon(String iconName) {
        return new ImageIcon(AppLauncher.class.getResource("/" + iconName));
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
