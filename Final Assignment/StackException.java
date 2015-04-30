/* 
   Jimmy Chin
   CS 110
   Final Assignment
   StackException Class
*/

/**Exception thrown when an attempt is made to access
a non-existent element in a stack */
public class StackException extends RuntimeException
{
   public StackException(String s) 
   {
      super(s);
   } 
}
