// I will eat your silly program.
public class ListMonster
{
  public static void main(String[] args)
  {
    ListNode<String> list;
    
    //size
    test("size", "empty", Lab.size(null) == 0);
    test("size", "1 element", Lab.size(parse("a")) == 1);
    test("size", "2 elements", Lab.size(parse("ab")) == 2);
    test("size", "3 elements", Lab.size(parse("abc")) == 3);
    test("size", "26 elements", Lab.size(parse("abcdefghijklmnopqrstuvwxyz")) == 26);
    
    //contains
    test("contains", "empty", !Lab.contains(null, "a"));
    test("contains", "1 element, present", Lab.contains(parse("a"), "a"));
    test("contains", "1 element, absent", !Lab.contains(parse("a"), "b"));
    test("contains", "5 elements, first", Lab.contains(parse("abcde"), "a"));
    test("contains", "5 elements, second", Lab.contains(parse("abcde"), "b"));
    test("contains", "5 elements, third", Lab.contains(parse("abcde"), "c"));
    test("contains", "5 elements, fourth", Lab.contains(parse("abcde"), "d"));
    test("contains", "5 elements, last", Lab.contains(parse("abcde"), "e"));
    test("contains", "5 elements, absent", !Lab.contains(parse("abcde"), "f"));
    test("contains", "string comparison", Lab.contains(parse("a"), "ba".substring(1)));
    
    //add
    list = parse("a");
    test("add", "1 element, duplicate", !Lab.add(list, "a") && toString(list).equals("a"));
    test("add", "1 element, new", Lab.add(list, "b") && toString(list).equals("ab"));
    test("add", "2 elements, duplicate first", !Lab.add(list, "a") && toString(list).equals("ab"));
    test("add", "2 elements, duplicate last", !Lab.add(list, "b") && toString(list).equals("ab"));
    test("add", "2 elements, new", Lab.add(list, "c") && toString(list).equals("abc"));
    test("add", "3 elements, new", Lab.add(list, "d") && toString(list).equals("abcd"));
    test("add", "4 elements, new", Lab.add(list, "e") && toString(list).equals("abcde"));
    test("add", "5 elements, duplicate first", !Lab.add(list, "a") && toString(list).equals("abcde"));
    test("add", "5 elements, duplicate second", !Lab.add(list, "b") && toString(list).equals("abcde"));
    test("add", "5 elements, duplicate third", !Lab.add(list, "c") && toString(list).equals("abcde"));
    test("add", "5 elements, duplicate fourth", !Lab.add(list, "d") && toString(list).equals("abcde"));
    test("add", "5 elements, duplicate last", !Lab.add(list, "e") && toString(list).equals("abcde"));
    test("add", "string comparison", !Lab.add(parse("a"), "ba".substring(1)));
    
    //remove
    list = parse("a");
    test("remove", "1 element, absent", !Lab.remove(list, "b") && toString(list).equals("a"));
    list = parse("ab");
    test("remove", "2 elements, absent", !Lab.remove(list, "c") && toString(list).equals("ab"));
    test("remove", "2 elements, second", Lab.remove(list, "b") && list.getValue().equals("a") && list.getNext() == null);
    list = parse("abc");
    test("remove", "3 elements, absent", !Lab.remove(list, "d") && toString(list).equals("abc"));
    list = parse("abc");
    test("remove", "3 elements, second", Lab.remove(list, "b") && toString(list).equals("ac"));
    list = parse("abc");
    test("remove", "3 elements, last", Lab.remove(list, "c") && toString(list).equals("ab"));
    list = parse("abcde");
    test("remove", "5 elements, second", Lab.remove(list, "b") && toString(list).equals("acde"));
    list = parse("abcde");
    test("remove", "5 elements, third", Lab.remove(list, "c") && toString(list).equals("abde"));
    list = parse("abcde");
    test("remove", "5 elements, fourth", Lab.remove(list, "d") && toString(list).equals("abce"));
    list = parse("abcde");
    test("remove", "5 elements, last", Lab.remove(list, "e") && toString(list).equals("abcd"));
    list = parse("abcde");
    test("remove", "5 elements, absent", !Lab.remove(list, "f") && toString(list).equals("abcde"));
    test("remove", "string comparison", Lab.remove(parse("ab"), "cb".substring(1)));
    
    //toList
    test("toList", "empty", Lab.toList(new String[]{}) == null);
    list = Lab.toList(new String[]{"a"});
    test("toList", "1 element", list.getValue().equals("a") && list.getNext() == null);
    test("toList", "2 elements", toString(Lab.toList(new String[]{"a", "b"})).equals("ab"));
    test("toList", "3 elements", toString(Lab.toList(new String[]{"a", "b", "c"})).equals("abc"));
    test("toList", "4 elements", toString(Lab.toList(new String[]{"a", "b", "c", "d"})).equals("abcd"));
    test("toList", "5 elements", toString(Lab.toList(new String[]{"a", "b", "c", "d", "e"})).equals("abcde"));
    
    System.out.println("Passed all test cases ..... but I get you next time!!!");
  }

  private static ListNode<String> parse(String s)
  {
    if (s.length() == 0)
      return null;
    else
      return new ListNode<String>(s.substring(0, 1), parse(s.substring(1)));
  }
  
  private static <E> String toString(ListNode<E> list)
  {
    String s = "";
    while (list != null)
    {
      s += list.getValue();
      list = list.getNext();
    }
    return s;
  }
  
  private static void test(String method, String testCase, boolean b)
  {
    if (!b)
      throw new RuntimeException(method + " was incorrect and DELICIOUS for case " + testCase);
  }
}
