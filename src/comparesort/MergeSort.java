package comparesort;

/**
 * Mergesort sorts data by splitting the data in two equal-sized parts,
 * recursively sorting each part, and merging the parts together.
 *
 * O(n*log(n)) always. There are no worst cases. This makes mergesort a good
 * choice if you have time constraints.
 *
 * Mergesort doesn't work so well on arrays, because it needs and extra array of
 * the same size, to hold the parts when splitting and merging. This is not a
 * problem with linked lists. The listnodes can easily be reused, which makes
 * mergesort a good choice for sorting linked lists.
 *
 * @author evenal
 */
public class MergeSort extends SortAlgorithm {

    public MergeSort() {
        super("MergeSort");
    }

    @Override
    public void sort() {
        ListNode list = null;
        for (int i = 0; i < getN(); i++) {
            list = new ListNode(get(i), list);
        }
        ListNode sorted = msort(list);
    }

    /**
     * This is where the actual algorithms starts. (The only thing the sort
     * method above does, is to build a linked list out of the arrays used by
     * the other algorithms.
     *
     * @param list
     * @return
     */
    private ListNode msort(ListNode list) {
        if (list == null || list.next == null) {
            return list;
        }
        ListNode[] parts = new ListNode[2];
        split(list, parts);
        parts[0] = msort(parts[0]);
        parts[1] = msort(parts[1]);
        return merge(parts[0], parts[1]);
    }

    /**
     * Splits the list in two (surprise! :-)) it appends the nodes in the
     * unsorted list two empty lists, alternating beween them.
     *
     * @param list
     * @param parts an array of length 2, that will hold the two parts of the
     * split list.
     */
    void split(ListNode list, ListNode[] parts) {
        int p = 1;
        while (list != null) {
            ListNode n = list;
            list = list.next;
            p = p + 1;
            p = p % 2;
            n.next = parts[p];
            parts[p] = n;
        }
    }

    /**
     * This is where the actual sorting happens. After they have been merged the
     * two parts are merged together again, by picking the "smallest" of the two
     * elements at the front of the parts, and appending it to the result list,
     * repeating until one of the parts is empty. The remaining nodes in the
     * other part are all larger than the last node we took from the other part,
     * so they can be safely append to the result.
     *
     * @param l1 one sorted part
     * @param l2 the other sorted part
     * @return
     */
    ListNode merge(ListNode l1, ListNode l2) {
        ListNode resultat = new ListNode(); // the sorted list
        ListNode last = resultat;
        ListNode n = null; // the  current node (the one we are handling now)
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) { // while both parts are non-empty
                counters.incCompares();    // their first items are  compared
                n = l1;
                l1 = l1.next;
            } else {
                n = l2;
                l2 = l2.next;
            }
            last.next = n;              // and appended to the output
            last = n;
        }

        if (l1 != null) // append the remaining values
        {
            last.next = l1;            // that will always be left in one part
        }
        if (l2 != null) {
            last.next = l2;
        }
        return resultat.next;
    }

    private class ListNode {

        ListNode next;
        int value;

        public ListNode() {
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public double expectedCompares(int n) {
        return n * Math.log(n);
    }

    @Override
    public double expectedSwaps(int n) {
        return n * Math.log(n);
    }

    @Override
    public double expectedMoves(int n) {
        return n * Math.log(n);
    }
}
