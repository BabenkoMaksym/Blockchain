package education;

public class Block {
    private final String hash;
    private final String prevHash;
    private final String transaction;
    private final long nonce;

    public Block(String transaction, String prevHash, String hash, long nonce) {
        this.transaction = transaction;
        this.prevHash = prevHash;
        this.hash = hash;
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getTransaction() {
        return transaction;
    }

    public long getNonce() {
        return nonce;
    }
}
