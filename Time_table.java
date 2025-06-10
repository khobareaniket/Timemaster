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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;


class Time_table extends JFrame implements KeyListener, ActionListener {
    // Initialize all Required Components
    JLabel wd, ct1, ct2, sb, lb, hms, d, d1, pb,sem,cl,div;
    JTextField wdt, ctth1, cttm2, ctth2, sbt, lbt, hmst, cttm1, pbt,semt,clt,divt;
    JPanel sl, f, sf[][];
    JLabel sl1[][];
    JLabel f1[][];
    JTextField slt1[][];
    JButton submit;
    int h, m, a;
    String Lec[][], Pra[][], Tut[][], PBA[][],TBA[][],temp[];
    int l = 0;
    int p = 0;
    int t = 0;
    int l1 = 0;
    int p1 = 0;
    int t1 = 0;
    int totalRows;
    String lecture[][];
    String practical[][];
    String tutorial[][];

    Time_table() {
        setTitle("Time Table Input");
        setSize(1024, 768);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        wd = new JLabel("How many Working days:");
        ct1 = new JLabel("College time from:");
        ct2 = new JLabel(" To:");
        sb = new JLabel("Short break in minutes:");
        lb = new JLabel("Lunch break in minutes:");
        hms = new JLabel("How many subjects:");
        d = new JLabel(":");
        d1 = new JLabel(":");
        submit = new JButton("Submit");
        pb = new JLabel("How many Practical Batch");
        sem = new JLabel("Semester");
        cl = new JLabel("Class");
        div = new JLabel("Division");

        wdt = new JTextField(20);
        ctth1 = new JTextField(10);
        ctth2 = new JTextField(10);
        sbt = new JTextField(20);
        lbt = new JTextField(20);
        hmst = new JTextField(20);
        cttm1 = new JTextField(10);
        cttm2 = new JTextField(10);
        pbt = new JTextField(10);
        semt = new JTextField(10);
        clt = new JTextField(10);
        divt = new JTextField(10);


        add(sem);
        add(semt);
        add(cl);
        add(clt);
        add(div);
        add(divt);
        add(wd);
        add(wdt);
        add(ct1);
        add(ctth1);
        add(d1);
        add(cttm1);
        add(ct2);
        add(ctth2);
        add(d);
        add(cttm2);
        add(sb);
        add(sbt);
        add(lb);
        add(lbt);
        add(pb);
        add(pbt);
        add(hms);
        add(hmst);
        

        hmst.addKeyListener(this);
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getSource() == hmst) {
            try {
                a = Integer.parseInt(hmst.getText());
                if (sl != null) {
                    remove(sl); 
                }
                sl = new JPanel();
                sl.setLayout(new GridLayout(a + 1, 7));

                
                sl1 = new JLabel[1][7];
                sl1[0][0] = new JLabel("Subject Name");
                sl1[0][1] = new JLabel("Subject Teacher");
                sl1[0][2] = new JLabel("Lecture Hours (per Week)");
                sl1[0][3] = new JLabel("Practical Hours (per Week)");
                sl1[0][4] = new JLabel("Tutorial Hours (per Week)");
                sl1[0][5] = new JLabel("Classroom No");
                sl1[0][6] = new JLabel("Practical Lab No");

               
                for (int i = 0; i < 7; i++) {
                    sl.add(sl1[0][i]);
                }

                slt1 = new JTextField[a][7];
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < 7; j++) {
                        slt1[i][j] = new JTextField(10);
                        sl.add(slt1[i][j]);
                    }
                }

