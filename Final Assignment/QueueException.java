/* 
   Jimmy Chin
   CS 110
   Final Assignment
   QueueException Class
*/

/**Exception thrown when an attempt is made to access
a non-existent element in a queue */
public class QueueException extends RuntimeException 
{

  public QueueException(String s) 
  {
    super(s);
  } 

}  