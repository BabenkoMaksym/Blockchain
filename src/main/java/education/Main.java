package education;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {

    private static final ArrayList<Block> BLOCKCHAIN = new ArrayList<>();
    private final static int DIFFICULTY = 5;


    public static void main(String[] args) {
        Miner miner = new Miner(DIFFICULTY);

        // 1. Create transaction
        String firstTransaction = "Thomas pays Lucy 5 CC";
        // 2. Miner listens to this transaction and mines block
        Block firstBlock = miner.mineBlock(firstTransaction, "0");
        // 3. Block is added to the blockchain
        BLOCKCHAIN.add(firstBlock);

        // 1. Create transaction
        String secondTransaction = "John pays Paul 2 CC";
        // 2. Miner listens to this transaction and mines block
        Block secondBlock = miner.mineBlock(secondTransaction, firstBlock.getHash());
        // 3. Block is added to the blockchain
        BLOCKCHAIN.add(secondBlock);

        // 1. Create transaction
        String thirdTransaction = "Paul pays Thomas 4 CC";
        // 2. Miner listens to this transaction and mines block
        Block thirdBlock = miner.mineBlock(thirdTransaction, secondBlock.getHash());
        // 3. Block is added to the blockchain
        BLOCKCHAIN.add(thirdBlock);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(BLOCKCHAIN);
        System.out.println("Blockchain:");
        System.out.println(blockchainJson);
        System.out.println("Is our blockchain valid?: " + isBlockchainValid());
    }

    private static boolean isBlockchainValid() {
        if (!BLOCKCHAIN.isEmpty()) {
            for (int i = 0; i < BLOCKCHAIN.size(); i++) {
                Block block = BLOCKCHAIN.get(i);

                String expectedHash = Hasher.calculateHash(block.getPrevHash(), block.getTransaction(), block.getNonce());
                if (!expectedHash.equals(block.getHash())) {
                    System.out.println("Block has invalid hash");
                    return false;
                }

                String hashTarget = new String(new char[DIFFICULTY]).replace("\0", "0");
                if (!block.getHash().substring(0, DIFFICULTY).equals(hashTarget)) {
                    System.out.println("Block wasn't mined");
                    return false;
                }

                if (i > 0) {
                    Block previousBlock = BLOCKCHAIN.get(i - 1);
                    // Previous hash is equal to actual previous hash
                    if (!block.getPrevHash().equals(previousBlock.getHash())) {
                        System.out.println("Block has invalid previous hash");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Empty blockchain");
            return true;
        }
        return true;
    }
}