/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

public class PlayFairManage {
    
     public String getEncryptionText(String plainText ,String key,char not_ignore){
         
         plainText = getPlainTextCorrection(plainText,not_ignore);
         
         key = getKeyCorrection(key,not_ignore);
         
         String upperText = plainText.toUpperCase();
         
         char upperNotIgnore = Character.toUpperCase(not_ignore);     
        
         char  [] plainTextArray = upperText.toCharArray();      
     
         char [] keyFinalArray = getFinalKey(key,not_ignore);    
        
         char [][] keyBox = new char[5][5];
         int k = 0;
           
         for(int i = 0 ; i< 5 ; i++){
             for(int j= 0 ; j<5 ; j++){
                 keyBox[i][j] = keyFinalArray[k++];
             }
         }
         
         StringBuilder sb = new StringBuilder();
         int [] row = new int[2];
         int [] column = new int[2];
         int rCount = 0;
         int cCount = 0;
         for(int i = 0 ; i<plainTextArray.length ; i++){
             
             for(int r = 0 ; r<5 ;r++){
                 boolean check = false;
                 for(int c = 0 ; c<5 ; c++){
                     if(plainTextArray[i] == keyBox[r][c]){
                        
                         row[rCount++] = r;
                 
                         column[cCount++] = c;
                         check = true;
                         break;
                     }
                 }
                 if(check){
                     break;
                 }
             }
             if(i%2==1){
                 if(row[0] == row[1]){
                     if(column[0]<4){
                         column[0] = column[0]+1;
                     }
                     else{
                         column[0] = 0;
                     }
                     if(column[1]<4){
                         column[1] = column[1]+1;
                     }
                     else{
                         column[1] = 0;
                     }
                    sb.append(keyBox[row[0]][column[0]]);
                    sb.append(keyBox[row[1]][column[1]]);
                 }
                 else if(column[0] == column[1]){
                     if(row[0]<4){
                         row[0] = row[0]+1;
                     }
                     else{
                         row[0] = 0;
                     }
                     if(row[1]<4){
                         row[1] = row[1]+1;
                     }
                     else{
                         row[1] = 0;
                     }
                    sb.append(keyBox[row[0]][column[0]]);
                    sb.append(keyBox[row[1]][column[1]]);
                 }
                 else {
                     sb.append(keyBox[row[0]][column[1]]);
                    sb.append(keyBox[row[1]][column[0]]);
                 }
                 rCount = 0;
                 cCount = 0;
                
             }
         }
         displayResult("Encryption",keyBox,upperText, sb.toString());
         return sb.toString();
     }
     
     public String getDecryptionMesage(String cipherText , String key , char not_ignore){
         
         String upperCipherText = cipherText.toUpperCase();
         
         char [] cipherTextArray = upperCipherText.toCharArray();
         
         key = getKeyCorrection(key, not_ignore);
         
         char [] keyFinalArray = getFinalKey(key,not_ignore);    
         
         char [][] keyBox = new char[5][5];
         
         int k = 0;
           
         for(int i = 0 ; i< 5 ; i++){
             for(int j= 0 ; j<5 ; j++){
                 keyBox[i][j] = keyFinalArray[k++];
             }
         }
               
         
         StringBuilder sb = new StringBuilder();
         
         int [] row = new int[2];
         int [] column = new int[2];
         int rCount = 0;
         int cCount = 0;
         for(int i = 0 ; i<cipherTextArray.length ; i++){
             
             for(int r = 0 ; r<5 ;r++){
                 boolean check = false;
                 for(int c = 0 ; c<5 ; c++){
                     if(cipherTextArray[i] == keyBox[r][c]){
                        
                         row[rCount++] = r;             
                         column[cCount++] = c;
                         check = true;
                         break;
                     }
                 }
                 if(check){
                     break;
                 }
             }
             if(i%2==1){
                 if(row[0] == row[1]){
                     if(column[0]>0){
                         column[0] = column[0]-1;
                     }
                     else{
                         column[0] = 4;
                     }
                     if(column[1]>0){
                         column[1] = column[1]-1;
                     }
                     else{
                         column[1] = 4;
                     }
                    sb.append(keyBox[row[0]][column[0]]);
                    sb.append(keyBox[row[1]][column[1]]);
                  
                 }
                 else if(column[0] == column[1]){
                     if(row[0]>0){
                         row[0] = row[0]-1;
                     }
                     else{
                         row[0] = 4;
                     }
                     if(row[1]>0){
                         row[1] = row[1]-1;
                     }
                     else{
                         row[1] = 4;
                     }
                    sb.append(keyBox[row[0]][column[0]]);
                    sb.append(keyBox[row[1]][column[1]]);                   
                 }
                 else {
                    sb.append(keyBox[row[0]][column[1]]);
                    sb.append(keyBox[row[1]][column[0]]);                
                 }
                 rCount = 0;
                 cCount = 0;
                
             }
         }
          displayResult("Decryption",keyBox, upperCipherText, sb.toString());
         return sb.toString();
     }
  
