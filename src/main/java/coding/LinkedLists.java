package coding;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkedLists {

    //Definition for singly-linked list.
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1);
            ListNode prev = dummy,curr = head;
            dummy.next = head;
            int len = 0;
            while(head != null) {
                len++;
                head = head.next;
            }
            head = dummy.next;
            for(int i = 0;i < len/k;i++) {
                for(int j = 0;j < k - 1 ;j++){
                    ListNode nextTemp = curr.next;
                    curr.next = nextTemp.next;
                    nextTemp.next = prev.next;
                    prev.next = nextTemp;
                }
                prev = curr;
                curr = prev.next;
            }
            return dummy.next;
        }
    }


    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            ListNode ret = new Solution().reverseKGroup(head, k);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}