                add(sl);
                add(submit);
                submit.addActionListener(this);
                revalidate();
                repaint();
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number for subjects.");
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void lectureSort(String[][] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Integer.parseInt(arr[j][2]) < Integer.parseInt(arr[j + 1][2])) {
                    
                    String temp = arr[j][0];
                    arr[j][0] = arr[j + 1][0];
                    arr[j + 1][0] = temp;

                    String temp1 = arr[j][1];
                    arr[j][1] = arr[j + 1][1];
                    arr[j + 1][1] = temp1;

                    String temp2 = arr[j][2];
                    arr[j][2] = arr[j + 1][2];
                    arr[j + 1][2] = temp2;
                }
            }
        }
    }

    public static void practicalSort(String[][] arr1, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Integer.parseInt(arr1[j][2]) < Integer.parseInt(arr1[j + 1][2])) {
                    
                    String temp = arr1[j][0];
                    arr1[j][0] = arr1[j + 1][0];
                    arr1[j + 1][0] = temp;

                    String temp1 = arr1[j][1];
                    arr1[j][1] = arr1[j + 1][1];
                    arr1[j + 1][1] = temp1;

                    String temp2 = arr1[j][2];
                    arr1[j][2] = arr1[j + 1][2];
                    arr1[j + 1][2] = temp2;
                }
            }
        }
    }

    public static void tutorialSort(String[][] arr3, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Integer.parseInt(arr3[j][2]) < Integer.parseInt(arr3[j + 1][2])) {
                  
                    String temp = arr3[j][0];
                    arr3[j][0] = arr3[j + 1][0];
                    arr3[j + 1][0] = temp;

                    String temp1 = arr3[j][1];
                    arr3[j][1] = arr3[j + 1][1];
                    arr3[j + 1][1] = temp1;

                    String temp2 = arr3[j][2];
                    arr3[j][2] = arr3[j + 1][2];
                    arr3[j + 1][2] = temp2;
                }
            }
        }
    }

