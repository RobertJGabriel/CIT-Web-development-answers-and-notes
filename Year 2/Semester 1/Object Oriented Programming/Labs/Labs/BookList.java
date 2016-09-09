public class BookList extends ObjectList{
	
	public BookList(int maxNumOfBooks){
		super(maxNumOfBooks);
	}
	public Book getBook(int index){
		return (Book) getObject(index);
	}
	public Book search(int index){
		return (Book) getObject(index);
	}
	public boolean removeBook(int index){
		if(index < getTotal()){
			remove(index);
			return true;
		}
		return false;
	}
	public double calculateYearlyBookPayment(){
		double totalPrice = 0.0;
		for(int i=0; i<getTotal(); i++){		
			if(super.getObject(i)!=null){
				totalPrice += ((Book)this.getObject(i)).getPrice();
			}
		}
		return totalPrice;//Returns total price
	}
}