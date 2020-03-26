package coding;

//Definition for singly-linked list.
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
    }
}

public class LinkedLists {

    public ListNode imergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = l1;
        //递归终止条件
        while(l2.next != null){
            if(l1.val <= l2.val){
                l1 = l1.next;
            }else{
                ListNode temp = new ListNode(l2.val);
                l1.next = temp;
                temp.next = l1.next.next;
            }
        }
        return head;
    }

    public static void main(String... args){

    }

}


