/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrisislem;


import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static matrisislem.OtomatikIslemlerController.matrisCarpımFonk;
import static matrisislem.OtomatikIslemlerController.matrisTranspozFonk;
import static matrisislem.OtomatikIslemlerController.prodcounter;

/**
 * FXML Controller class
 *
 * @author Kadir Han
 */

public class ManualIslemlerController implements Initializable {

    @FXML private RadioButton rb1,rb2,rb3,rb4,rb5,rb11,rb12,rb13,rb14,rb15;
    @FXML private TextField tv00,tv01,tv02,tv03,tv04,tv10,tv11,tv12,tv13,tv14,tv20,tv21,tv22,tv23,tv24,tv30,tv31,tv32,tv33,tv34,tv40,tv41,tv42,tv43,tv44;
    @FXML private Button geriButton,tersiniAlButton;
    @FXML private GridPane gridPane;
    @FXML private TextArea islemler;
    
    public static double[][] anaMatris;
    public static TextField[][] tvDizisi;
    int satirBo=1,sutunBo=2;
    
    private String islemYazilacak="";
    static int sumcounter = 0;
    static int prodcounter = 0;
    
    public void tersAlHandler(ActionEvent event) throws Exception{
        tvDiziyeAtma();
        islemYazilacak="";
        islemler.setText(islemYazilacak);
        if(!isDouble(tv00) || !isDouble(tv01) || !isDouble(tv02) || !isDouble(tv03) || !isDouble(tv04) || 
                !isDouble(tv10) || !isDouble(tv11) || !isDouble(tv12) || !isDouble(tv13) || !isDouble(tv14) ||
                !isDouble(tv20) || !isDouble(tv21) || !isDouble(tv22) || !isDouble(tv23) || !isDouble(tv24) ||
                !isDouble(tv30) || !isDouble(tv31) || !isDouble(tv32) || !isDouble(tv33) || !isDouble(tv34) ||
                !isDouble(tv40) || !isDouble(tv41) || !isDouble(tv42) || !isDouble(tv43) || !isDouble(tv44) )
        {
            
            //System.out.println("uyarıı");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("UYARI");
            alert.setHeaderText("");
            alert.setContentText("Lütfen Sayı Giriniz!!!");
            alert.show();
            return;
            
        }
        
        anaMatris=new double[satirBo][sutunBo];
        
        for (int i = 0; i < satirBo; i++) {
            for (int j = 0; j < sutunBo; j++) {
                //System.out.println(" - - "+tvDizisi[i][j].getText());
                double deger=Double.parseDouble(tvDizisi[i][j].getText());
                anaMatris[i][j]=deger;
            }
        }
        int rowSize=satirBo, columnSize=sutunBo;
        double[][] ilkMatris=anaMatris;
        islemYazilacak+="Transpoz (A)': \n";
        islemler.setText(islemYazilacak);
        System.out.println("\nTranspoz (A)'");
        double[][] matristranpose = matrisTranspozFonk(ilkMatris);
        for (int i = 0; i < matristranpose.length; i++) {
            for (int j = 0; j < matristranpose[0].length; j++) {
                System.out.print(matristranpose[i][j] + " ");
                islemYazilacak += matristranpose[i][j] + "   ";
                
            }
            System.out.println("");
            islemYazilacak += "\n";
        }
        islemler.setText(islemYazilacak);
        
        double[][] matrisCarpimi;
        if (rowSize > columnSize) {
            System.out.println("\nMatris_Transpoz X Matris (A^T * A) ");
            islemYazilacak+="\nMatris_Transpoz X Matris (A^T * A) : \n";
            matrisCarpimi = matrisCarpımFonk(matristranpose, ilkMatris);
        } else {
            System.out.println("\nMatris X Matris_Transpoz (A * A^T) ");
            islemYazilacak+="\nMatris X Matris_Transpoz (A * A^T) : \n";
            matrisCarpimi = matrisCarpımFonk(ilkMatris, matristranpose);
        }
        for (int i = 0; i < matrisCarpimi.length; i++) {
            for (int j = 0; j < matrisCarpimi[0].length; j++) {
                System.out.print(matrisCarpimi[i][j] + " ");
            }
            System.out.println("");
        }
        String[][] matrisCarpimString = new String[matrisCarpimi.length][matrisCarpimi[0].length];
        for (int i = 0; i < matrisCarpimi.length; i++) {
            for (int j = 0; j < matrisCarpimi[0].length; j++) {
                 matrisCarpimString[i][j] = new DecimalFormat("##.#").format(matrisCarpimi[i][j]);
                 System.out.print(matrisCarpimString[i][j] + " ");
                 islemYazilacak +=matrisCarpimString[i][j] + "   ";
            }
            System.out.println("");
            islemYazilacak += "\n";
        }
        islemler.setText(islemYazilacak);
        System.out.println("***********" + prodcounter);
        System.out.println("+++++++++++" + sumcounter);

        if (rowSize > columnSize) {
            System.out.println("\nOluşan Matrisin Tersi (A^T * A)^-1 ");
            islemYazilacak += "\nOluşan Matrisin Tersi (A^T * A)^-1\n";
        } else {
            System.out.println("\nOluşan Matrisin Tersi (A * A^T)^-1 ");
            islemYazilacak +="\nOluşan Matrisin Tersi (A * A^T)^-1 \n";
        }
        
        double[][] matrisTersi = matrisTersiFonk(matrisCarpimi);
        for (int i = 0; i < matrisTersi.length; i++) {
            for (int j = 0; j < matrisTersi[0].length; j++) {
                System.out.print(matrisTersi[i][j] + " ");
            }
            System.out.println("");
        }
        String[][] matrisTersiString = new String[matrisTersi.length][matrisTersi[0].length];
        for (int i = 0; i < matrisTersi.length; i++) {
            for (int j = 0; j < matrisTersi[0].length; j++) {
                 matrisTersiString[i][j] = new DecimalFormat("##.########").format(matrisTersi[i][j]);
                 System.out.print(matrisTersiString[i][j] + " ");
                 islemYazilacak += matrisTersiString[i][j] + "   ";
            }
            System.out.println("");
            islemYazilacak += "\n";
        }
        if(!isDouble(matrisTersiString[0][0])){
            System.out.println("Tersi alınamıyor!!!!");
            Alert alert1=new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("UYARI");
            alert1.setHeaderText("");
            alert1.setContentText("Matris is singular to working precision!!!");
            alert1.showAndWait();
            return;
        }
        islemler.setText(islemYazilacak);

        double[][] matrisSonuc;
        if (rowSize > columnSize) {
            System.out.println("\nGirilen Matrisin Tersi (A^T * A)^-1 * A^T");
            islemYazilacak += "\nGirilen Matrisin Tersi (A^T * A)^-1 * A^T\n";
            matrisSonuc = matrisCarpımFonk(matrisTersi, matristranpose);
        } else {
            System.out.println("\nGirilen Matrisin Tersi  A^T * (A * A^T)^-1");
            islemYazilacak += "\nGirilen Matrisin Tersi  A^T * (A * A^T)^-1\n";
            matrisSonuc = matrisCarpımFonk(matristranpose, matrisTersi);
        }
        islemler.setText(islemYazilacak);
        for (int i = 0; i < matrisSonuc.length; i++) {
            for (int j = 0; j < matrisSonuc[0].length; j++) {
                System.out.print(matrisSonuc[i][j] + " ");
            }
            System.out.println("");
        }
        String[][] matrisSonucString = new String[matrisSonuc.length][matrisSonuc[0].length];
        for (int i = 0; i < matrisSonuc.length; i++) {
            for (int j = 0; j < matrisSonuc[0].length; j++) {
                 matrisSonucString[i][j] = new DecimalFormat("##.########").format(matrisSonuc[i][j]);
                 System.out.print(matrisSonucString[i][j] + " ");
                 islemYazilacak +=matrisSonucString[i][j] + "   ";
            }
            System.out.println("");
            islemYazilacak += "\n";
        }
        islemler.setText(islemYazilacak);
        System.out.println("***********" + prodcounter);
        System.out.println("+++++++++++" + sumcounter);
        islemYazilacak+="\n\n Yapılan çarpma-bölme işlemleri: "+ prodcounter;
        islemYazilacak+="\n Yapılan toplama-çıkarma işlemleri: "+ sumcounter;
        islemler.setText(islemYazilacak);
        prodcounter=0;
        sumcounter=0;
        
    }
    
