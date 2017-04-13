/**
 * The MIT License
 *
 * Copyright (c) 2013, Sebastian Sdorra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */



package sonia.youtrack.dto;

//~--- JDK imports ------------------------------------------------------------

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Sebastian Sdorra
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentDTO
{

  /**
   * Constructs ...
   *
   */
  CommentDTO() {}

  /**
   * Constructs ...
   *
   *
   * @param id
   * @param author
   * @param authorFullName
   * @param issueId
   * @param deleted
   * @param text
   * @param shownForIssueAuthor
   * @param created
   */
  public CommentDTO(String id, String author, String authorFullName,
    String issueId, boolean deleted, String text, boolean shownForIssueAuthor,
    long created)
  {
    this.id = id;
    this.author = author;
    this.authorFullName = authorFullName;
    this.issueId = issueId;
    this.deleted = deleted;
    this.text = text;
    this.shownForIssueAuthor = shownForIssueAuthor;
    this.created = created;
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public String getAuthor()
  {
    return author;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getAuthorFullName()
  {
    return authorFullName;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public long getCreated()
  {
    return created;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getId()
  {
    return id;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getIssueId()
  {
    return issueId;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getText()
  {
    return text;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isDeleted()
  {
    return deleted;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isShownForIssueAuthor()
  {
    return shownForIssueAuthor;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  @XmlAttribute
  private String author;

  /** Field description */
  @XmlAttribute
  private String authorFullName;

  /** Field description */
  @XmlAttribute
  private long created;

  /** Field description */
  @XmlAttribute
  private boolean deleted;

  /** Field description */
  @XmlAttribute
  private String id;

  /** Field description */
  @XmlAttribute
  private String issueId;

  /** Field description */
  @XmlAttribute
  private boolean shownForIssueAuthor;

  /** Field description */
  @XmlAttribute
  private String text;
}
