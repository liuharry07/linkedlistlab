// Libraries For Additional Credit Tester - not used in exercises
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Lab {
    public static void main(String[] args) {
        tester();
    }

    public static int size(ListNode<String> list) {
        int total = 0;
        while(list != null) {
            list = list.getNext();
            ++total;
        }
        return total;
    }

    public static boolean contains(ListNode<String> list, String str) {
        boolean x = false;
        while(list != null) {
            if(list.getValue().equals(str)) {
                x = true;
            }
            list = list.getNext();
        }
        return x;
    }

    public static boolean add(ListNode<String> list, String str) {
        if(!contains(list, str)) {
            while(list.getNext() != null) {
                list = list.getNext();
            }
            list.setNext(new ListNode<String>(str, null));
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean remove(ListNode<String> list, String str) {
        if(contains(list, str)) {
            while(!list.getNext().getValue().equals(str)) {
                list = list.getNext();
            }
            list.setNext(list.getNext().getNext());
            return true;
        }
        else {
            return false;
        }
    }

    public static ListNode<String> toList(String[] array) {
        ListNode<String> result = null;
        for(int i = array.length - 1; i >= 0; --i) {
            ListNode<String> temp = new ListNode<String>(array[i], result);
            result = temp;
        }
        return result;
    }

    // Additional Credit
    public static <E> boolean hasDuplicate(ListNode<E> list) {
        int size = 0;
        ListNode<E> copy = list;
        while(copy != null) {
            copy = copy.getNext();
            ++size;
        }
        for(int i = 0; i < size; ++i) {
            copy = list;
            for(int j = i; j < size - 1; ++j) {
                copy = copy.getNext();
                if(copy.getValue() == list.getValue()) {
                    return true;
                }
            }
            list = list.getNext();
        }
        return false;
    }

    public static void testHasDuplicate() {
        String[][] tests = {{}, {"A"}, {"A", "A"}, {"A", "B"}, {"A", "B", "C", "D", "E"},
                {"A", "B", "C", "D", "C"}, {"A", "A", "C", "D", "E"}};
        // Expected Results: false, false, true, false, false, true, true
        System.out.println("\ntestHasDuplicate: ");
        for(String[] i : tests) {
            System.out.print(Arrays.toString(i));
            System.out.println(" - " + hasDuplicate(toList(i)));
        }
    }

    public static ListNode<Integer> insert(ListNode<Integer> list, int value) {
        //fix if null
        if(list == null) {
            return list;
        }
        if(list.getValue() < value && list.getNext() != null) {
            insert(list.getNext(), value);
        }
        else {
            if(list.getValue() >= value) {
                int temp = list.getValue();
                list.setValue(value);
                list.setNext(new ListNode<Integer>(temp, list.getNext()));
            }
            else {
                list.setNext(new ListNode<Integer>(value, list.getNext()));
            }
        }
        return list;
    }

    public static void testInsert() {
        int[][] tests = {{1, 2, 3, 4, 5}, {1, 1, 5, 5, 9, 9}, {9, 10, 11, 12, 13},
                {1, 1, 7, 7, 8, 8, 9, 9}, {}};
        System.out.println("\ntestInsert: ");
        /*
        Expected Results: [ 1 | ] -> [ 2 | ] -> [ 3 | ] -> [ 4 | ] -> [ 5 | ] -> [ 8 | null ]
        [ 1 | ] -> [ 1 | ] -> [ 5 | ] -> [ 5 | ] -> [ 8 | ] -> [ 9 | ] -> [ 9 | null ]
        [ 8 | ] -> [ 9 | ] -> [ 10 | ] -> [ 11 | ] -> [ 12 | ] -> [ 13 | null ]
        [ 1 | ] -> [ 1 | ] -> [ 7 | ] -> [ 7 | ] -> [ 8 | ] -> [ 8 | ] -> [ 8 | ] -> [ 9 | ] -> [ 9 | null ]
         */
        int value = 8;
        for(int[] i : tests) {
            ListNode<Integer> result = null;
            for(int j = i.length - 1; j >= 0; --j) {
                ListNode<Integer> temp = new ListNode<Integer>(i[j], result);
                result = temp;
            }
            System.out.print("original listNode - ");
            printListNode(result);
            System.out.print("insert " + value + " - ");
            printListNode(insert(result, value));
        }
    }

    public static ListNode<Integer> sort(ListNode<Integer> list) {
        int size = 0;
        if(list == null) 
            return list;
        ListNode<Integer> one = list;
        ListNode<Integer> two = list.getNext();
        while(one != null) {
            one = one.getNext();
            ++size;
        }
        one = list;
        for(int i = 0; i < size - 1; ++i) {
            two = one.getNext();
            while(two != null) {
                if(two.getValue() < one.getValue()) {
                    int temp = one.getValue();
                    one.setValue(two.getValue());
                    one.getNext().setValue(temp);
                }
                one = one.getNext();
                two = one.getNext();
            }
            one = list;
        }
        return list;
    }

    public static void testSort() {
        int[][] tests = {{}, {2, 1, 5, 4, 3}, {1, 2, 3, 4, 5}, {6, 3, 7, 3, 4, 3, 1}};
        System.out.println("\ntestSort: ");
        /*
        Expected Results: [ 1 | ] -> [ 2 | ] -> [ 3 | ] -> [ 4 | ] -> [ 5 | null ]
        [ 1 | ] -> [ 2 | ] -> [ 3 | ] -> [ 4 | ] -> [ 5 | null ]
        [ 1 | ] -> [ 3 | ] -> [ 3 | ] -> [ 3 | ] -> [ 4 | ] -> [ 6 | ] -> [ 7 | null ]
         */
        for(int[] i : tests) {
            ListNode<Integer> result = null;
            for(int j = i.length - 1; j >= 0; --j) {
                ListNode<Integer> temp = new ListNode<Integer>(i[j], result);
                result = temp;
            }
            System.out.print("original listNode - ");
            printListNode(result);
            System.out.print("sort - ");
            printListNode(sort(result));
        }

    }

    public static <E> ListNode<E> reverse(ListNode<E> list) {
        if(list == null)
            return list;
        ListNode<E> one = list;
        ListNode<E> two = list.getNext();
        ListNode<E> three = list.getNext();
        one.setNext(null);
        while(two != null) {
            three = three.getNext();
            two.setNext(one);
            one = two;
            two = three;
        }
        return one;
    }

    public static void testReverse() {
        /*
        Expected Results: [ 1 | null ]
        [ 2 | ] -> [ 1 | null ]
        [ 5 | ] -> [ 4 | ] -> [ 3 | ] -> [ 2 | ] -> [ 1 | null ]
        [ 1 | ] -> [ 7 | ] -> [ 4 | ] -> [ 8 | ] -> [ 6 | ] -> [ 2 | null ]
         */
        int[][] tests = {{}, {1}, {1, 2}, {1, 2, 3, 4, 5}, {2, 6, 8, 4, 7, 1},};
        System.out.println("\ntestReverse: ");
        for(int[] i : tests) {
            ListNode<Integer> result = null;
            for(int j = i.length - 1; j >= 0; --j) {
                ListNode<Integer> temp = new ListNode<Integer>(i[j], result);
                result = temp;
            }
            System.out.print("original listNode - ");
            printListNode(result);
            System.out.print("reverse - ");
            printListNode(reverse(result));
        }
    }

    //hasLoop - one pointer at starting node, other pointer looping, see if pointers meet?

    public static <E> void printListNode(ListNode<E> list) {
        if(list == null) {
            System.out.println("null");
        }
        else {
            while(list.getNext() != null) {
                System.out.print("[ " + list.getValue() + " | ] -> ");
                list = list.getNext();
            }
            System.out.println("[ " + list.getValue() + " | null ]");
        }
    }

    public static void tester() {
        // Adapted from a previous HCS1 lab
        JFrame frame = new JFrame();
        JButton button = new JButton("Test");
        button.setBounds(75, 120, 50, 20);
        DefaultListModel<String> contents = new DefaultListModel<>();
        contents.addElement("hasDuplicate");
        contents.addElement("insert");
        contents.addElement("sort");
        contents.addElement("reverse");
        contents.addElement("hasLoop");
        JList<String> list = new JList<>(contents);
        list.setBounds(100 - list.getPreferredSize().width / 2, 20, list.getPreferredSize().width,
                list.getPreferredSize().height);
        frame.add(list);
        frame.add(button);
        frame.setSize(200, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch(list.getSelectedIndex()) {
                    case 0:
                        testHasDuplicate();
                        break;
                    case 1:
                        testInsert();
                        break;
                    case 2:
                        testSort();
                        break;
                    case 3:
                        testReverse();
                        break;
                }
            }
        });
    }
}