public void addPractical() {
   
}



   public void addLecture() {
   int c=0;
    int numWorkingDays = Integer.parseInt(wdt.getText()); 

    for (int i = 0; i < numWorkingDays + 1; i++) {
        System.out.print("aniket");
        switch (i) {
            case 0:
                lectureSort(lecture, l);
                for (int inc = 0; inc < l; inc++) { 
                    for (int a1 = 0; a1 < totalRows - 1; a1++) { 
                        if (a1 != 2 && a1 != 3 && a1 != 4 && a1 != 5 && a1 != 7) {
                            if (sf[a1][i].getComponentCount() == 0) { 
                                String s = lecture[inc][0] + "( " + lecture[inc][1] + ") " + "("+lecture[inc][3] + ") ";
                                sf[a1][i].add(new JLabel(s));
                               
                                int a = Integer.parseInt(lecture[inc][2]);
                                a--;
                                lecture[inc][2] = Integer.toString(a);
                                if (a == 0) { 
                                    for (int j = inc; j < l - 1; j++) {
                                        lecture[j] = lecture[j + 1]; 
                                    }
                                    l--; 
                                    inc--; 
                                }
                                break; 
                            }
                        }
                    }
                }
                break;

            case 1:
                lectureSort(lecture, l);
                for (int inc = 0; inc < l; inc++) {
                    for (int a1 = 0; a1 < totalRows - 1; a1++) {
                        if (a1 != 2 && a1 != 6 && a1 != 7 && a1 != 5) {
                            if (sf[a1][i].getComponentCount() == 0) {
                                String s = lecture[inc][0] + "( " + lecture[inc][1] + ") " + "("+lecture[inc][3] + ") ";
                                sf[a1][i].add(new JLabel(s));
                                
                                int a = Integer.parseInt(lecture[inc][2]);
                                a--;
                                lecture[inc][2] = Integer.toString(a);
                                if (a == 0) {
                                    for (int j = inc; j < l - 1; j++) {
                                        lecture[j] = lecture[j + 1];
                                    }
                                    l--;
                                    inc--; 
                                }
                                break;
                            }
                        }
                    }
                }
                break;

            case 2:
                lectureSort(lecture, l);
                for (int inc = 0; inc < l; inc++) {
                    for (int a1 = 0; a1 < totalRows - 1; a1++) {
                        if (a1 != 2 && a1 != 3 && a1 != 4 && a1 != 5 && a1 != 7) {
                            if (sf[a1][i].getComponentCount() == 0) {
                                String s = lecture[inc][0] + "( " + lecture[inc][1] + ") " + "("+lecture[inc][3] + ") ";
                                sf[a1][i].add(new JLabel(s));
                               
                                int a = Integer.parseInt(lecture[inc][2]);
                                a--;
                                lecture[inc][2] = Integer.toString(a);
                                if (a == 0) {
                                    for (int j = inc; j < l - 1; j++) {
                                        lecture[j] = lecture[j + 1];
                                    }
                                    l--;
                                    inc--; 
                                }
                                break;
                            }
                        }
                    }
                }
                break;

            case 3:
                lectureSort(lecture, l);
                for (int inc = 0; inc < l; inc++) {
                    for (int a1 = 0; a1 < totalRows - 1; a1++) {
                        if (a1 != 2 && a1 != 5 && a1 != 1 && a1 != 0 && a1 != 7) {
                            if (sf[a1][i].getComponentCount() == 0) {
                                String s = lecture[inc][0] + "( " + lecture[inc][1] + ") " + "("+lecture[inc][3] + ") ";
                                sf[a1][i].add(new JLabel(s));
                                
                                int a = Integer.parseInt(lecture[inc][2]);
                                a--;
                                lecture[inc][2] = Integer.toString(a);
                                if (a == 0) {
                                    for (int j = inc; j < l - 1; j++) {
                                        lecture[j] = lecture[j + 1]; 
                                    }
                                    l--; 
                                    inc--; 
                                }
                                break; 
                            }
                        }
                    }
                }
                break;

            case 4:
                lectureSort(lecture, l);
                for (int inc = 0; inc < l; inc++) {
                    for (int a1 = 0; a1 < totalRows - 1; a1++) {
                        if (a1 != 2 && a1 != 3 && a1 != 4 && a1 != 5 && a1 != 6 && a1 != 7) {
                            if (sf[a1][i].getComponentCount() == 0) {
                                String s = lecture[inc][0] + "( " + lecture[inc][1] + ") " + "("+lecture[inc][3] + ") ";
                                sf[a1][i].add(new JLabel(s));
                                
                                int a = Integer.parseInt(lecture[inc][2]);
                                a--;
                                lecture[inc][2] = Integer.toString(a);
                                if (a == 0) {
                                    for (int j = inc; j < l - 1; j++) {
                                        lecture[j] = lecture[j + 1]; 
                                    }
                                    l--;
                                    inc--;
                                }
                                break; 
                            }
                        }
                    }
                }
                break;
        }
    }

    // Calculate total remaining lectures
    int sum1 = 0;
    for (int count = 0; count < l; count++) {
        sum1 += Integer.parseInt(lecture[count][2]);
    }

    String lec[][];
    lec = new String[sum1][2];
    int k = 0;
    c = 0;
    while (k < l) {
        for (int k1 = 0; k1 < Integer.parseInt(lecture[k][2]); k1++) {
            lec[c][0] = lecture[k][0];
            lec[c][1] = lecture[k][1];
            c++;
        }
        k++;
    }

    for (int z = 0; z < c; z++) {
        System.out.println("l " + lec[z][0] + "() " + lec[z][0]);
    }

if(totalRows>=7){
    k = 0; 
    for (int i = 0; i < numWorkingDays; i++) {
        if (i == 0 || i == 2 || i == 3) {
            if (k < c) { 
                sf[7][i].add(new JLabel(lec[k][0]));
                k++;
            } 
        }
    }
}
}




    public void calculateCounts() {
    l = 0; 
    p = 0; 
    t = 0; 
    l1 = 0; 
    p1 = 0; 
    t1 = 0;

  
    for (int k = 0; k < a; k++) {
        try {
            if (k < slt1.length) {
                if (!slt1[k][2].getText().isEmpty() && Integer.parseInt(slt1[k][2].getText()) > 0) {
                    l++;
                }
                if (!slt1[k][3].getText().isEmpty() && Integer.parseInt(slt1[k][3].getText()) > 0) {
                    p++;
                }
                if (!slt1[k][4].getText().isEmpty() && Integer.parseInt(slt1[k][4].getText()) > 0) {
                    t++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input in row " + (k + 1) + ". Please enter valid integers.");
        }
    }

    System.out.println("l: " + l + " p: " + p + " t: " + t);


    lecture = (l > 0) ? new String[l][4] : new String[0][0];
    practical = (p > 0) ? new String[p][4] : new String[0][0];
    tutorial = (t > 0) ? new String[t][4] : new String[0][0];


    for (int k = 0; k < a; k++) {
        try {
            if (k < slt1.length) {
                if (!slt1[k][2].getText().isEmpty() && Integer.parseInt(slt1[k][2].getText()) > 0) {
                    lecture[l1][0] = slt1[k][0].getText();
                    lecture[l1][1] = slt1[k][1].getText();
                    lecture[l1][2] = slt1[k][2].getText();
                    lecture[l1][3] = slt1[k][5].getText();
                    l1++;
                }
                if (!slt1[k][3].getText().isEmpty() && Integer.parseInt(slt1[k][3].getText()) > 0) {
                    practical[p1][0] = slt1[k][0].getText();
                    practical[p1][1] = slt1[k][1].getText();
                    practical[p1][2] = slt1[k][3].getText();
                    practical[p1][3] = slt1[k][6].getText();
                    p1++;
                }
                if (!slt1[k][4].getText().isEmpty() && Integer.parseInt(slt1[k][4].getText()) > 0) {
                    tutorial[t1][0] = slt1[k][0].getText();
                    tutorial[t1][1] = slt1[k][1].getText();
                    tutorial[t1][2] = slt1[k][4].getText();
                    tutorial[t1][3] = slt1[k][6].getText();
                    t1++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input in row " + (k + 1));
        }
    }

   
    int sum1 = 0;
    for (int count = 0; count < p; count++) {
        sum1 += Integer.parseInt(practical[count][2]);
    }
    sum1 = sum1 / 2;
    PBA = new String[Integer.parseInt(pbt.getText())][sum1 + 1];

    int sum=0;
    for(int count =0;count<t;count++){
        sum += Integer.parseInt(tutorial[count][2]);
    }
    if(sum % 2 != 0){
        TBA = new String[Integer.parseInt(pbt.getText())][sum+1];
        //PBA = new String[Integer.parseInt(pbt.getText())][(sum1 + 1)+(sum+1)];
    }else{
        TBA = new String[Integer.parseInt(pbt.getText())][sum+2];
       // PBA = new String[Integer.parseInt(pbt.getText())][(sum1 + 1)+(sum+2)];
    }
    

    // Sort the arrays
    lectureSort(lecture, l);
    practicalSort(practical, p);
    tutorialSort(tutorial, t);

    System.out.println("sum: " + sum);
    addLecture(); 
    addPractical();

    // Fill PBA array
    for (int i = 0; i < Integer.parseInt(pbt.getText()); i++) {
        PBA[i][0] = "B" + (i + 1);
        int k = 1;
        int totalPracticalEntries = p;
        for (int j = 0; j < totalPracticalEntries; j++) {
            
            int practicalIndex = (i + j) % totalPracticalEntries; 
            int d = Integer.parseInt(practical[practicalIndex][2]) / 2; 
            for (int co = 0; co < d; co++) {
                if (k < PBA[i].length) { 
                    PBA[i][k] = practical[practicalIndex][0]; 
                    k++; 
                }
            }
        }
    }

     System.out.println("First PBA contents:");
    for (int i = 0; i < Integer.parseInt(pbt.getText()); i++) {
        for (int j = 0; j < PBA[i].length; j++) {
            if (PBA[i][j] != null) { 
                System.out.print(PBA[i][j] + " ");
            }
        }
        System.out.println();
    }

    //fill TBA array

    for(int i=0;i<Integer.parseInt(pbt.getText());i++){
        TBA[i][0] = "B"+(i+1);
        int k = 1;
        int totaltutorialEntries = t;
       /* if(totaltutorialEntries % 2 != 0){
            totaltutorialEntries++;
        }*/
        for(int j=0;j<totaltutorialEntries;j++){
            int tutorialIndex = (i+j)%totaltutorialEntries;
            int d = Integer.parseInt(tutorial[tutorialIndex][2]);

            for(int co =0;co<d;co++){
                if(k<TBA[i].length){
                    TBA[i][k] =tutorial[tutorialIndex][0]+"("+tutorial[tutorialIndex][3]+")";
                    k++;
                }
            }
            if(j>=t){
                TBA[i][k] = "EH";
            }
        }
    }

// fill Practical and Tutorial

  /*  for(int i=0;i<Integer.parseInt(pbt.getText());i++){
        int k=0;
        int p=1;
        for(int j=0;j<PBA[i].length;j++){
            k=j+1;
            if(PBA[i][j] == PBA[i][k]){
                 String[] newArr = new String[PBA[i].length + 1];

                 for (int z = 0; z < k; z++) {
                    newArr[z] = PBA[i][z];
                }
                newArr[k] =" "+TBA[p]+" "+TBA[p];
                p +=2;
                for (int z = k; z < PBA[i].length; z++) {
                    newArr[z + 1] = PBA[i][z];
                }

                 PBA[i] = newArr;
            }
        }

    }*/

   for (int i = 0; i < Integer.parseInt(pbt.getText()); i++) {
    int c = 1;
    int check=0;
    for (int j = 0; j < PBA[i].length - 1; j++) { 
        if (PBA[i][j].equals(PBA[i][j + 1])) { 
            if(TBA[i].length==c || TBA[i][c]==null){
                break;
             }
            String[] newArr = new String[PBA[i].length + 1];

           
            for (int z = 0; z <= j; z++) {
                newArr[z] = PBA[i][z];
            }

            
            if (c < TBA[i].length) { 
            if(c < TBA[i].length-1){
                newArr[j + 1] = " " + TBA[i][c] + " " + TBA[i][c + 1]; 
                c += 2;
                check = j+1;
            }else{
                if(TBA[i][c]!=null){
                newArr[j + 1] = " " + TBA[i][c] + "Eh"; 
                c++;
                check = j+1;
                }else{
                    check = j;
                }
            }
                
                
            }

           
            for (int z =check; z < PBA[i].length; z++) {
                newArr[z + 1] = PBA[i][z]; 
            }

            
            PBA[i] = newArr;
            j++; 
        }
    }
}

    temp = new String[PBA[PBA.length-1].length];
     for (int i = 0; i < PBA[PBA.length-1].length; i++) {
            temp[i] = PBA[PBA.length-1][i];
        }
        int batches = PBA.length;
        int subjects = PBA[0].length;

       
        HashSet<String>[] usedSubjects = new HashSet[subjects];
        for (int i = 0; i < subjects; i++) {
            usedSubjects[i] = new HashSet<>();
        }

        
        for (int i = 0; i < batches; i++) {
            for (int j = 1; j < subjects; j++) { 
                usedSubjects[j].add(PBA[i][j]);
            }
        }

        
        for (int j = 1; j < subjects; j++) {
            HashSet<String> seenSubjects = new HashSet<>();
            for (int i = 0; i < batches; i++) {
                String subject = PBA[i][j];

                
                if (seenSubjects.contains(subject)) {
                    for (int k = 1; k < subjects; k++) {
                        if (k != j && !usedSubjects[j].contains(PBA[i][k])) {
                            
                            usedSubjects[j].remove(subject);
                            PBA[i][j] = PBA[i][k];
                            usedSubjects[j].add(PBA[i][k]);
                            break;
                        }
                    }
                }

                seenSubjects.add(PBA[i][j]);
            }
        }

        for (int i = 0; i < PBA[PBA.length-1].length; i++) {
            //temp[i] = PBA[PBA.length-1][i];
            PBA[PBA.length-1][i] = temp[i];
        }
    
    for (int q = 0; q < l; q++) {
        System.out.println(lecture[q][0] + " " + lecture[q][1] + " " + lecture[q][2]);
    }
    for (int q = 0; q < p; q++) {
        System.out.println(practical[q][0] + " " + practical[q][1] + " " + practical[q][2]);
    }
    for (int q = 0; q < t; q++) {
        System.out.println(tutorial[q][0] + " " + tutorial[q][1] + " " + tutorial[q][2]);
    }

    
    System.out.println("PBA contents:");
    for (int i = 0; i < Integer.parseInt(pbt.getText()); i++) {
        for (int j = 0; j < PBA[i].length; j++) {
            if (PBA[i][j] != null) { 
                System.out.print(PBA[i][j] + " ");
            }
        }
        System.out.println();
    }
Random rand = new Random();
for (int i = PBA[3].length - 1; i > 0; i--) {
            int j = 3; // random index from 0 to i
            // Swap arr[i] with arr[j]
            String temp = PBA[3][i];
            PBA[3][i] = PBA[3][j];
            PBA[3][j] = temp;
        }
        for (int i = PBA[3].length - 1; i > 0; i--) {
            int j = 3; // random index from 0 to i
            // Swap arr[i] with arr[j]
            String temp = PBA[3][i];
            PBA[3][i] = PBA[3][j];
            PBA[3][j] = temp;
        }
        for (int i = PBA[3].length - 1; i > 0; i--) {
            int j = 3; // random index from 0 to i
            // Swap arr[i] with arr[j]
            String temp = PBA[3][i];
            PBA[3][i] = PBA[3][j];
            PBA[3][j] = temp;
        }
    
   
    System.out.println("TBA contents:");
    for (int i = 0; i < Integer.parseInt(pbt.getText()); i++) {
        for (int j = 0; j < TBA[i].length; j++) {
            if (TBA[i][j] != null) { 
                System.out.print(TBA[i][j] + " ");
            }
        }
        System.out.println();
    }
       //Add Practicals And Tutorials
    int nwd = Integer.parseInt(wdt.getText());
    for (int i = 0; i < nwd; i++) {
        System.out.print("sds");
        switch (i) {
            case 0:
               /* if(PBA.length <=1){
                    break;
                }*/
                if (PBA[i].length <= 1) {
                    break;
                } else {
                    for (int a1 = 0; a1 < totalRows; a1++) {
                        if (a1 == 3) { 
                            if (sf[a1][i].getComponentCount() < 2) {
                                String s;
                                System.out.print("ljkj");
                                
                                for (int p = 0; p < PBA.length; p++) { 
                                    if (PBA[p][i + 1] != null) { 
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 1])) { 
                                                s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")"; 
                                                 sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 1] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                            }
                        }
                    }
                }
                break;
                
            case 1:if (PBA[i].length <= 2) {
                    break;
                } else {
                    for (int a1 = 0; a1 < totalRows; a1++) {
                        if (a1 == 6) { 
                            if (sf[a1][i].getComponentCount() < 2) { 
                                String s;
                                System.out.print("ljkj");
                               for (int p = 0; p < PBA.length; p++) { 
                                    if (PBA[p][i + 1] != null) { 
                                        
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 1])) { 
                                               
                                                s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 1] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                            }
                        }
                    }
                }
                break;

            case 2:
                /*if(PBA.length <=3){
                    break;
                }*/
                if (PBA[i].length <= 3) {
                    break;
                } else {
                    for (int a1 = 0; a1 < totalRows; a1++) {
                        if (a1 == 3) { 
                            if (sf[a1][i].getComponentCount() < 2) { 
                                String s;
                                System.out.print("ljkj");
                                
                                 for (int p = 0; p < PBA.length; p++) { 
                                    if (PBA[p][i + 1] != null) {
                                        
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 1])) { 
                                                
                                                s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 1] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                            }
                        }
                    }
                }
                break;

            case 3:
                if (PBA[i].length <= 4) {
                    break;
                } else {
                    for (int a1 = 0; a1 < totalRows; a1++) {
                        if (a1 == 0) { 
                            if (sf[a1][i].getComponentCount() < 2) { 
                                String s;
                                System.out.print("ljkj");
                                
                                 for (int p = 0; p < PBA.length; p++) { 
                                    if (PBA[p][i + 1] != null) {
                                        
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 1])) { 
                                                
                                                s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")";
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 1] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 4:
                 if(PBA[1].length <= 5){
                        break;
                    }else{
                        System.out.println("PBA length:"+PBA[0].length);
                for (int a1 = 0; a1 < totalRows; a1++) { 
                    if (a1 == 3) { 
                        if (sf[a1][i].getComponentCount() < 2) { 
                            String s;
                            System.out.print("ljkj");
                            for (int p = 0; p < PBA.length; p++) { 
                                    if (PBA[p][i + 1] != null) { 
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 1])) { 
                                                 s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 1] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                        }
                    }
                   
                  /* if (a1 == 6) { // Check for specific row
                        if (sf[a1][i].getComponentCount() < 2) { // Check if less than 2 components are present
                            String s;
                            System.out.print("ljkj");
                            // Loop through each practical batch
                            for (int p = 0; p < PBA.length; p++) { // Loop through each practical batch
                                if (PBA[p][i+2] != null) { // Ensure we don't access null
                                    // Find the practical in the practical array
                                    for (int pIndex = 0; pIndex < practical.length; pIndex++) {
                                        if (practical[pIndex][0].equals(PBA[p][i+2])) { // Match practical name
                                            // Format the string as "B1 (a5)()"
                                            s = PBA[p][0] + " (" + practical[pIndex][0] + ")()"; // Use PBA[p][0] for batch name
                                            sf[a1][i].add(new JLabel(s)); // Add JLabel to the panel
                                            System.out.print(s);
                                            break; // Exit after adding one practical
                                        }
                                    }
                                     // Exit after adding one practical for this batch
                                }
                            }
                        }
                    
                    }*/
                }
                    }
                if(PBA[1].length <= 6){
                        break;
                    }else{
                for (int a1 = 0; a1 < totalRows; a1++) { 
                   /* if (a1 == 3) { // Check for specific row
                        if (sf[a1][i].getComponentCount() < 2) { // Check if less than 2 components are present
                            String s;
                            System.out.print("ljkj");
                            // Loop through each practical batch
                            for (int p = 0; p < PBA.length; p++) { // Loop through each practical batch
                                if (PBA[p][i+1] != null) { // Ensure we don't access null
                                    // Find the practical in the practical array
                                    for (int pIndex = 0; pIndex < practical.length; pIndex++) {
                                        if (practical[pIndex][0].equals(PBA[p][i+1])) { // Match practical name
                                            // Format the string as "B1 (a5)()"
                                            s = PBA[p][0] + " (" + practical[pIndex][0] + ")()"; // Use PBA[p][0] for batch name
                                            sf[a1][i].add(new JLabel(s)); // Add JLabel to the panel
                                            System.out.print(s);
                                            break; // Exit after adding one practical
                                        }
                                    }
                                     // Exit after adding one practical for this batch
                                }
                            }
                        }
                    }*/
                   
                   if (a1 == 6) { 
                        if (sf[a1][i].getComponentCount() < 2) { 
                            String s;
                            System.out.print("ljkj");
                            
                            for (int p = 0; p < PBA.length; p++) {
                                    if (PBA[p][i + 2] != null) {
                                        
                                        for (int pIndex = 0; pIndex < practical.length;pIndex++) {
                                            if (practical[pIndex][0].equals(PBA[p][i + 2])) { 
                                                 s = PBA[p][0] + " (" + practical[pIndex][0] + ")("+practical[pIndex][3]+")"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break; 
                                            }
                                            else{
                                                s = PBA[p][0] + " (" + PBA[p][i + 2] + ")()"; 
                                                sf[a1][i].add(new JLabel(s)); 
                                                System.out.print(s);
                                                break;
                                            }
                                        }
                                        
                                        
                                    }
                                }
                        }
                    
                    }
                }
                
                break;
                    }   
            default:
                
                break;
        }
    } 
int k = Integer.parseInt(wdt.getText());
for (int i = 0; i < totalRows - 1; i++) {
                for (int j = 0; j < k; j++) {
                    if (sf[i][j].getComponentCount() < 1){
                        sf[i][j].add(new JLabel("Extra Hours"));
                    }
                }
            }

}


public void captureAndSaveAsPDF(JComponent component, String filePath) throws Exception {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        component.paint(g2d);
        g2d.dispose();

        File tempFile = new File("temp_snapshot.png");
        ImageIO.write(image, "png", tempFile);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        
document.open();
//college name
Font titleFont1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,14);
Paragraph title1 = new Paragraph("Marathwada Mitramandal’s Institute of Technology,Lohegoan Pune-47\n\n", titleFont1);
title1.setAlignment(Element.ALIGN_CENTER);
document.add(title1);
// Bold, Large, Centered Title
Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
Paragraph title = new Paragraph("TimeTable 2024-25\n\n", titleFont);
title.setAlignment(Element.ALIGN_CENTER);
document.add(title);

// "Branch: Computer Engineer" with bold "Branch"
Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

Paragraph branch = new Paragraph();
branch.add(new Chunk("Branch: ", boldFont));
branch.add(new Chunk("Computer Engineer\n\n", normalFont));
document.add(branch);

Paragraph sem1 = new Paragraph();
sem1.add(new Chunk("Semester: ", boldFont));
sem1.add(new Chunk(semt.getText()+"\n\n", normalFont));
document.add(sem1);

// "Div: SEB" with bold "Div"
Paragraph div = new Paragraph();
div.add(new Chunk("Div: ", boldFont));
div.add(new Chunk(divt.getText()+"\n\n", normalFont));
document.add(div);

Paragraph class1 = new Paragraph();
class1.add(new Chunk("Class: ", boldFont));
class1.add(new Chunk(clt.getText()+"\n\n", normalFont));
document.add(class1);

LocalDateTime now = LocalDateTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
String formattedDateTime = now.format(formatter);

Paragraph wef = new Paragraph();
wef.add(new Chunk("w.e.f: ", boldFont));
wef.add(new Chunk(formattedDateTime+"\n\n", normalFont));
document.add(wef);

        Image pdfImg = Image.getInstance("temp_snapshot.png");
        pdfImg.scaleToFit(500, 500);
        document.add(pdfImg);
        PdfPTable bottomTable = new PdfPTable(2);
bottomTable.setWidthPercentage(100);
bottomTable.setSpacingBefore(50f); // spacing before bottom text

// Left Cell
PdfPCell leftCell = new PdfPCell(new Paragraph("\n\n\nCo-ordinator Timetable", boldFont));
leftCell.setBorder(PdfPCell.NO_BORDER);
leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);

// Right Cell
PdfPCell rightCell = new PdfPCell(new Paragraph("\n\n\nHOD Dept of Computer Engg", boldFont));
rightCell.setBorder(PdfPCell.NO_BORDER);
rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

// Add cells to table
bottomTable.addCell(leftCell);
bottomTable.addCell(rightCell);

document.add(bottomTable);
        document.close();

        tempFile.delete();
    }


    public void actionPerformed(ActionEvent ae) {
        try {
            int th1 = Integer.parseInt(ctth1.getText());
            int th2 = Integer.parseInt(ctth2.getText());
            int tm1 = Integer.parseInt(cttm1.getText());
            int tm2 = Integer.parseInt(cttm2.getText());
            int dh = th2 - th1;
            int dm = tm2 - tm1;
            if (dm < 0) {
                dm = 60 + dm;
                dh--;
            }
            int sb = Integer.parseInt(sbt.getText());
            int lb = Integer.parseInt(lbt.getText());

            int total_time = sb + lb;

            if (total_time >= 60) {
                h = total_time / 60;
                m = total_time % 60;
            } else {
                h = 0;
                m = total_time;
            }

            int lh = dh - h;
            int lm = dm - m;

            if (lm < 0) {
                lm = 60 + lm;
                lh--;
            }

            int k = Integer.parseInt(wdt.getText());
            totalRows = lh + 3; 
            f1 = new JLabel[totalRows][k + 1];
            sf = new JPanel[totalRows - 1][k]; 
            
            f = new JPanel();
            f.setLayout(new GridLayout(totalRows, k + 1));
             Border border = BorderFactory.createLineBorder(Color.BLACK, 3); // 3-pixel black border
             f.setBorder(border);

            
            f1[0][0] = new JLabel("Time/Days");
            f.add(f1[0][0]);
            String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            for (int i = 1; i <= k; i++) {
                f1[0][i] = new JLabel(day[i - 1]);
                f.add(f1[0][i]);
            }

            
            for (int i = 0; i < totalRows - 1; i++) {
                for (int j = 0; j < k; j++) {
                    sf[i][j] = new JPanel();
                    sf[i][j].setLayout(new GridLayout(4, 1));
                    sf[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    sf[i][j].setPreferredSize(new Dimension(40, 50));
                }
            }

            
            int currentRow = 1;
            int startHour = th1;
            int startMinute = tm1;

            for (int i = 1; i <= lh; i++) {
                
                int endMinute = startMinute + 60; 
                int endHour = startHour;

                if (endMinute >= 60) {
                    endMinute -= 60;
                    endHour++;
                }

                
                String timeSlot = String.format("%02d:%02d to %02d:%02d", startHour, startMinute, endHour, endMinute);
                f1[currentRow][0] = new JLabel(timeSlot); 
                f.add(f1[currentRow][0]);
                for (int j = 1; j <= k; j++) {
                    f.add(sf[currentRow - 1][j - 1]);
                }
                currentRow++;

               
                startHour = endHour;
                startMinute = endMinute;

                
                if (i == 2 && currentRow < totalRows) {
                    startMinute += sb; 
                    if (startMinute >= 60) {
                        startMinute -= 60;
                        startHour++;
                    }
                    f1[currentRow][0] = new JLabel("Short Break (" + sb + " min)"); 
                    f.add(f1[currentRow][0]);
                    for (int j = 1; j <= k; j++) {
                        f.add(new JLabel("Short Break"));
                    }
                    currentRow++;
                }

                
                if (i == 4 && currentRow < totalRows) {
                    startMinute += lb; 
                    if (startMinute >= 60) {
                        startMinute -= 60;
                        startHour++;
                    }
                    f1[currentRow][0] = new JLabel("Lunch Break (" + lb + " min)"); 
                    f.add(f1[currentRow][0]);
                    for (int j = 1; j <= k; j++) {
                        f.add(new JLabel("Lunch Break"));
                    }
                    currentRow++;
                }
            }

            
            calculateCounts();
           
            add(f);
            JButton captureBtn = new JButton("Export PDF");
        captureBtn.addActionListener(e -> {
            try {
                captureAndSaveAsPDF(f, clt.getText()+divt.getText()+".pdf");
                JOptionPane.showMessageDialog(this, "PDF saved successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save PDF.");
            }
        });
        add(captureBtn);
        
            revalidate();
            repaint();
        } catch (NumberFormatException ex) {
            System.out.println("Please enter valid numbers for time and breaks.");
        }
    }
}
