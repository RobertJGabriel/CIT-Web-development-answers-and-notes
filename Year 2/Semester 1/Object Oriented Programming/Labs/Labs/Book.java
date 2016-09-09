
public class Book {
	private String title,author;
	private int isbn;
	private double price;
	
	public Book(String title, int isbn, String author, double price){
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}
	public String getTitle(){
		return title;
	}
	public int getIsbn(){
		return isbn;
	}
	public String getAuthor(){
		return author;
	}
	public double getPrice(){
		return price;
	}
	public String toString(){
		return "\n----Book Details----\nTitle: " + title +"\nISBN: " + isbn +"\nAuthor: " + author +"\nPrice: " + price;
	}
}
