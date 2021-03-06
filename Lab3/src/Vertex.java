public class Vertex<E>{

    private E name;

    public Vertex(E name){
        this.name = name;
    }

    public E getName(){
        return name;
    }

    //Two vertexes are equal if their names are equal
    @Override
    public boolean equals(Object v){
        if(v instanceof Vertex<?>){
            Vertex<?> v1 = (Vertex<?>) v;
            if(this.name.equals(v1.getName())){
                return true;
            }
        }
        return false;
    }
}