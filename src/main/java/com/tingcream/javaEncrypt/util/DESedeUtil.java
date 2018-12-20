package com.tingcream.javaEncrypt.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


public class DESedeUtil {

	 /**
     * 转换成十六进制字符串
     */
    public static byte[] hex(String key){  
        String f = DigestUtils.md5Hex(key);  
        byte[] bkeys = new String(f).getBytes();  
        byte[] enk = new byte[24];  
        for (int i=0;i<24;i++){  
            enk[i] = bkeys[i];  
        }  
        return enk;  
    }
    
    /**
     * 3DES加密
     * @param key 密钥，24位
     * @param text 将加密的字符串
     * @return
     */
    public static String  encode3Des(String key,String text)throws Exception{  
    	byte[] keybyte = hex(key);
    	byte[] src = text.getBytes();
           //生成密钥  
           SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
           //加密  
           Cipher c1 = Cipher.getInstance("DESede");
           c1.init(Cipher.ENCRYPT_MODE, deskey);  
           
          return  Base64.encodeBase64String(c1.doFinal(src));
   }
    
   /**
    * 3DES解密
    * @param key 加密密钥，长度为24位  
    * @param desStr 解密后的字符串
    * @return
    */ 
    public static String decode3Des(String key, String desStr) throws Exception{  
    	Base64 base64 = new Base64();
    	byte[] keybyte = hex(key);
    	byte[] src = base64.decode(desStr);
        
            //生成密钥  
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");  
            //解密  
            Cipher c1 = Cipher.getInstance("DESede");  
            c1.init(Cipher.DECRYPT_MODE, deskey);  
            return  new String(c1.doFinal(src));
                   
    }
 
}
