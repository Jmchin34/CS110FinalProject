/* 
   Jimmy Chin
   CS 110
   Final Assignment
   Card Class
*/

public class Card 
{  
   //initilizing the four suits of the cards
   public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;
   
   //initilizing the face cards
   public final static int JACK = 11, QUEEN = 12, KING = 13, ACE = 14;
   
   //initilizes an integer to hold the specific suit of the card
   private final int suit;
   
   //initilizes an integer to hold the value of the card
   private final int rank;
   
   //note: all of the integers above are FINAL values and cannot be changed
   
   //constructor for suit and value
   public Card(int cardSuit, int cardRank) 
   {
      suit = cardSuit;   
      rank = cardRank;
   }

   //method will return the suit of card
   public int getSuit()
   {
      return suit;
   }
      
   //method will return the value of card   
   public int getRank()
   {
      return rank;
   }
   
   //method to return string of the suit of card
   public String getSuitString() 
   {
      switch (suit) 
      {
      case SPADES: return "Spades";
      case HEARTS: return "Hearts";
      case DIAMONDS: return "Diamonds";
      case CLUBS: return "Clubs";
      default: return "Not Valid.";
      }
   }

   //method to return string of the value of card
   public String getRankString() 
   {
      switch (rank) 
      {
         case 2: return "2";
         case 3: return "3";
         case 4: return "4";
         case 5: return "5";
         case 6: return "6";
         case 7: return "7";
         case 8: return "8";
         case 9: return "9";
         case 10: return "10";
         case 11: return "Jack";
         case 12: return "Queen";
         case 13: return "King";
         case 14: return "Ace";
         default: return "Not Valid.";
      }
   }
   
   //method to return a string representing a card(both value and suit)
   public String toString() 
   {
      return getRankString() + " of " + getSuitString();
   }
}