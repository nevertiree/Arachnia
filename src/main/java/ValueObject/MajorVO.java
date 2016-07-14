package ValueObject;

/**
 * Created by Lance on 7/14/16.
 */
public class MajorVO {
    private String no;
    private String name;
    private int rank;

    //Constructor
    public MajorVO(){}

    public MajorVO(String no,String name,int rank){
        this.no=no;
        this.name=name;
        this.rank=rank;
    }

    //No method
    public String getNo() {return no;}
    public void setNo(String no) {this.no = no;}
    //Name method
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    //Rank method
    public int getRank() {return rank;}
    public void setRank(int rank) {this.rank = rank;}
}
