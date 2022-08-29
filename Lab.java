//For Additional Credit Tester - not used in exercises
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Lab {
    public static void main(String[] args) {
        tester();
    }

    public static int size(ListNode<String> list) {
        int total = 0;
        while (list != null) {
            list = list.getNext();
            ++total;
        }
        return total;
    }

    public static boolean contains(ListNode<String> list, String str) {
        boolean x = false;
        while (list != null) {
            if (list.getValue().equals(str)) {
                x = true;
            }
            list = list.getNext();
        }
        return x;
    }

    public static boolean add(ListNode<String> list, String str) {
        if (!contains(list, str)) {
            while (list.getNext() != null) {
                list = list.getNext();
            }
            list.setNext(new ListNode<String>(str, null));
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean remove(ListNode<String> list, String str) {
        if (contains(list, str)) {
            while (!list.getNext().getValue().equals(str)) {
                list = list.getNext();
            }
            list.setNext(list.getNext().getNext());
            return true;
        } else {
            return false;
        }
    }

    public static ListNode<String> toList(String[] array) {
        ListNode<String> result = null;
        for (int i = array.length - 1; i >= 0; --i) {
            ListNode<String> temp = new ListNode<String>(array[i], result);
            result = temp;
        }
        return result;
    }

    // Additional Credit
    public static <E> boolean hasDuplicate(ListNode<E> list) {
        int size = 0;
        ListNode<E> copy = list;
        while (copy != null) {
            copy = copy.getNext();
            ++size;
        }
        for (int i = 0; i < size; ++i) {
            ListNode<E> temp = list;
            for (int j = i; j < size - 1; ++j) {
                temp = temp.getNext();
                if (temp.getValue() == list.getValue()) {
                    return true;
                }
            }
            list = list.getNext();
        }
        return false;
    }

    public static void testHasDuplicate() {
        String[][] tests = { {}, { "A" }, { "A", "A" }, { "A", "B" },
                { "A", "B", "C", "D", "E" },
                { "A", "B", "C", "D", "C" },
                { "A", "A", "C", "D", "E" } };
        // Expected Results: false, false, true, false, false, true, true
        for (String[] i : tests) {
            System.out.print(Arrays.toString(i));
            System.out.println(" - " + hasDuplicate(toList(i)));
        }
    }

    public static ListNode<Integer> insert(ListNode<Integer> list, int value) {
        if (list.getValue() < value && list.getNext() != null) {
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
        int[][] tests = { { 1, 2, 3, 4, 5 }, 
            { 1, 1, 5, 5, 9, 9 },
            { 9, 10, 11, 12, 13 },
            { 1, 1, 7, 7, 8, 8, 9, 9 } };
        /* Expected Results:
            [ 1 | ] -> [ 2 | ] -> [ 3 | ] -> [ 4 | ] -> [ 5 | ] -> [ 8 | null ]
            [ 1 | ] -> [ 1 | ] -> [ 5 | ] -> [ 5 | ] -> [ 8 | ] -> [ 9 | ] -> [ 9 | null ]
            [ 8 | ] -> [ 9 | ] -> [ 10 | ] -> [ 11 | ] -> [ 12 | ] -> [ 13 | null ]
            [ 1 | ] -> [ 1 | ] -> [ 7 | ] -> [ 7 | ] -> [ 8 | ] -> [ 8 | ] -> [ 8 | ] -> [ 9 | ] -> [ 9 | null ]
        */
        int value = 8;
        for (int[] i : tests) {
            ListNode<Integer> result = null;
            for (int j = i.length - 1; j >= 0; --j) {
                ListNode<Integer> temp = new ListNode<Integer>(i[j], result);
                result = temp;
            }
            printListNode(insert(result, value));
        }
    }

    public static <E> void printListNode(ListNode<E> list) {
        while(list.getNext() != null) {
            System.out.print("[ " + list.getValue() + " | ] -> ");
            list = list.getNext();
        }
        System.out.println("[ " + list.getValue() + " | null ]");
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
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (list.getSelectedIndex()) {
                    case 0:
                        testHasDuplicate();
                        break;
                    case 1:
                        testInsert();
                        break;
                }
                frame.setVisible(false);
            }
        });
    }
}