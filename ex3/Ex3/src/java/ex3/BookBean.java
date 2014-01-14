package ex3;

public class BookBean 
{
        private String id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private String date;
	private String description;
	
        public void setId(String str) { this.id = str; }
	public void setAuthor(String str) { this.author = str; }
	public void setTitle(String str) { this.title = str; }
	public void setGenre(String str) { this.genre = str; }
	public void setPrice(double price) { this.price = price; }
	public void setDate(String str) { this.date = str; }
	public void setDescription(String str) { this.description = str; }
	
        public String getId() { return this.id; }
	public String getAuthor() { return this.author; }
	public String getTitle()  { return this.title; }
	public String getGenre()  { return this.genre; }
	public double getPrice()  { return this.price; }
	public String getDate()   { return this.date; }
	public String getDescription() { return this.description; }
}
	
	