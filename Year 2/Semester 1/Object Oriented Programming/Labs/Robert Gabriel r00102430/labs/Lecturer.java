
public class Lecturer {
    final private int MAX_NO_OF_BOOKS = 15;//amount of books allowed normally 15
    private String name;
    private int id;
    private BookList books;
    
    public Lecturer(String name, int id){
        this.name = name;
        this.id = id;
        books = new BookList(MAX_NO_OF_BOOKS);
    }
    
    public String getName(){
        return name;//returns Name
    }
    public int getId(){
        return id; //returns ID
    }
    public void addBook(Book book){
      outOfRange problem = new outOfRange ("Is all full"); 
        try {
            if(books.isFull()){
                throw problem;
            }else{
            books.add(new Book(book.getTitle(), book.getIsbn(), book.getAuthor(), book.getPrice()));
            }
        }catch (outOfRange exception){
         System.out.println("Limit reached");
        }
    }
    public void removeBook(int index){
        books.removeBook(index);   ////////////// remove
    }
    
    public BookList getBookList(){////////////////////////////////////////////////////////////////////////////////////////
       BookList copy = new BookList(MAX_NO_OF_BOOKS);
       for (int i = 0; i < MAX_NO_OF_BOOKS; i++)
       {
           copy.add(books.getBook(i));
        }
        return copy;//returns book list
    }
    public String toString(){
        return" -------Details-------\n"+ "Name: " + name + "\nId: " + id +"\n";
    }
    public void print(){
        System.out.println(toString()); //prints details
    }
}