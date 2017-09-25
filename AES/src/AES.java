import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
public class AES {
    public static void main(String[] args) throws Exception {
        String FileName = "plaintext.txt"; // ไฟล์ต้นฉบับ
        String FileName1 = "cipher.txt"; // ไฟล์ที่ถูกเข้าหรัส
        String FileName2 = "textout.txt"; // ไฟล์ที่ถูกถอดรหัส
        String FileName3 = "keytext.txt"; // ไฟล์ secret key

        KeyGenerator KeyGen = KeyGenerator.getInstance("AES"); // สร้าง key แบบ AES
        KeyGen.init(128); // Key ขนาด 128 Bit

        SecretKey SecKey = KeyGen.generateKey(); // นำ key มาทำเป็น key secret Key
        byte[] Secretkey = SecKey.getEncoded(); // นำ secret key มาเข้ารหัสเป็น byte
        Files.write(Paths.get(FileName3), Secretkey); // เขียน Secret Key ลงในไฟลื keytext.txt
        
        Cipher AesCipher = Cipher.getInstance("AES"); // เลือกแบบการเข้ารหัสและการถอดรหัส แบบ AES
        
       byte[] byteText = Files.readAllBytes(Paths.get(FileName)); // อ่านไฟลื plaintext.txt 

        AesCipher.init(Cipher.ENCRYPT_MODE, SecKey); // เข้าหรัสด้วย secret key
        byte[] byteCipherText = AesCipher.doFinal(byteText); // เก็บข้อความที่ถูกเข้ารหัสไว้ในตัวแปร byteCipherText
        Files.write(Paths.get(FileName1), byteCipherText); // เขียนไฟล์ที่เข้ารหัสไว้ในไฟล์ cipher.txt


        byte[] cipherText = Files.readAllBytes(Paths.get(FileName1)); // อ่านไฟล์ cipher.txt

        AesCipher.init(Cipher.DECRYPT_MODE, SecKey); //ถอดหรัสด้วย Secret Key
        byte[] bytePlainText = AesCipher.doFinal(cipherText); // เก็บข้อความที่ถูกเข้ารหัสไว้ในตัวแปร
        Files.write(Paths.get(FileName2), bytePlainText); // นำข้อความที่ถูกถอดรหัสจากตัวเปรเก็บไว้ใน textout.txt
    }   
}