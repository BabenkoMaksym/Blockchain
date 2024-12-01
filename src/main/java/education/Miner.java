package education;

public class Miner {
    private final int difficulty;
    private final String target;

    Miner(int difficulty) {
        this.difficulty = difficulty;
        this.target = new String(new char[difficulty]).replace('\0', '0');
    }

    Block mineBlock(String transaction, String prevHash) {
        System.out.println("Mining block...");

        long nonce = 0;
        String hash = Hasher.calculateHash(prevHash, transaction, nonce);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = Hasher.calculateHash(prevHash, transaction, nonce);
        }
        System.out.println("Yuhuu. I mined a block at " + nonce + " attempt! Hash: " + hash);
        System.out.println();
        return new Block(transaction, prevHash, hash, nonce);
    }
}