    public static double[][] matrisTranspozFonk(double[][] a) {
        int matrisSatir = a.length;
        int matrisSutun = a[0].length;
        double[][] b = new double[matrisSutun][matrisSatir];
        for (int i = 0; i < matrisSatir; i++) {
            for (int j = 0; j < matrisSutun; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    public static double[][] matrisCarpımFonk(double[][] a, double[][] b) {
        int mat1Satir = a.length;
        int mat1Sutun = a[0].length;
        int mat2Satir = b.length;
        int mat2Sutun = b[0].length;

        double[][] c = new double[mat1Satir][mat2Sutun];
        for (int i = 0; i < mat1Satir; i++) {
            for (int j = 0; j < mat2Sutun; j++) {
                for (int k = 0; k < mat1Sutun; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    prodcounter++;
                }
            }
        }
        sumcounter = sumcounter + (mat1Satir * (mat2Satir - 1) * mat2Sutun);
        return c;
    }

    public static double[][] matrisTersiFonk(double[][] gelenMatris) {

        double[][] matrixGelen = gelenMatris;
        double oran, a;
        int n = matrixGelen.length;
        double[][] sonucMatrisi = new double[n][n];
        double[][] birlesikMatris = new double[n][2*n];
        
        //GELEN MATRİSİ BİRLEŞİKMATRİS'E ATTIK
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                birlesikMatris[i][j] = matrixGelen[i][j];
            }
        }
        //BİRİM MATRİSİ DE BİRLEŞİKMATRİS'E ATTIK
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                if (i == (j - n)) {
                    birlesikMatris[i][j] = 1.0;
                } else {
                    birlesikMatris[i][j] = 0.0;
                }
            }
        }
        //SATIRLAR ARASI ORANI BULUP ÇARPMA 0'A ULAŞMAYA ÇALIŞTIK
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    oran = birlesikMatris[j][i] / birlesikMatris[i][i];
                    prodcounter++;
                    for (int k = 0; k < 2 * n; k++) {
                        birlesikMatris[j][k] -= oran * birlesikMatris[i][k];
                        prodcounter++;
                        sumcounter++;
                    }
                }
            }
        }
        //OLUŞAN KÖŞEGEN MATRİSİ KENDİ DEĞERLERİNE BÖLÜP BİRİM MATRİS OLUŞTURDUK
        for (int i = 0; i < n; i++) {
            a = birlesikMatris[i][i];
            for (int j = 0; j < 2 * n; j++) {
                birlesikMatris[i][j] /= a;
                prodcounter++;
            }
        }
        //BİRLEŞİKMATRİS İÇİNDEKİ 2.MATRİS GELEN MATRİSİN TERSİ
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sonucMatrisi[i][j] = birlesikMatris[i][n + j];
            }
        }
        gelenMatris = sonucMatrisi;
        return gelenMatris;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rb1.setSelected(true);
        rb11.setDisable(true);
        rb12.setSelected(true);
        tv00.setVisible(true);
        tv01.setVisible(true);
        tv02.setVisible(false);
        tv03.setVisible(false);
        tv04.setVisible(false);
        tv10.setVisible(false);
        tv11.setVisible(false);
        tv12.setVisible(false);
        tv13.setVisible(false);
        tv14.setVisible(false);
        tv20.setVisible(false);
        tv21.setVisible(false);
        tv22.setVisible(false);
        tv23.setVisible(false);
        tv24.setVisible(false);
        tv30.setVisible(false);
        tv31.setVisible(false);
        tv32.setVisible(false);
        tv33.setVisible(false);
        tv34.setVisible(false);
        tv40.setVisible(false);
        tv41.setVisible(false);
        tv42.setVisible(false);
        tv43.setVisible(false);
        tv44.setVisible(false);
        tv00.setText("0");
        tv01.setText("0");
        tv02.setText("0");
        tv03.setText("0");
        tv04.setText("0");
        tv10.setText("0");
        tv11.setText("0");
        tv12.setText("0");
        tv13.setText("0");
        tv14.setText("0");
        tv20.setText("0");
        tv21.setText("0");
        tv22.setText("0");
        tv23.setText("0");
        tv24.setText("0");
        tv30.setText("0");
        tv31.setText("0");
        tv32.setText("0");
        tv33.setText("0");
        tv34.setText("0");
        tv40.setText("0");
        tv41.setText("0");
        tv42.setText("0");
        tv43.setText("0");
        tv44.setText("0");
        
        
    }
    
    public void onclick(ActionEvent event) throws Exception{
        Parent layout=FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));  
            
        Scene gris=new Scene(layout);
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(gris);
        window.show();
    }
    
    
    
    public void tvHandler()throws Exception{
        
        
        
    }
    public boolean isDouble(TextField tv) throws Exception{
        
        try{
            double d=Double.parseDouble(tv.getText());
            return true;
            
        }catch(Exception e){
            return false;
        }
    }
    public boolean isDouble(String deg) throws Exception{
        
        try{
            double d=Double.parseDouble(deg);
            return true;
            
        }catch(Exception e){
            return false;
        }
    }
    public void tvDiziyeAtma(){
        tvDizisi=new TextField[5][5];
        tvDizisi[0][0]=tv00;
        tvDizisi[0][1]=tv01;
        tvDizisi[0][2]=tv02;
        tvDizisi[0][3]=tv03;
        tvDizisi[0][4]=tv04;
        tvDizisi[1][0]=tv10;
        tvDizisi[1][1]=tv11;
        tvDizisi[1][2]=tv12;
        tvDizisi[1][3]=tv13;
        tvDizisi[1][4]=tv14;
        tvDizisi[2][0]=tv20;
        tvDizisi[2][1]=tv21;
        tvDizisi[2][2]=tv22;
        tvDizisi[2][3]=tv23;
        tvDizisi[2][4]=tv24;
        tvDizisi[3][0]=tv30;
        tvDizisi[3][1]=tv31;
        tvDizisi[3][2]=tv32;
        tvDizisi[3][3]=tv33;
        tvDizisi[3][4]=tv34;
        tvDizisi[4][0]=tv40;
        tvDizisi[4][1]=tv41;
        tvDizisi[4][2]=tv42;
        tvDizisi[4][3]=tv43;
        tvDizisi[4][4]=tv44;
        
    }
    public void tabloSifirla(){
        tv00.setText("0");
        tv01.setText("0");
        tv02.setText("0");
        tv03.setText("0");
        tv04.setText("0");
        tv10.setText("0");
        tv11.setText("0");
        tv12.setText("0");
        tv13.setText("0");
        tv14.setText("0");
        tv20.setText("0");
        tv21.setText("0");
        tv22.setText("0");
        tv23.setText("0");
        tv24.setText("0");
        tv30.setText("0");
        tv31.setText("0");
        tv32.setText("0");
        tv33.setText("0");
        tv34.setText("0");
        tv40.setText("0");
        tv41.setText("0");
        tv42.setText("0");
        tv43.setText("0");
        tv44.setText("0");
    }
    public void radioTut() throws Exception{
        
        //MATRİS BOYUTUNUN SEÇİMİ
            if(rb1.isSelected()){
                rb11.setDisable(true);
                if(rb11.isSelected()){
                    rb12.setSelected(true);
                    
                }
                rb12.setDisable(false);
                rb13.setDisable(false);
                rb14.setDisable(false);
                rb15.setDisable(false);
            }else if(rb2.isSelected()){
                rb11.setDisable(false);
                rb12.setDisable(true);
                if(rb12.isSelected()){
                    rb13.setSelected(true);
                    
                }
                rb13.setDisable(false);
                rb14.setDisable(false);
                rb15.setDisable(false);
            }else if(rb3.isSelected()){
                rb11.setDisable(false);
                rb12.setDisable(false);
                rb13.setDisable(true);
                if(rb13.isSelected()){
                    rb14.setSelected(true);
                    
                }
                rb14.setDisable(false);
                rb15.setDisable(false);
            }else if(rb4.isSelected()){
                rb11.setDisable(false);
                rb12.setDisable(false);
                rb13.setDisable(false);
                rb14.setDisable(true);
                if(rb14.isSelected()){
                    rb15.setSelected(true);
                    
                }
                rb15.setDisable(false);
            }else if(rb5.isSelected()){
                rb11.setDisable(false);
                rb12.setDisable(false);
                rb13.setDisable(false);
                rb14.setDisable(false);
                rb15.setDisable(true);
                if(rb15.isSelected()){
                    rb11.setSelected(true);
                    
                }
            }
            //MATRİX TABLOSUNUN OLUŞTURULMASI
            if(rb1.isSelected()){
                satirBo=1;
                if(rb12.isSelected()){
                    tabloSifirla();
                    sutunBo=2;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(false);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb13.isSelected()){
                    tabloSifirla();
                    sutunBo=3;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(false);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb14.isSelected()){
                    tabloSifirla();
                    sutunBo=4;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(false);
                    tv10.setVisible(false);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb15.isSelected()){
                    tabloSifirla();
                    sutunBo=5;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(true);
                    tv10.setVisible(false);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }
            }else if(rb2.isSelected()){
                satirBo=2;
                if(rb11.isSelected()){
                    tabloSifirla();
                    sutunBo=1;
                    tv00.setVisible(true);
                    tv01.setVisible(false);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb13.isSelected()){
                    tabloSifirla();
                    sutunBo=3;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb14.isSelected()){
                    tabloSifirla();
                    sutunBo=4;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(false);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb15.isSelected()){
                    tabloSifirla();
                    sutunBo=5;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(true);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(true);
                    tv20.setVisible(false);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }
            }else if(rb3.isSelected()){
                satirBo=3;
                if(rb12.isSelected()){
                    tabloSifirla();
                    sutunBo=2;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb11.isSelected()){
                    tabloSifirla();
                    sutunBo=1;
                    tv00.setVisible(true);
                    tv01.setVisible(false);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb14.isSelected()){
                    tabloSifirla();
                    sutunBo=4;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(true);
                    tv24.setVisible(false);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb15.isSelected()){
                    tabloSifirla();
                    sutunBo=5;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(true);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(true);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(true);
                    tv24.setVisible(true);
                    tv30.setVisible(false);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }
            }else if(rb4.isSelected()){
                satirBo=4;
                if(rb12.isSelected()){
                    tabloSifirla();
                    sutunBo=2;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb13.isSelected()){
                    tabloSifirla();
                    sutunBo=3;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(true);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb11.isSelected()){
                    tabloSifirla();
                    sutunBo=1;
                    tv00.setVisible(true);
                    tv01.setVisible(false);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb15.isSelected()){
                    tabloSifirla();
                    sutunBo=5;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(true);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(true);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(true);
                    tv24.setVisible(true);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(true);
                    tv33.setVisible(true);
                    tv34.setVisible(true);
                    tv40.setVisible(false);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }
            }else if(rb5.isSelected()){
                satirBo=5;
                if(rb12.isSelected()){
                    tabloSifirla();
                    sutunBo=2;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(true);
                    tv41.setVisible(true);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb13.isSelected()){
                    tabloSifirla();
                    sutunBo=3;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(true);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(true);
                    tv41.setVisible(true);
                    tv42.setVisible(true);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }else if(rb14.isSelected()){
                    tabloSifirla();
                    sutunBo=4;
                    tv00.setVisible(true);
                    tv01.setVisible(true);
                    tv02.setVisible(true);
                    tv03.setVisible(true);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(true);
                    tv12.setVisible(true);
                    tv13.setVisible(true);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(true);
                    tv22.setVisible(true);
                    tv23.setVisible(true);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(true);
                    tv32.setVisible(true);
                    tv33.setVisible(true);
                    tv34.setVisible(false);
                    tv40.setVisible(true);
                    tv41.setVisible(true);
                    tv42.setVisible(true);
                    tv43.setVisible(true);
                    tv44.setVisible(false);
                }else if(rb11.isSelected()){
                    tabloSifirla();
                    sutunBo=1;
                    tv00.setVisible(true);
                    tv01.setVisible(false);
                    tv02.setVisible(false);
                    tv03.setVisible(false);
                    tv04.setVisible(false);
                    tv10.setVisible(true);
                    tv11.setVisible(false);
                    tv12.setVisible(false);
                    tv13.setVisible(false);
                    tv14.setVisible(false);
                    tv20.setVisible(true);
                    tv21.setVisible(false);
                    tv22.setVisible(false);
                    tv23.setVisible(false);
                    tv24.setVisible(false);
                    tv30.setVisible(true);
                    tv31.setVisible(false);
                    tv32.setVisible(false);
                    tv33.setVisible(false);
                    tv34.setVisible(false);
                    tv40.setVisible(true);
                    tv41.setVisible(false);
                    tv42.setVisible(false);
                    tv43.setVisible(false);
                    tv44.setVisible(false);
                }
            }
        
    }
      
    
}
