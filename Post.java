public class Post {
    private int postNumber;
    private String content;
    private String author;
    private String time;
    private String[] liked;
    private Post[] responds;

    public Post() {
    }

    public Post(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getLiked() {
        return liked;
    }

    public void setLiked(String[] liked) {
        this.liked = liked;
    }

    public Post[] getResponds() {
        return responds;
    }

    public void setResponds(Post[] responds) {
        this.responds = responds;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void writeAPost(){}
    public void liked(){}
    public void addARespond(){}
    public void showAPost(){}

}
