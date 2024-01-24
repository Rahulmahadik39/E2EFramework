package excelLabrary;

public class CheckingClass {
	   public static void main(String []args){
		    
           String str=" Student details";
            int count=0;
            char temp;
          String strnewString="";

          for(int i=0;i<str.length();i++){
             temp=str.charAt(i);
         
                 if(temp != ' '){
                 
                  for(int j=str.length()-1; j>=0; j--){
                            char temp1=str.charAt(j);
                     if(temp != temp1){
                           
                              strnewString=strnewString+temp1;
                          }
                                    
             
           }
           

 }
          }
          count=strnewString.length()-1;
          System.out.println(strnewString);
          System.out.println(count);
          
	   }
}
