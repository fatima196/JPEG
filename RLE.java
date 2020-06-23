
package jpeg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import sun.misc.FloatingDecimal;


public class RLE {
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 //System.out.println(ones(Integer.toBinaryString(2)));
		
	
		
		// System.out.println("Enter Text splitted by coma :");
		// Scanner TextTemp = new Scanner (System.in);
			// String text = TextTemp.nextLine();
			 
			// String[] letters=text.split(",");
			// System.out.println(letters.length);
			 
			/*int[]arr=new int [text.length()];
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
					
					additionalBits.add(get1c(bin));
					
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
			System.out.print(obj.n2c.get("eob"));
			 
                         FileWriter file=new FileWriter("D:\\compress");
                        file.append(comp);
                        file.close();
                        
                        //System.out.println("");
                        HashMap<String,String>res =HuffmanReverse();
			System.out.println(res);
                        String out = "";
                        String temp = "";
                        
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
                                    a=get1c(a);
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
                      /*  j+=cat;
                             
                        out += B2D;
                              
                        temp = "";
                    }
                     else j++;
        }
        System.out.println("The Decompressed code is "+out);
         FileWriter defile=new FileWriter("D:\\decompress");
                        defile.append(out);
                        defile.close();*/
			
                        JPEGJFrame j=new JPEGJFrame();
                        j.setVisible(true);
			
	}
		
			
static HashMap <String, String> HuffmanReverse() {
        HashMap <String, String> res = new HashMap <>();
        for (Map.Entry <String, String> entry :Huffman.n2c.entrySet()) {
            res.put(entry.getValue(), entry.getKey());
        }

        return res;
    }
	

	static int getCategory(int n)
	{ int i;
		for( i =1;i<11;i++)
		{
			int maxRange=(int) (Math.pow(2, i))-1;
			int lowRange=(int) (Math.pow(2, i-1))-1;
			 Vector<Integer> v = new Vector<>();
			 v.add(maxRange);
			 while(true)
			 {
				 v.add(maxRange--);
				 if(maxRange==lowRange)
				 {
					 break;
				 }
			 }
			 if(v.contains(n))
			 {
				 break;
			 }
			
			
		}
		return i;
	}

	   
	
	 public static String get1c(String binary) {
		  
		  StringBuffer sbuf = new StringBuffer();
		  for(int n=0;n<binary.length();n++) {
		   
		   char c = binary.charAt(n)=='1' ? '0' : '1';
		   sbuf.append(c);   
		  }
		  return sbuf.toString();       
		 }
	   

}

