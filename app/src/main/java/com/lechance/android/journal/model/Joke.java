public class Joke implements Serializes{
    
    private int id;
    private String content;
    private String author;

    public void setId(int id){
	this.id=id;
    }

    public void setContent(String Content){
	this.content = content;
    }

    public void setAuthor(String author){
	this.author = author;
    }

    public int getId(){
	return this.id;
    }

    public String getContent(){
	return this.content;
    }

    public String getAuthor(){
	return this.author;
    }
}
