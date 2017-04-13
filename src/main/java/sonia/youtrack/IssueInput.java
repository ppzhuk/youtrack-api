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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */



package sonia.youtrack;

/**
 *
 * TODO: support for attachments
 *
 * @author Sebastian Sdorra
 * @since 0.2.0
 */
public final class IssueInput
{

  /**
   * Constructs ...
   *
   *
   * @param project
   * @param summary
   * @param description
   */
  public IssueInput(String project, String summary, String description)
  {
    this.project = project;
    this.summary = summary;
    this.description = description;
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getPermittedGroup()
  {
    return permittedGroup;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getProject()
  {
    return project;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String getSummary()
  {
    return summary;
  }

  //~--- set methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param permittedGroup
   */
  public void setPermittedGroup(String permittedGroup)
  {
    this.permittedGroup = permittedGroup;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private final String description;

  /** Field description */
  private final String project;

  /** Field description */
  private final String summary;

  /** Field description */
  private String permittedGroup;
}
