package education;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Hasher {
    static String calculateHash(String prevHash, String transaction, long nonce) {
        final String toHash = prevHash + transaction + nonce;
        return Hashing.sha256().hashString(toHash, StandardCharsets.UTF_8).toString();
    }
}
