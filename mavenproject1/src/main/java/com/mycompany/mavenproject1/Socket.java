/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidor;

import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gabriela.biasi
 */
public class Socket extends Thread {

    public int porta;
    public BufferedImage image;
    public int imageWidth;
    public int imageHeight;
    public JLabel jlabel;

    public Socket(int porta, BufferedImage image, int imageWidth, int imageHeight, JLabel jlabel) {
        this.porta = porta;
        this.image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.jlabel = jlabel;
    }

    @Override
    public void run() {

        try {

            ServerSocket servidor = new ServerSocket(porta);
            java.net.Socket cliente = servidor.accept();
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

            while (true) {
                ImageIcon resposta = null;
                try {
                    resposta = (ImageIcon) entrada.readObject();
                } catch (Exception ee) {
                    continue;
                }

                if (resposta == null) {
                    continue;
                }

                System.out.println("CHEGO");

                image.getGraphics().drawImage(resposta.getImage(), imageWidth, imageHeight, null);
                jlabel.setIcon(new ImageIcon(image));

                System.out.println("IMPRIMIU");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Acabooo");
    }

}
