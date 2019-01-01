/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrisislem;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kadir Han
 */
public class OtomatikIslemlerController implements Initializable {

    private String yazilacakIlkMatris = "";
    private String islemYazilacak = "";
    //int satirBoyut=2, sutunBoyut=4;
    // double[][] matrisimiz=new double[satirBoyut][sutunBoyut];
    static int sumcounter = 0;
    static int prodcounter = 0;

    @FXML
    private TextArea ilkmatris;
    @FXML
    private TextArea islemler;
    @FXML
    private Label label;
    @FXML
    private Button randomButton;
    //@FXML private Button buttonBaslat;

    public OtomatikIslemlerController() {
        System.out.println("constur");

    }

    public void randomButtonHandler() {
        randomMatrisIslemler();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initi");
        randomMatrisIslemler();

    }

    public void randomMatrisIslemler(){

        yazilacakIlkMatris = "";
        islemYazilacak = "";
        ilkmatris.setText(yazilacakIlkMatris);
        islemler.setText(islemYazilacak);

        int rowSize, columnSize;
        double[][] ilkMatris;
        Random r = new Random();

        do {
            rowSize = r.nextInt(5) + 1;
            columnSize = r.nextInt(5) + 1;
        } while (rowSize == columnSize);
        
        ilkMatris = new double[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                String randDegerString = new DecimalFormat("##.#").format(r.nextDouble() * 8 + 1);
                double randDeger = Double.valueOf(randDegerString);
                ilkMatris[i][j] = randDeger;
                yazilacakIlkMatris += ilkMatris[i][j] + "  ";
            }
            System.out.println("");
            yazilacakIlkMatris += "\n";
        }

        ilkmatris.setText(yazilacakIlkMatris);

        System.out.println("Matris (A)");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(ilkMatris[i][j] + " ");
            }
            System.out.println("");
        }
        islemYazilacak += "Transpoz (A)': \n";
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
            islemYazilacak += "\nMatris_Transpoz X Matris (A^T * A) : \n";
            matrisCarpimi = matrisCarpımFonk(matristranpose, ilkMatris);
        } else {
            System.out.println("\nMatris X Matris_Transpoz (A * A^T) ");
            islemYazilacak += "\nMatris X Matris_Transpoz (A * A^T) : \n";
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
            islemYazilacak += "\nOluşan Matrisin Tersi (A^T * A)^-1\n ";
        } else {
            System.out.println("\nOluşan Matrisin Tersi (A * A^T)^-1 ");
            islemYazilacak += "\nOluşan Matrisin Tersi (A * A^T)^-1 \n ";
        }
        islemler.setText(islemYazilacak);
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
                islemYazilacak += matrisSonucString[i][j] + "   ";
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
    public boolean isDouble(String deg) {
        
        try{
            double d=Double.parseDouble(deg);
            return true;
            
        }catch(Exception e){
            return false;
        }
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

    public void onclick(ActionEvent event) throws Exception {

        Parent layout = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));

        Scene gris = new Scene(layout);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(gris);
        window.show();
    }

}
