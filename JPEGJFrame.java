/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpeg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jpeg.RLE.HuffmanReverse;
import static jpeg.RLE.getCategory;

/**
 *
 * @author lenovo
 */
public class JPEGJFrame extends javax.swing.JFrame {

    /**
     * Creates new form JPEGJFrame
     */
    public JPEGJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTcomp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTdecomp = new javax.swing.JTextField();
        jBcomp = new javax.swing.JButton();
        jBdecomp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter text to compress");

        jLabel2.setText("Enter text to decompress");

        jTdecomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTdecompActionPerformed(evt);
            }
        });

        jBcomp.setText("compress");
        jBcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcompActionPerformed(evt);
            }
        });

        jBdecomp.setText("decompress");
        jBdecomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdecompActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBcomp)
                        .addGap(94, 94, 94)
                        .addComponent(jBdecomp))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jTdecomp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(35, 35, 35)
                            .addComponent(jTcomp))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTdecomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcomp)
                    .addComponent(jBdecomp))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTdecompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTdecompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTdecompActionPerformed

    private void jBcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcompActionPerformed
        // TODO add your handling code here:
        String text=jTcomp.getText();
         String[] letters=text.split(",");
			// System.out.println(letters.length);
			 
			int[]arr=new int [text.length()];
			for(int i =0;i<letters.length;i++)
			{
				arr[i]=Integer.parseInt(letters[i]);
			}
		
		
			
			Vector<Integer> zero=new Vector();
			Vector<Integer> arrnozero=new Vector();
			int counter=0;
			for(int i =0;i<letters.length;i++)
			{
			if(arr[i]!=0)
			{   arrnozero.add(arr[i]);
				zero.add(counter);
				counter=0;
			}
			else
                        {counter++;}}
			
			//HashMap<Integer,Integer> value=new HashMap<>();
			Vector<Integer> categories=new Vector();
			for(Integer v:arrnozero)
			{  
                            
				 int c=Math.abs(v);
				 categories.add(getCategory(c));
                                 //value.put(getCategory(c),v);
			}
			Vector<String> additionalBits=new Vector();
		          //System.out.println(value);
			for(int  i =0;i<arrnozero.size();i++)
			{
				   String bin ="";
				
				if(arrnozero.get(i)<0)
				{ bin=Integer.toBinaryString(Math.abs(arrnozero.get(i)));
					
					additionalBits.add(RLE.get1c(bin));
					
				}
				else
				{ bin=Integer.toBinaryString(arrnozero.get(i));
					 additionalBits.add(bin);
				}	
			}
			
			String [] tags=new String[zero.size()];
			for(int i =0;i<zero.size();i++)
			{
				String s =Integer.toString(zero.get(i))+"/"+Integer.toString(categories.get(i));
				tags[i]=s;
			}
			
			Vector<String> uniqueTags=new Vector();
			for(int i =0;i<tags.length;i++)
			{
				if(!uniqueTags.contains(tags[i]))
				{
					uniqueTags.add(tags[i]);
				}
			}
                       
			// System.out.println(uniqueTags);
			 int lettersCounter=0;
			int[] probability=new int[uniqueTags.size()+1];
			 for(int i =0;i<uniqueTags.size();i++)
			 {
				 String s=uniqueTags.get(i);
			
				 for(int j =0;j<tags.length;j++)
				 {
					 if(s.equals(tags[j]))
					 {  
						 lettersCounter+=1;
					 }
					 
				 }
				 probability[i]=lettersCounter;
				 lettersCounter=0;
				 
			 }
                         
			 String[] uniqueArray=uniqueTags.toArray(new String[uniqueTags.size()] );
			  String[] uniqueArrayeob=new String[ uniqueArray.length+1];
			  for(int i =0;i<uniqueArray.length;i++)
			  {
				  uniqueArrayeob[i]=uniqueArray[i];
			  }
			  
			  uniqueArrayeob[uniqueArrayeob.length-1]="eob";
			
			  
			  int [] newP=new int[probability.length+1];
			  for(int i =0;i<probability.length;i++)
			  {
				  newP[i]=  probability[i];  
			  }
			  newP[newP.length-1]=1;

			  Huffman obj=new Huffman();
			obj.getHufmanCode(uniqueArrayeob, newP); 
                        
                        String comp="";
			for(int  i =0;i<tags.length;i++)
			{
				//System.out.print(obj.n2c.get(tags[i]));
                                comp+=obj.n2c.get(tags[i]);
				//System.out.print(additionalBits.get(i));
                                comp+=additionalBits.get(i);
			}
			comp+=obj.n2c.get("eob");
			//System.out.print(obj.n2c.get("eob"));
			 
                         FileWriter file = null;
        try {
            file = new FileWriter("D:\\compress.txt");
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.append(comp);
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
                        
        
        
        
    }//GEN-LAST:event_jBcompActionPerformed

    private void jBdecompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdecompActionPerformed
        // TODO add your handling code here:
        
         HashMap<String,String>res =HuffmanReverse();
			System.out.println(res);
                        String out = "";
                        String temp = "";
                        
                        File file =new File("D:\\compress.txt");
                        Scanner outFile = null;
       
        try {
            outFile = new Scanner (file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                        String comp=outFile.nextLine();
                        
                        for (int j = 0; j <comp.length()+2;) {
                            temp += comp.charAt(j);
                            if (res.containsKey(temp)) {
                                
                                if (res.get(temp).equals("eob") ){
                                out += "eob";
                                break;
                            }
                                String[] t = res.get(temp).split("/");
                               
                            
                        
                        for (int y=0;y<Integer.parseInt(t[0]);y++)
                            out+="0";
                        
                        int cat = Integer.parseInt(t[1]);
                        j++;
                        String a=comp.substring(j,j+cat);

                        int B2D;

                        if (a.charAt(0)=='0') {
                                    a=RLE.get1c(a);
                                    B2D =-1*(Integer.parseInt(a,2));
                                }
                                else
                                {B2D=Integer.parseInt(a,2);}
                       /* if (j!=comp.length())
                        for (int o=size;o<size+cat;o++)str+=comp.charAt(o);
                                System.out.println("str="+str);
                                if (str.charAt(0)=='0') {
                                    str=get1c(str);
                                    System.out.println("str2="+str);
                                   B2D =-1*(Integer.parseInt(str,2));
                                    System.out.println("B2D-->"+B2D);
                                }
                                else
                                {B2D=Integer.parseInt(str,2);
                                System.out.println("B2D-->"+B2D);}
                                
                               //str=FloatingDecimal.BinaryToASCIIConverter(str);
                         */     // System.out.println("str-->"+str);
                        j+=cat;
                             
                        out += B2D;
                              
                        temp = "";
                    }
                     else j++;
        }
                        jTdecomp.setText(out);
        System.out.println("The Decompressed code is "+out);
         FileWriter defile = null;
        try {
            defile = new FileWriter("D:\\decompress.txt");
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            defile.append(out);
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            defile.close();
        } catch (IOException ex) {
            Logger.getLogger(JPEGJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jBdecompActionPerformed

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
            java.util.logging.Logger.getLogger(JPEGJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JPEGJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JPEGJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JPEGJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JPEGJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcomp;
    private javax.swing.JButton jBdecomp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTcomp;
    private javax.swing.JTextField jTdecomp;
    // End of variables declaration//GEN-END:variables
}
