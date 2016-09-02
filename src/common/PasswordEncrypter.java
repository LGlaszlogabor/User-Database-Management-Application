package common;

import java.io.UnsupportedEncodingException; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

public final class PasswordEncrypter {
	private static final Logger LOG = LoggerFactory.getLogger (PasswordEncrypter.class);
	public static String generateHashedPassword (final String password, final String salt) { 
		String hashedPassword = ""; 
		byte[] initialBytes;
		try { 
			initialBytes = (password + salt).getBytes (PropertyProvider.INSTANCE.getProperty ("passwordEncoding"));
			final MessageDigest algorithm = MessageDigest.getInstance (PropertyProvider.INSTANCE .getProperty ("passwordHashingAlgorithm")); 
			algorithm.reset ();
			algorithm.update (initialBytes);
			final byte[] hashedBytes = algorithm.digest (); 
			//Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< hashedBytes.length ;i++)
            {
                sb.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashedPassword = sb.toString(); 
        } 
		catch (final UnsupportedEncodingException e) { 
			LOG.error ("Password encryption failed: unsupported encoding", e); 
		} catch (final NoSuchAlgorithmException nsae) { 
			LOG.error ("Password encryption failed: hashing algorithm not supported", nsae); 
		}
		return hashedPassword;
	}
}
