import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class ListDisplay implements ActionListener
{
  public static void main(String[] args)
  {
    new ListDisplay();
  }
  
  private static final String VAR_NAME = "test";
  private static String[] IMAGE_NAMES = {"mouse", "chicken", "pig", "sheep", "duck", "horse", "cow"};
  
  private ListNode<String> first;
  private JLabel label;
  private BufferedImage image;
  private Map<String, Image> imageMap;
  private JButton topButton;
  
  public ListDisplay()
  {
    try
    {
      imageMap = new TreeMap<String, Image>();
      for (String name : IMAGE_NAMES)
        imageMap.put(name, ImageIO.read(new File(name + ".png").toURI().toURL()));                                                                                                                                                                                                              imageMap.put("mystery", ImageIO.read(new File("mystery.png").toURI().toURL()));
    }
    catch(IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException();
    }
    
    first = new ListNode<String>(IMAGE_NAMES[0],
                                 new ListNode<String>(IMAGE_NAMES[1],
                                                      new ListNode<String>(IMAGE_NAMES[2], null)));

    JFrame frame = new JFrame("ListDisplay");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
    
    label = new JLabel();
    image = new BufferedImage(1000, 250, BufferedImage.TYPE_INT_RGB);
    update();
    frame.getContentPane().add(label);
    
    JButton button;
    button = new JButton(VAR_NAME + " = null;");
    button.setActionCommand("null");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    topButton = button;

    button = new JButton(VAR_NAME + " = new ListNode<String>( _____ , null);");
    button.setActionCommand("new");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    button = new JButton("Lab.size(" + VAR_NAME + ")");
    button.setActionCommand("size");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    button = new JButton("Lab.contains(" + VAR_NAME + ", _____ )");
    button.setActionCommand("contains");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    button = new JButton("Lab.add(" + VAR_NAME + ", _____ )");
    button.setActionCommand("add");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    button = new JButton("Lab.remove(" + VAR_NAME + ", _____ )");
    button.setActionCommand("remove");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    button = new JButton(VAR_NAME + " = Lab.toList( randomArrayOfLength( _____ ) )");
    button.setActionCommand("toList");
    button.addActionListener(this);
    frame.getContentPane().add(button);
    
    frame.pack();
    frame.setVisible(true);
  }
  
  public void update()
  {
    Graphics g = image.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, image.getWidth(), image.getHeight());
    g.setColor(Color.BLACK);
    
    if (first == null)
      g.drawString(VAR_NAME + ":  null", 20, 75);
    else
    {
      g.drawString(VAR_NAME, 20, 75);
      ListNode<String> temp = first;
      int x = 50;
      while (temp != null)
      {
        //horizontal arrow
        g.drawLine(x, 75,  x + 75, 75);
        g.drawLine(x + 65, 65, x + 75, 75);
        g.drawLine(x + 65, 85, x + 75, 75);
                   
        //box
        g.drawRect(x + 75, 50, 100, 50);
        g.drawLine(x + 125, 50, x + 125, 100);
        if (temp.getNext() == null)
          g.drawLine(x + 125, 100, x + 175, 50);
        
        //vertical arrow
        if (temp.getValue() != null)
        {
          g.drawLine(x + 100, 75, x + 100, 125);
          g.drawLine(x + 90, 115, x + 100, 125);
          g.drawLine(x + 110, 115, x + 100, 125);
        }
        
        if (temp.getValue() == null)
          g.drawLine(x + 75, 50, x + 125, 100);  //show null value as empty box with line
        else
        {
          Image image = imageMap.get(temp.getValue());
          g.drawImage(image, x + 50, 125, image.getWidth(null) * 100 / image.getHeight(null), 100, null);
        }
        
        x += 150;
        temp = temp.getNext();
      }
    }
    
    label.setIcon(new ImageIcon(image));
  }
  
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("null"))
    {
      first = null;
      update();
    }
    else if (command.equals("new"))
    {
      String value = chooseAnimal(false);
      if (value == null)
        return;
      first = new ListNode<String>(value, null);
      update();
    }
    else if (command.equals("size"))
    {
      int result = Lab.size(first);
      update();
      JOptionPane.showMessageDialog(topButton, "" + result);
    }
    else if (command.equals("contains"))
    {
      String value = chooseAnimal(false);
      if (value == null)
        return;
      boolean result = Lab.contains(first, value);
      update();
      JOptionPane.showMessageDialog(topButton, "" + result);
    }
    else if (command.equals("add"))
    {
      String value = chooseAnimal(false);
      if (value == null)
        return;
      boolean result = Lab.add(first, value);
      update();
      JOptionPane.showMessageDialog(topButton, "" + result);
    }
    else if (command.equals("remove"))
    {
      String value = chooseAnimal(true);
      if (value == null)
        return;
      boolean result = Lab.remove(first, value);
      update();
      JOptionPane.showMessageDialog(topButton, "" + result);
    }
    else if (command.equals("toList"))
    {
      Object result = JOptionPane.showInputDialog(topButton,
                                                  null,
                                                  null,
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  null,
                                                  new Object[]{0, 1, 2, 3, 4, 5},
                                                  3);
      
      if (result == null)
        return;
      
      ArrayList<String> possible = new ArrayList<String>();
      for (String value : IMAGE_NAMES)
        possible.add(value);
      String[] values = new String[(Integer)result];
      for (int i = 0; i < values.length; i++)
        values[i] = possible.remove((int)(Math.random() * possible.size()));
      
      first = Lab.toList(values);
      update();
      
      if (values.length > 0)
        JOptionPane.showMessageDialog(topButton, Arrays.toString(values));
    }
    else
      throw new RuntimeException("invalid command:  " + command);
  }
  
  public String chooseAnimal(boolean excludeFirst)
  {
    Object[] values = new Object[IMAGE_NAMES.length];
    for (int i = 0; i < IMAGE_NAMES.length; i++)
    {
      if (excludeFirst && first != null && first.getValue().equals(IMAGE_NAMES[i]))
        values[i] = "porcupine";
      else
        values[i] = IMAGE_NAMES[i];
    }
                                                                                                                                                                                                            if (Math.random() < 0.05) values[(int)(Math.random() * IMAGE_NAMES.length)] = "mystery";
    Object result = JOptionPane.showInputDialog(topButton,
                                                null,
                                                null,
                                                JOptionPane.PLAIN_MESSAGE,
                                                null,
                                                values,
                                                values[0]);
    
    return (String)result;
  }                                                                                                                                                                                                                                             public String toString() { return "ListDisplay22"; }
}