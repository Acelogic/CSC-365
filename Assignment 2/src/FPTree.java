import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FPTree {
    
    private TransactionCSVParser csvParser;
    private LinkedHashMap<String, Integer> freqItemSet;
    private LinkedHashMap<String, Integer> freqItemSetReversed;
    private ArrayList<Transaction> transactionsList;
    private TreeSet<String> itemSet;
    private LinkedHashMap<String, ArrayList<FPNode>> uniquePaths;
    private FPNode tree;

    public FPTree(int supportThreshold) throws IOException {
        csvParser = new TransactionCSVParser();
        transactionsList = csvParser.getTransactionList();
        itemSet = csvParser.getItemSet();
        freqItemSetReversed = new LinkedHashMap<>();

        buildFreqItemSet(supportThreshold);
        reOrderedItemSet();
        uniquePaths = new LinkedHashMap<>();
        tree = new FPNode(null);
    }

    public void populateTree() {
        //Deep copy transaction list to new Array
        ArrayList<Transaction> transactionListCopy = new ArrayList<>();
        for (Transaction tr : transactionsList) {
            transactionListCopy.add((Transaction) tr.clone());
        }
        for (Transaction transaction : transactionListCopy) {
            addEntity(this.getHead(), transaction);
        }
    }

    private boolean addEntity(FPNode loci, ArrayList<String> transaction) {
        if (transaction.isEmpty()) {
            return true;
        }

        String insertWord = transaction.get(0);
        transaction.remove(0);

        FPNode child = null;
        for (FPNode x : loci.children) {
            if (x.item.equals(insertWord)) {
                child = x;
                break;
            }
        }
        if (child == null) {
            child = new FPNode(insertWord);
            child.head = loci;
            loci.children.add(child);
            ArrayList<FPNode> set = uniquePaths.get(insertWord);
            if (set == null) {
                set = new ArrayList();
            }
            set.add(child);
            uniquePaths.put(insertWord, set);
        } else {
            child.freq++;
        }

        return addEntity(child, transaction);
    }


    public FPNode getHead() {
        return tree;
    }

    // A count of how much of each item is in each transaction (X being items, Y being support count)
    private void buildFreqItemSet(int supportThreshold) throws IOException {
        TreeMap<String, Integer> tempSupportCountList = new TreeMap<>();

        // Build Unpruned freq item sets with support counts
        for (Transaction tr : transactionsList) {
            for (String item : itemSet) {
                if (tr.contains(item) && !tempSupportCountList.containsKey(item)) {
                    tempSupportCountList.put(item, 1);
                } else if (tr.contains(item) && tempSupportCountList.containsKey(item)) {
                    tempSupportCountList.put(item, tempSupportCountList.get(item) + 1);
                }
            }
        }

        // Prune Items below min support count
        for (String key : List.copyOf(tempSupportCountList.keySet())) {
            if (tempSupportCountList.get(key) < supportThreshold) {
                tempSupportCountList.remove(key);
            }
        }
        // Sorting keys based on value and reinserting them into a linkedhashmap to maintain insertion order
        // the .keySet() and .values() would be in order already when called
        freqItemSet = tempSupportCountList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void reOrderedItemSet() {
        for (Transaction transaction : transactionsList) {

            // Pruning All Transactions of items that didn't meet support min
            transaction.removeIf(item -> !freqItemSet.containsKey(item));

            // Convert Transaction Item list into LinkedHashMap, with values corresponding to freq item set
            LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();
            for (String item : transaction) {
                temp.put(item, freqItemSet.get(item));
            }

            // Sorting Items By Value rather than key (Descending order)
            LinkedHashMap<String, Integer> sortedTemp = temp.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            // Wipes Unsorted item list
            transaction.clear();

            // Populates transaction item list based on sorted keyset
            transaction.addAll(sortedTemp.keySet());

        }

        // Clean up any empty transactions due to pruning
        for (Transaction transaction : List.copyOf(transactionsList)) {
            if (transaction.size() == 0) {
                transactionsList.remove(transaction);
            }
        }
    }

    public LinkedHashMap<String, Integer> getFreqItemSet() {
        return freqItemSet;
    }


    public LinkedHashMap<String, Integer> getFreqItemSetReversed() {
        List<String> list = new ArrayList<>(freqItemSet.keySet());
        Collections.reverse(list);
        for (String key : list) {
            freqItemSetReversed.put(key, freqItemSet.get(key));
        }

        return freqItemSetReversed;

    }

    public void getConditionalPatternBase() {
        for (String key : freqItemSetReversed.keySet()) {
            int x = 0;
            System.out.println("Item: " + key + " Support: " + freqItemSetReversed.get(key));
            System.out.println("=============================================================");
            for (FPNode leaf : uniquePaths.get(key)) {
                System.out.println("Num:" + x + " (" + key + "|" + leaf.freq + ")" + "-> " + leaf.getHeadLinks());
                leaf.getHeadLinks().clear();
                x++;
            }
            System.out.println();
        }

    }
}