    private String getPlainTextCorrection(String plainText,char not_ignore){
         
        if(plainText.isEmpty()){
             return "";
        }
        
        String upperPlaintext = plainText.toUpperCase();
        String text = upperPlaintext.replaceAll(" ","");
        
        char upperNotIgnore = Character.toUpperCase(not_ignore);
      
        char [] allText = text.toCharArray();
        
        for(int j = 0 ; j< allText.length ; j++){
             if(upperNotIgnore == 'I'){
                if(allText[j] == 'J'){
                    allText[j] = 'I';
                }
              }else if(upperNotIgnore == 'J'){
                 if(allText[j] == 'I'){
                    allText[j] = 'J';
                }
              }    
        }
        
   
        
        StringBuilder sb = new StringBuilder();
        sb.append(allText[0]);
         
        for(int i = 0 ; i< allText.length-1 ; i++){
                if(allText[i] == allText[i+1]){
                    sb.append('X');
                    sb.append(allText[i+1]);
                    
                }else{
                    sb.append(allText[i+1]);
                }
        }
        int len = sb.length();
        if(len%2==1) sb.append('X');
        return sb.toString();
        
    }
        
     
     private String getKeyCorrection(String key , char not_ignore){
         
         if(key.isEmpty()){
             return "";
         }
         
         String upperKey = key.toUpperCase();
         String text = upperKey.replaceAll(" ", "");
         
         char upperNotIgnore = Character.toUpperCase(not_ignore);
        
         
         char [] keyArray = text.toCharArray();
         
          
        for(int j = 0 ; j< keyArray.length ; j++){
             if(upperNotIgnore == 'I'){
                if(keyArray[j] == 'J'){
                    keyArray[j] = 'I';
                }
              }else if(upperNotIgnore == 'J'){
                 if(keyArray[j] == 'I'){
                    keyArray[j] = 'J';
                }
              }    
        }
        
         
         StringBuilder sb = new StringBuilder();
         sb.append(keyArray[0]);
         
         for(int i = 1 ; i< keyArray.length ; i++){
             if(!hasCharacter(sb.toString().toCharArray(), keyArray[i])){
                 sb.append(keyArray[i]);
             }
         }
         return sb.toString();
         
     }
     
     private boolean hasCharacter(char [] arr , char newChar){
         for(int i =0 ; i< arr.length ; i++){
             if(arr[i]== newChar){
                 return true;
             }
         }
         return false;
     }
     
    
     
     private char[] getFinalKey(String key, char not_ignore){
         String upperKey = key.toUpperCase();
         char upper_not_ignore = Character.toUpperCase(not_ignore);      
         
         char [] keyChar = upperKey.toCharArray();
         
         char [] alphabet = {'A','B','C','D','E','F','G','H',upper_not_ignore,'K','L','M','N','O','P','Q','R','S','T'
         ,'U','V','W','X','Y','Z'};
         
         char [] keyFinalArray = new char[25];
         
         for(int i = 0 ; i<keyChar.length ; i++){
              keyFinalArray[i] = keyChar[i];
               
         }
         
         int finished = 0;
         for(int i = keyChar.length ; i<keyFinalArray.length ; i++){
              for(int j = finished ; j<25 ; j++){
                  if(!hasCharacter(keyChar, alphabet[j])){
                     keyFinalArray[i] = alphabet[j];
                     finished = j+1;                    
                     break;
                  }
              }  
         }
         return keyFinalArray;
     }
     
     public void displayResult(String text, char [][] keyBox,String mainPlainText,String encryptionText){
         
         System.out.println("\n\n********************  "+text+"  ***********************\n");
         
         for(int i = 0 ; i< 5 ; i++){
             for(int j= 0 ; j<5 ; j++){
                 System.out.print(keyBox[i][j]+" ");
             }
             System.out.println();
         }
         
         char [] plainText = mainPlainText.toCharArray();
         char [] cipherText = encryptionText.toCharArray();
         System.out.println("----------------------");
         for(int i = 0 ; i<plainText.length ; i+=2){
             System.out.println(plainText[i]+""+plainText[i+1]+" = "+cipherText[i]+cipherText[i+1]);
         }
         System.out.println("----------------------");
     }
         
}
