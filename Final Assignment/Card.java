/* 
   Jimmy Chin
   CS 110
   Final Assignment
   Card Class
*/

/** The Card class is used to create a card out of a total deck of 52 cards
*/
public class Card 
{
   /**The two factors determining a card are its suit and rank
   */
   private int suit;
   private int rank;
   
   /**The Card constructor takes two ints as arguments
   */
   public Card (int r, int s) 
   {
      rank = r;
      suit = s;
   }
   
   /**The getRank method returns the rank of the card
   */
   public int getRank () 
   {
      return rank;
   }
   
   /**The getSuit method returns the suit of the card
   */
   public int getSuit () 
   {
      return suit;
   }
   
   /**The toString method returns a String of the suit and rank
   */
   public String toString () {
      return "" + suit + rank;
   }

}