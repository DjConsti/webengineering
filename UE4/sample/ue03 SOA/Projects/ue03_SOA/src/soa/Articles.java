/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package soa;

/**
 * Class that will represent an article.
 *
 * @author Harun Koyuncu(hkoyuncu@student.tgm.ac.at)
 */
public class Articles {

    private String title;
    private String content;
    /**
     * default constructor, no functionallity
     */
    public Articles() {}
    
    /**
     * Constructor will set the supplied attributes
     * @param title title of the article
     * @param content content of the article
     */
    public Articles(String title, String content) {
        this.title = title;
        this.content = content;
    }
    /**
     * will return the content
     * 
     * @return returns the content
     */
    public String getContent() {
        return content;
    }
    /**
     * set the content
     * 
     * @param content the new content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * return the title
     *
     * @return return the tilte
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set the title
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
