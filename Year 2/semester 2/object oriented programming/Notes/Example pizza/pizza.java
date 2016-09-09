
/**
 * Write a description of class Pizza here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pizza 
{
  private static  JLabel pizzaLabel;
  private static  JButton orderButton, resetButton, quitButton;
  private static  JTextArea orderDetails;
  private static  JCheckBox[] topping;
  private static  int NUM_TOPPINGS= 5;
  private static JPanel apanel;

public static void main (String argv[])
{
    JFrame messageFrame= new JFrame("Pizza order");
    pizza textMessage = new pizza();
    apanel = new JPanel();
    init();
    messageFrame.add("Center", apanel);
    messageFrame.setSize(350,250);
    messageFrame.setVisible(true);
}
public static void init()
  {
   pizzaLabel = new JLabel("choose your topping: ");
   topping= new JCheckBox[NUM_TOPPINGS];
   topping[0] = new JCheckBox("Extra cheese",false);
   topping[1] = new JCheckBox("Peperoni",false);
   topping[2] = new JCheckBox("Anchovies",false);
   topping[3] = new JCheckBox("Mushrooms",false);
   topping[4] = new JCheckBox("Olives",false);
   

   orderButton = new JButton("Place order");
   orderButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
   processOrder();
   }
   });
   
   resetButton = new JButton("Reset order form");
   resetButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
   resetForm();
   } });
  
   quitButton = new JButton("Quit program");
   quitButton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
  
   System.exit(0);
   } });
   
   orderDetails = new JTextArea("awaiting your order.",5,40);
   apanel.add(pizzaLabel);
   for (int index=0; index < NUM_TOPPINGS;++index)
         apanel.add(topping[index]);
   apanel.add(orderButton);
   apanel.add(resetButton);
   apanel.add(quitButton);
   apanel.add(orderDetails);
}

static void  processOrder()
{
   String order = "you ordered";
   boolean toppingsOrdered= false;
   for (int index=0; index < NUM_TOPPINGS;++index)
      if (topping[index].isSelected()) {
         toppingsOrdered = true;
         order += "\n"+ topping[index].getLabel();
      }
   if (!toppingsOrdered)
      order += "No Toppings";
   order += ".";
   orderDetails.setText(order);
}
   
static void  resetForm()
{
   for (int index=0; index < NUM_TOPPINGS;++index)
       topping[index].setSelected(false);
   orderDetails.setText("awaiting your order.");
}
}    


