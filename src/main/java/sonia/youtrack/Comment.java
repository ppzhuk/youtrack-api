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



package sonia.youtrack;

//~--- non-JDK imports --------------------------------------------------------

import sonia.youtrack.dto.CommentDTO;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

/**
 *
 * @author Sebastian Sdorra
 */
public final class Comment
{

  /**
   * Constructs ...
   *
   *
   * @param dto
   */
  Comment(CommentDTO dto)
  {
    this.dto = dto;
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public User getAuthor()
  {
    return new User(dto.getAuthor(), dto.getAuthorFullName());
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public Date getCreated()
  {
    return new Date(dto.getCreated());
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getId()
  {
    return dto.getId();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getText()
  {
    return dto.getText();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isDeleted()
  {
    return dto.isDeleted();
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public boolean isShownForIssueAuthor()
  {
    return dto.isShownForIssueAuthor();
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private final CommentDTO dto;
}
