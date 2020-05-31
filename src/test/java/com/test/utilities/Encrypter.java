package com.test.utilities;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author KRISHNENDU
 * @category Encypter for authentication. Generates encrypted password and
 *           decrypt as well.
 * 
 */

public class Encrypter {

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESED_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;

	public Encrypter() throws Exception {
		myEncryptionKey = "ThisIsSpartaThisIsSparta";
		myEncryptionScheme = DESED_ENCRYPTION_SCHEME;
		arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		ks = new DESedeKeySpec(arrayBytes);
		skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		key = skf.generateSecret(ks);
	}

	public String encrypt(String stringTobeEncrypted) {
		String encryptedString = null;

		try {

			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = stringTobeEncrypted.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {

		String decryptedText = null;

		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);

		} catch (Exception e) {

		}
		return decryptedText;
	}
	
	/* Uncomment below main method to get encrypted value. 'target' value should be the original one which needs to be encrypted */

	
//	 public static void main(String[]arg) throws Exception { 
//		 Encrypter encryption= new Encrypter();
//	  
//	  String target = ""; String encryptedString = encryption.encrypt(target);
//	  String decryptedString = encryption.decrypt(encryptedString);
//	  
//	  System.out.println("Encrypted String: "+encryptedString);
//	  System.out.println("Decrypted String: "+decryptedString); 
//	  
//	 }
	 

}
