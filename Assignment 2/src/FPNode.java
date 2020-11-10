import java.util.ArrayList;
import java.util.Collections;

public class FPNode {
    int freq;
    String item = null;
    FPNode head = null;
    ArrayList<FPNode> children = new ArrayList();
    static public ArrayList<FPNode> headLinks = new ArrayList<>();

    FPNode(String item) {
        this.item = item;
        if (item == null) {
            freq = 0;
        } else {
            freq = 1;
        }
    }

    ArrayList<FPNode> getHeadLinks(){
        if(head == null) {
            headLinks.add(head);
            Collections.reverse(headLinks);
            return headLinks;
        }
        else {
            headLinks.add(this);
            return head.getHeadLinks();

        }
    }

    @Override
    public String toString() {
        return "("+item + "|" + freq+")";
    }
}
