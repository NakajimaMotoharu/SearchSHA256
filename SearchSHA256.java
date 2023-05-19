import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SearchSHA256 {
	public static String searchSHA256(String ans){
		boolean frag = true;
		String result = "Default";

		for (int i = 1; frag; i = i + 1){
			result = searchSHA256(ans, i);
			if (result != null){
				break;
			}
			System.out.println("Depth: " + i);
		}

		return result;
	}

	public static String searchSHA256(String ans, int n){
		long message = 0;
		boolean frag = true;

		for (message = 0; frag && (message <= (long) Math.pow(10, n)); message = message + 1){
			String msg = String.format("%0" + n +"d", message);
			try {
				MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
				byte[] hash = sha256.digest(msg.getBytes());

				String result = byte2str(hash);
				if (ans.equals(result)){
					frag = false;
				}
			} catch (NoSuchAlgorithmException e){
				e.printStackTrace();
			}
		}

		if (message > (int)Math.pow(10, n)){
			return null;
		} else {
			return String.format("%0" + n +"d", message - 1);
		}
	}

	private static String byte2str(byte[] input){
		String ans = "", buf = "", sup = "";

		for (int i = 0; i < input.length; i = i + 1){
			int num = input[i] & 0xff;
			if (num < 16){
				sup = "0" + Integer.toHexString(num);
			} else {
				sup = Integer.toHexString(num);
			}

			buf = ans + sup;
			ans = buf;
		}

		return ans;
	}
}
