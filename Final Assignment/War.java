/* 
   Jimmy Chin
   CS 110
   Final Assignment
   War Class
*/

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class War extends JFrame 
{
   public final static int PLAYER = 0;
   public final static int COMPUTER = 1;
   int clicked = 0;

   //creates a queue for both of the players
   QueueReferenceBased deck1 = new QueueReferenceBased();
   QueueReferenceBased deck2 = new QueueReferenceBased();
   
   //creates a stack for both of the players
   StackReferenceBased stack1 = new StackReferenceBased();
   StackReferenceBased stack2 = new StackReferenceBased();
   
   //creates two buttons that are used to start each round and to start a war
   JButton turn = new JButton("Next turn");
   JButton war = new JButton("War");
   
   JPanel panel = new JPanel(new FlowLayout());
   JPanel box = new JPanel();
   
   LayoutManager layout = new BoxLayout(box, BoxLayout.Y_AXIS);
   
   //attempting to add the number of rounds played
   JLabel p3Size = new JLabel("Round : " + clicked);
   
   //creates two labels for both players
   JLabel p1Size = new JLabel("Your deck size: " + Integer.toString(deck1.getSize()));
   JLabel p2Size = new JLabel("Computer deck size: " + Integer.toString(deck2.getSize()));
   
   //creates two "labels" that are pictures of the back of the cards to represent both of the players cards on hand
   JLabel back = new JLabel(new ImageIcon("back.jpg"));
   JLabel back2 = new JLabel(new ImageIcon("back.jpg"));
   
   JPanel panel2 = new JPanel(new FlowLayout());
   JPanel panel3 = new JPanel();
   
   LayoutManager layout2 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
   JLabel p1Card = new JLabel();
   JLabel p2Card = new JLabel();
   
   //creates a new Random object called random
   Random random = new Random();
   
   JLabel p1War1 = new JLabel();
   JLabel p1War2 = new JLabel();
   
   JLabel p2War1 = new JLabel();
   JLabel p2War2 = new JLabel();
   
   //initializes warStage to 0, there are two stages of the war: one for the face down card and one for the face up card
   int warStage = 0;
   
   JLabel win = new JLabel();

   public War () 
   {
      super("The War Card Game!");
      
      panel3.setLayout(layout2);
      box.setLayout(layout);
      
      //create a new ArrayList named deck; this ArrayList will contain all of the cards
      ArrayList<Card> deck = new ArrayList<Card>(); 
      
      //the following for and nested for loop run through all of the card suits and ranks, where each card is added to the ArrayList deck 
      for (int s = 0; s <= 3; s++) 
      {
         for (int r = 2; r <= 14; r++) 
         {
            deck.add(new Card (r, s));
         }
      }

      //initializing the counter to hold the random cards taken from the ArrayList deck
      int counter = 0;

      while (deck.size() > 0) 
      {
         //takes a random card from the deck and places the card in counter
         counter = random.nextInt(deck.size());
   
         //places the card in counter into the queue of the first player
         deck1.enqueue(deck.get(counter));

         //removes the card in counter from the main deck of cards
         deck.remove(counter);
         
         //same as above except for the other player
         counter = random.nextInt(deck.size());
         deck2.enqueue(deck.get(counter));
         deck.remove(counter);
      }
      
      this.setIconImage(new ImageIcon("back.jpg").getImage());
      
      //if the user exits out of the game the code will stop running
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      //creates two labels for both players
      p1Size.setText("Your deck size: " + Integer.toString(deck1.getSize()));
      p2Size.setText("Computer deck size: " + Integer.toString(deck2.getSize()));      

      //attempting to add the number of rounds played
      p3Size.setText("Round : " + clicked);

      //adds to the pane1 and pane12
      panel.add(p1Size);
      panel.add(back);
      panel.add(panel3);
      panel.add(back2);
      panel.add(p2Size);
      panel.add(p3Size);
      panel2.add(p1War2);
      panel2.add(p1War1);
      panel2.add(p1Card);
      panel2.add(p2Card);
      panel2.add(p2War1);
      panel2.add(p2War2);
      
      //adds pane1 and pane12 to the box GUI
      box.add(panel);
      box.add(panel2);
      
      //adds the round and war buttons as well as the win screen
      panel3.add(turn);
      panel3.add(war);
      panel3.add(win);
      
      //sets all of the buttons in the center of the GUI as well as the win screen
      turn.setAlignmentX(Component.CENTER_ALIGNMENT);
      war.setAlignmentX(Component.CENTER_ALIGNMENT);
      win.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      war.setEnabled(false);
      
      turn.addActionListener(new Flip());
      war.addActionListener(new Wars());
      this.getContentPane().add(box);
      
      //sets the window size of the game at the launch and makes the GUI visible
      setMinimumSize(new Dimension(1000, 600));
      setVisible(true);
      
   }
   
   /**The Flip class is performed when the "Next Turn" button is pressed
      which draws the top card from both of the players' decks and compares
   */
   class Flip implements ActionListener 
   {
   
      public void actionPerformed(ActionEvent e) 
      {
      
         if (e.getSource() instanceof JButton) 
         {
            //if either of the players have 0 cards left then that player loses
            if (deck1.getSize() == 0) 
            {
               winner(COMPUTER);
            } 
            else if (deck2.getSize() == 0) 
            {
               winner(PLAYER);
            } 
            //else it removes the icons from the previous pressing of the button, removes the top cards from
            //the deck stacks, places the new card images, and calls the update and compare methods
            else 
            {
               p1War1.setIcon(null);
               p1War2.setIcon(null);
               p2War1.setIcon(null);
               p2War2.setIcon(null);
               stack1.push(deck1.dequeue());
               stack2.push(deck2.dequeue());
               ImageIcon first = new ImageIcon(getImage(stack1.peek()));
               ImageIcon second = new ImageIcon(getImage(stack2.peek()));
               p1Card.setIcon(first);
               p2Card.setIcon(second);
               update();
               compare();
            }
         }
         
      }
   }
   
    /**The War class executes when the war button is clicked, where it the first card facedown and then the second card
       face up, then it compares the face up cards with one another 
   */
   class Wars implements ActionListener 
   {
      public void actionPerformed(ActionEvent e) 
      {
         
         if (e.getSource() instanceof JButton) 
         {
            //if either of the players have 0 cards left then that player losses
            if (warStage == 1) 
            {
               if (deck1.getSize() == 0) 
               {
                  winner(COMPUTER);
               } 
               else if (deck2.getSize() == 0) 
               {
                  winner(PLAYER);
               } 
               //else it removes removes the top cards from the stacks and places those cards face down for the war,
               //it also calls the update method and initializes the warStage for the next step of war
               else 
               {
                  stack1.push(deck1.dequeue());
                  stack2.push(deck2.dequeue());
                  p1War1.setIcon(new ImageIcon("back.jpg"));
                  p2War1.setIcon(new ImageIcon("back.jpg"));
                  update();
                  warStage = 2;
               }
            } 
            else if (warStage == 2) 
            {
               //if either of the players have 0 cards left then that player losses
               if (deck1.getSize() == 0) 
               {
                  winner(COMPUTER);
               } 
               else if (deck2.getSize() == 0) 
               {
                  winner(PLAYER);
               } 
               //else the next two top cards are pushed from the stacks and they are placed face up for comparison
               else 
               {
                  stack1.push(deck1.dequeue());
                  stack2.push(deck2.dequeue());
                  p1War2.setIcon(new ImageIcon(getImage(stack1.peek())));
                  p2War2.setIcon(new ImageIcon(getImage(stack2.peek())));
                  update();
                  warStage = 3;
                  war.setEnabled(false);
                  turn.setEnabled(true);
                  compare();
               }
            } 
            else 
            {
               warStage = 1;
               p1Card.setIcon(p1War2.getIcon());
               p2Card.setIcon(p2War2.getIcon());
               p1War1.setIcon(null);
               p1War2.setIcon(null);
               p2War1.setIcon(null);
               p2War2.setIcon(null);
            }
         }
      }
   }
   
   /**The getImage method takes an object as an argument and takes the object to determine the suit and rank of
      the card, then based on the card it retrieves the image associating with that card
   */
   public String getImage(Object a) 
   {
      String suit = a.toString().substring(0, 1);
      String rank = a.toString().substring(1, a.toString().length());
      if (suit.equals("0")) 
      {
         return rank + "s.jpg";
      } 
      else if (suit.equals("1")) 
      {
         return rank + "c.jpg";
      } 
      else if (suit.equals("2")) 
      {
         return rank + "d.jpg";
      } 
      else 
      {
         return rank + "h.jpg";
      }
   }
   
   /*The compare method compares the two cards to see which card is higher and updates the players' hands accordingly
   */
   public void compare() 
   {
      if (Integer.parseInt(stack1.peek().toString().substring(1, stack1.peek().toString().length())) > Integer.parseInt(stack2.peek().toString().substring(1, stack2.peek().toString().length()))) 
      {
         while (stack1.isEmpty() == false || stack2.isEmpty() == false) 
         {
            if (stack1.isEmpty() == false && stack2.isEmpty() == false) 
            {
               if (random.nextInt(2) == 1) 
               {
                  deck1.enqueue(stack1.pop());
               } 
               else 
               {
                  deck1.enqueue(stack2.pop());
               }
            } 
            else if (stack1.isEmpty() == false) 
            {
               deck1.enqueue(stack1.pop());
            } 
            else if (stack2.isEmpty() == false) 
            {
               deck1.enqueue(stack2.pop());
            }
         }
         update();
         warStage = 0;
      } 
      else if (Integer.parseInt(stack1.peek().toString().substring(1, stack1.peek().toString().length())) < Integer.parseInt(stack2.peek().toString().substring(1, stack2.peek().toString().length()))) 
      {
         while (stack1.isEmpty() == false || stack2.isEmpty() == false) 
         {
            if (stack1.isEmpty() == false && stack2.isEmpty() == false) 
            {
               if (random.nextInt(2) == 1) 
               {
                  deck2.enqueue(stack1.pop());
               } 
               else 
               {
                  deck2.enqueue(stack2.pop());
               }
            } 
            else if (stack1.isEmpty() == false) 
            {
               deck2.enqueue(stack1.pop());
            } 
            else if (stack2.isEmpty() == false) 
            {
               deck2.enqueue(stack2.pop());
            }
         }
         warStage = 0;
         update();
      } 
      else 
      {
         war.setEnabled(true);
         turn.setEnabled(false);
         if (warStage == 0) 
         {
            warStage = 1;
         }
      }
   }
   
   /**The winner method takes an int as argument and decides based on that int who won,
      it also removes everything from the GUI before displaying results
   */
   public void winner (int victor) 
   {
      panel.remove(p1Size);
      panel.remove(back);
      panel.remove(back2);
      panel.remove(p2Size);
      panel2.remove(p1War2);
      panel2.remove(p1War1);
      panel2.remove(p1Card);
      panel2.remove(p2Card);
      panel2.remove(p2War1);
      panel2.remove(p2War2);
      panel3.remove(turn);
      panel3.remove(war);
      
      if (victor == PLAYER)
      {
         win.setText("Congratulations! You win the game of war!");
      }
      else
         win.setText("Bummer! The Computer wins the game...");
      
   }
   
   /**The update method updates the size of each of the players' decks
   */
   public void update() 
   {
      p1Size.setText("Your deck size: " + Integer.toString(deck1.getSize()));
      p2Size.setText("Computer deck size: " + Integer.toString(deck2.getSize()));
   }
   
   /**Starts the War game
   */
   public static void main(String [] args) 
   {
      new War();
   }
   
}