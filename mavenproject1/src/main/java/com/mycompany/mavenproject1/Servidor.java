/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.servidor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gabriela.biasi
 */
public class Servidor {

    private static final int imageWidth = 1920 / 4;
    private static final int imageHeight = 1080 / 3;
    private static int portaInicial = 5000;

    public static void main(String[] args) {

        BufferedImage buffered = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        JFrame janela = new JFrame();
        janela.setSize(1920, 1080);
        janela.setVisible(true);
        JLabel label = new JLabel();

        int acumuladorPortas = 0;
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < 1; x++) {
                int senderPort = portaInicial + acumuladorPortas;
                Socket imgs;
                imgs = new Socket(
                        senderPort,
                        buffered,
                        imageWidth * x,
                        imageHeight * y,
                        label
                );
                imgs.start();
                acumuladorPortas++;
            }
        }
    }
}
