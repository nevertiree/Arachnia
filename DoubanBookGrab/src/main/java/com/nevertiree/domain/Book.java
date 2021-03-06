package com.nevertiree.domain;

public class Book {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.isbn
     *
     * @mbg.generated
     */
    private String isbn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.vote
     *
     * @mbg.generated
     */
    private Integer vote;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.score
     *
     * @mbg.generated
     */
    private Double score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.rank5
     *
     * @mbg.generated
     */
    private Double rank5;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.rank4
     *
     * @mbg.generated
     */
    private Double rank4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.rank3
     *
     * @mbg.generated
     */
    private Double rank3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.rank2
     *
     * @mbg.generated
     */
    private Double rank2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book.rank1
     *
     * @mbg.generated
     */
    private Double rank1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table book
     *
     * @mbg.generated
     */
    public Book(String isbn, String name, Integer vote, Double score, Double rank5, Double rank4, Double rank3, Double rank2, Double rank1) {
        this.isbn = isbn;
        this.name = name;
        this.vote = vote;
        this.score = score;
        this.rank5 = rank5;
        this.rank4 = rank4;
        this.rank3 = rank3;
        this.rank2 = rank2;
        this.rank1 = rank1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.isbn
     *
     * @return the value of book.isbn
     *
     * @mbg.generated
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.name
     *
     * @return the value of book.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.vote
     *
     * @return the value of book.vote
     *
     * @mbg.generated
     */
    public Integer getVote() {
        return vote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.score
     *
     * @return the value of book.score
     *
     * @mbg.generated
     */
    public Double getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.rank5
     *
     * @return the value of book.rank5
     *
     * @mbg.generated
     */
    public Double getRank5() {
        return rank5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.rank4
     *
     * @return the value of book.rank4
     *
     * @mbg.generated
     */
    public Double getRank4() {
        return rank4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.rank3
     *
     * @return the value of book.rank3
     *
     * @mbg.generated
     */
    public Double getRank3() {
        return rank3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.rank2
     *
     * @return the value of book.rank2
     *
     * @mbg.generated
     */
    public Double getRank2() {
        return rank2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book.rank1
     *
     * @return the value of book.rank1
     *
     * @mbg.generated
     */
    public Double getRank1() {
        return rank1;
    }
}