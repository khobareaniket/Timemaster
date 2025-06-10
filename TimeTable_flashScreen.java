import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;


public class TimeTable_flashScreen extends JFrame {
    URL url;
    Icon icon;
    JLabel label;

    TimeTable_flashScreen() {
        setTitle("TimeTable Flash Screen");
        setSize(1024, 768);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            url = new URL("file:///C:/Users/khoba/Downloads/a1.gif");
            icon = new ImageIcon(url);
            label = new JLabel(icon);
            add(label);
            setVisible(true);
            Thread.sleep(1000);
            setVisible(false);
            new Index();
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TimeTable_flashScreen();
    }
}
