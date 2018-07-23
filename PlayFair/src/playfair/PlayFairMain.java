 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfair;

import java.util.Scanner;



/**
 *
 * @author jubel
 */
public class PlayFairMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //TODO code application logic here  
        PlayFairManage tc = new PlayFairManage();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n**** PLAY FAIR *****\n");
        
        while (true) {
            System.out.println("1 : Encryption\n2 : Decryption\n0 : Exit");
            System.out.print("\nSelect your choice : ");
            int choice = 0;
            try{
                choice = sc.nextInt();
            }catch(Exception ex){
                System.out.println(ex);
            }
            
            switch (choice) {
                case 1:
                    {
                        System.out.print("Enter plain text : ");
                        sc.nextLine();
                        String mainPlainText =sc.nextLine();
                        System.out.print("Enter The Key : ");
                        String key =sc.nextLine();
                        System.out.print("Enter Consider Letter ( i / j) : ");
                        char not_ignore =sc.next().charAt(0);
                        System.out.println(mainPlainText + " "+key+" "+not_ignore);
                        String encryptionMessage = tc.getEncryptionText( mainPlainText,key, not_ignore);
                        System.out.println("Encryption Mesaage is : "+encryptionMessage);
                        break;
                    }
                case 2:
                    {
                        System.out.print("Enter cipher text : ");
                        sc.nextLine();
                        String cipherText =sc.nextLine();
                        System.out.print("Enter The Key : ");
                        String key =sc.nextLine();
                        System.out.print("Enter Consider Letter ( i / j) : ");
                        char not_ignore =sc.next().charAt(0);
                        String decryptionMessage = tc.getDecryptionMesage(cipherText, key, not_ignore);
                        System.out.println("Decryption mesaage is : "+decryptionMessage);
                        System.out.println("\n");
                        ;
                        break;
                    }
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("plaese enter the correct input\n");
                    break;
            }
        }
        
        
        
    }
    
   
}
 
 
