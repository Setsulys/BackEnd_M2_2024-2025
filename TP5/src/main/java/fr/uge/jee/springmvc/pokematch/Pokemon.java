package fr.uge.jee.springmvc.pokematch;


public class Pokemon{
    String name;
    String url;
    int id;

    Pokemon(String name,String url){
        this.name=name;
        this.url=url;
        var stringId = url.split("/");
        this.id=Integer.parseInt(stringId[stringId.length-1]);
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
