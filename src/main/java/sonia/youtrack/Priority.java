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

/**
 *
 * @author Sebastian Sdorra
 */
public enum Priority
{
  SHOW_STOPPER("Show-stopper"), CRITICAL("Critical"), MAJOR("Major"),
  NORMAL("Normal"), MINOR("Minor");

  /**
   * Constructs ...
   *
   *
   * @param parameter
   */
  private Priority(String parameter)
  {
    this.parameter = parameter;
  }

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param parameter
   *
   * @return
   */
  public static Priority fromParameter(String parameter)
  {
    Priority priority = null;

    for (Priority p : values())
    {
      if (p.parameter.equalsIgnoreCase(parameter))
      {
        priority = p;

        break;
      }
    }

    return priority;
  }

  /**
   * Method description
   *
   *
   * @return
   */
  public String toParameter()
  {
    return parameter;
  }

  //~--- fields ---------------------------------------------------------------

  /** Field description */
  private final String parameter;
}
