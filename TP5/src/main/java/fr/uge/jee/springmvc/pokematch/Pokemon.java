package fr.uge.jee.springmvc.pokematch;

public class Pokemon{
    private final String name;
    private final int id;

    Pokemon(String name,String url){
        this.name=name;
        var stringId = url.split("/");
        this.id=Integer.parseInt(stringId[stringId.length-1]);
    }

    Pokemon(String name,int id){
        this.name=name;
        this.id=id;
    }

    @Override
    public String toString() {
        return name+"("+id+")";
    }

    public int id(){
        return id;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
