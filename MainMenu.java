package Interface;
import java.awt.Image;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class MainMenu extends javax.swing.JFrame {
    String hasilOcr, matchIngredients;
    String[] inputIng, arrayIng;
    boolean match = false;
    ArrayList<String> matchIng = new ArrayList<>();
    public MainMenu() {
        initComponents();
        setLocationRelativeTo(this);          
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableListIngredients = new javax.swing.JTable();
        ButtonInsert = new javax.swing.JButton();
        detect = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        ShowCI = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableListIngredients.setFont(new java.awt.Font("Gill Sans MT", 0, 11)); // NOI18N
        TableListIngredients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Indredients"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableListIngredients);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 148, 160));

        ButtonInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/iNSERT.png"))); // NOI18N
        ButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonInsertActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 73, 23));

        detect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Detect.png"))); // NOI18N
        detect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectActionPerformed(evt);
            }
        });
        getContentPane().add(detect, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 120, 40));

        Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Plus.png"))); // NOI18N
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        getContentPane().add(Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 30, 30));

        ShowCI.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 2, true));
        getContentPane().add(ShowCI, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 200, 140));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Slide 4_3 - 1.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonInsertActionPerformed
        Tesseract tesseract = new Tesseract();
        try{
            tesseract.setDatapath("C:\\Users\\laudz\\OneDrive\\Documents\\NetBeansProjects\\lib\\Tess4J");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose an image that contains your food ingredients");
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File chooseFile = fileChooser.getSelectedFile();
                String result = tesseract.doOCR(chooseFile);
                ImageIcon displayIcon = new ImageIcon(chooseFile.getAbsolutePath());
                Image image = displayIcon.getImage();
                Image scaledImage = image.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
                ShowCI.setIcon(new ImageIcon(scaledImage));
                System.out.println(result);
                hasilOcr = result.trim();
                arrayIng = hasilOcr.split(",");
                System.out.println(Arrays.toString(arrayIng));
            }
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_ButtonInsertActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        String ingredients = JOptionPane.showInputDialog("Input avoid ingredients (seperate with commas [,] )");
        String[] dataIngre = ingredients.split(",");
        
        DefaultTableModel model = (DefaultTableModel)TableListIngredients.getModel();
        for (String in : dataIngre){
            model.addRow(new Object[] {in.trim()});
        }
        inputIng = dataIngre;
    }//GEN-LAST:event_AddActionPerformed

    private void detectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detectActionPerformed
        for (String det : arrayIng) {
            for (String dete : inputIng) {
                if (det.trim().equalsIgnoreCase(dete.trim())) {
                    match = true;
                    matchIng.add(dete.trim());
                    matchIngredients = String.join(" , ", matchIng);
                }
            }
        } 
        if (match) {
            JOptionPane.showMessageDialog(this, "               WARNING!!! \n Avoid Ingredients detected: \n " +matchIngredients);            
            JOptionPane.showMessageDialog(this, "               CONCLUDE \n Product NOT SAFE To Consume \n Because it contains: \n \t"+matchIngredients);            
        } else {
            JOptionPane.showMessageDialog(this, "Your Product Is Safe To Consume");            
        }
    }//GEN-LAST:event_detectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JLabel Background;
    private javax.swing.JButton ButtonInsert;
    private javax.swing.JLabel ShowCI;
    private javax.swing.JTable TableListIngredients;
    private javax.swing.JButton detect;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}