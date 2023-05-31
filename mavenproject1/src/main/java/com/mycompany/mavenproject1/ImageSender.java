/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ImageSender extends Thread {

    private final Socket cliente;
    private final ObjectOutputStream saida;
    private final Robot robot;
    private final Rectangle screenRectangle = new Rectangle(new Dimension(1920, 1080));
    private final int offsetX;
    private final int offsetY;

    private final int imageWidth = 1920 / 4;
    private final int imageHeight = 1080 / 3;

    public ImageSender(String ip, int porta, int offsetX, int offsetY) throws IOException, AWTException {
        this.cliente = new Socket(ip, porta);
        this.robot = new Robot();
        this.saida = new ObjectOutputStream(cliente.getOutputStream());
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    private BufferedImage screenCapture() {
        BufferedImage image = this.robot.createScreenCapture(this.screenRectangle);
        return image;
    }
    
    private BufferedImage getSubimage(BufferedImage image)
    {
        System.out.println(this.offsetX);
        System.out.println(this.offsetY);
        return image.getSubimage(this.offsetX, this.offsetY, this.imageWidth, this.imageHeight);
    }

    private void sendImage(BufferedImage image) throws IOException {
        BufferedImage newImage = this.getSubimage(image);
        ImageIcon imageIcon = new ImageIcon(newImage);
        this.saida.writeObject(imageIcon);
    }

    private void dispose() throws IOException {
        this.saida.close();
        this.cliente.close();
    }

    @Override
    public void run() {
        
        try {
            BufferedImage bi = this.screenCapture();
            this.sendImage(bi);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(ImageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
