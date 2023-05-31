package com.mycompany.mavenproject1;

import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mavenproject1 {

    private static final int imageWidth = 1920 / 4;
    private static final int imageHeight = 1080 / 3;

    public static void main(String[] args) {
        String ip = "10.3.54.28";
        int portaInicial = 5000;

        int acumuladorPortas = 0;
        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < 1; x++) {
                int senderPort = portaInicial + acumuladorPortas;
                ImageSender imgs;
                try {
                    imgs = new ImageSender(
                            ip,
                            senderPort,
                            imageWidth * y,
                            imageHeight * x
                    );
                    imgs.start();
                } catch (IOException | AWTException ex) {
                    Logger.getLogger(Mavenproject1.class.getName()).log(Level.SEVERE, null, ex);
                }
                acumuladorPortas++;
            }
        }
    }
}
