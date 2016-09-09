public class ObjectList {
    private Object[] object;
    private int total;
    
    public ObjectList(int total){
        this.object = new Object[total];
        this.total = total;
    }
    public boolean add(Object newObject){
        for(int i=0; i<object.length; i++){
            if(object[i]== null){
                object[i] = newObject;
                return true;
            }
        }
        return false;
    }
    public boolean remove(int index){
        if(index < object.length){
            int i;
            for(i=index; i<object.length-1; i++){            
                object[i] = object[i+1];
            }
            object[i]=null;
            return true;
        }
        return false;
    }
    public boolean isFull(){//checks if 
        
        for(int i=0; i<object.length; i++){
            if(object[i]==null){
                return false;
            }
        }
        return true;
}
    public boolean isEmpty(){// Checks if is empty
        for(int i=0; i<object.length; i++){
            if(object[i]!=null){
                return false;
            }
        }
        return true;
    }
    public Object getObject(int index){
        return object[index];
    }
    public int getTotal(){
        return total;
    }
}
